package com.StardewValley.server;

import com.StardewValley.client.AppClient;
import com.StardewValley.models.JSONUtils;
import com.StardewValley.models.Labi;
import com.StardewValley.models.Message;
import com.StardewValley.models.game_structure.Game;
import com.StardewValley.models.interactions.Player;
import com.StardewValley.models.interactions.User;
import com.StardewValley.server.controllers.Controller;
import com.StardewValley.server.controllers.GameMenuController;
import com.StardewValley.server.controllers.RegisterMenuController;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class ClientHandler extends Thread {
    protected final DataInputStream dataInputStream;
    protected final DataOutputStream dataOutputStream;
    protected final BlockingQueue<Message> receivedMessagesQueue;
    protected String otherSideIP;
    protected int otherSidePort;
    protected Socket socket;
    protected AtomicBoolean end;
    protected boolean initialized = false;
    private Controller currentController;

    private User clientUser;
    private Game clientGame;
    private Player clientPlayer;

    public ClientHandler(Socket socket) throws IOException {
        this.socket = socket;
        // (Optional) buffer the streams for fewer syscalls
        this.dataInputStream  = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
        this.dataOutputStream = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
        this.receivedMessagesQueue = new LinkedBlockingQueue<>();
        this.end = new AtomicBoolean(false);
        this.currentController = new RegisterMenuController(this);
    }

    /* -------------------- length‑prefixed framing helpers -------------------- */

    private static void writeString(DataOutputStream out, String s) throws IOException {
        byte[] bytes = s.getBytes(StandardCharsets.UTF_8);
        out.writeInt(bytes.length);   // 4‑byte length prefix
        out.write(bytes);             // payload
        out.flush();
    }

    private static String readString(DataInputStream in) throws IOException {
        int len = in.readInt();               // may block until 4 bytes available
        byte[] buf = new byte[len];
        in.readFully(buf);                    // blocks until len bytes read
        return new String(buf, StandardCharsets.UTF_8);
    }

    /* ------------------------------------------------------------------------ */

    protected boolean handleMessage(Message message) {
        if (message.getType() == Message.Type.command
            || message.getType() == Message.Type.request
            || message.getType() == Message.Type.change) {
            sendMessage(currentController.handleMessage(message));
            return true;
        }
        return false;
    }

    public boolean initialHandshake() { return true; }

    public Message sendAndWaitForResponse(Message message, int timeoutMilli) {
        sendMessage(message);
        try {
            if (initialized) {
                return receivedMessagesQueue.poll(timeoutMilli, TimeUnit.MILLISECONDS);
            }
            // direct blocking read with timeout before the readInt/readFully pair
            socket.setSoTimeout(timeoutMilli);
            String json = readString(dataInputStream);
            socket.setSoTimeout(0); // back to infinite
            return JSONUtils.fromJson(json);
        } catch (Exception e) {
            System.err.println("Request Timed out or failed: " + e.getMessage());
            return null;
        }
    }

    public synchronized void sendMessage(Message message) {
        String json = JSONUtils.toJson(message);
        try {
            writeString(dataOutputStream, json);
        } catch (IOException e) {
            e.printStackTrace();
            end(); // close on fatal I/O
        }
    }

    @Override
    public void run() {
        initialized = false;
        if (!initialHandshake()) {
            System.err.println("Initial HandShake failed with remote device.");
            end();
            return;
        }
        initialized = true;

        while (!end.get()) {
            try {
                String receivedStr = readString(dataInputStream);
                Message message = JSONUtils.fromJson(receivedStr);
                boolean handled = handleMessage(message);
                if (!handled) {
                    try { receivedMessagesQueue.put(message); } catch (InterruptedException ignored) {}
                }
            } catch (EOFException | SocketException closed) {
                // peer closed
                break;
            } catch (IOException io) {
                io.printStackTrace();
                break;
            }
        }
        end();
    }

    public void end() {
        end.set(true);
        try {
            if (clientUser != null) {
                if (currentController instanceof GameMenuController gameMenuController) {
                    for (Labi labi : AppServer.getLabies()) {
                        if (labi.getUsers().contains(clientUser)) {
                            gameMenuController.exitLabi(new Message(new HashMap<>() {{
                                put("function", "exitLabi");
                                put("arguments", new ArrayList<>(Arrays.asList(String.valueOf(labi.getID()), clientUser.getUsername())));
                            }}, Message.Type.command));
                        }
                    }
                }
                AppServer.getOnlineUsers().remove(clientUser);
            }
            AppServer.getClientHandlers().remove(this);
            socket.close();
        } catch (IOException ignored) {}
    }

    // getters/setters (unchanged) ...
    public Controller getCurrentController() { return currentController; }
    public void setCurrentController(Controller c) { this.currentController = c; }
    public User getClientUser() { return clientUser; }
    public void setClientUser(User u) { this.clientUser = u; }
    public Game getClientGame() { return clientGame; }
    public void setClientGame(Game g) { this.clientGame = g; }
    public Player getClientPlayer() { return clientPlayer; }
    public void setClientPlayer(Player p) { this.clientPlayer = p; }
}
