package com.StardewValley.server;

import com.StardewValley.models.JSONUtils;
import com.StardewValley.models.Message;
import com.StardewValley.models.game_structure.Game;
import com.StardewValley.models.interactions.User;
import com.StardewValley.server.controllers.Controller;
import com.StardewValley.server.controllers.RegisterMenuController;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
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

    protected ClientHandler(Socket socket) throws IOException {
        this.socket = socket;
        this.dataInputStream = new DataInputStream(socket.getInputStream());
        this.dataOutputStream = new DataOutputStream(socket.getOutputStream());
        this.receivedMessagesQueue = new LinkedBlockingQueue<>();
        this.end = new AtomicBoolean(false);
        this.currentController = new RegisterMenuController(this);
    }

    protected boolean handleMessage(Message message) {
        if (message.getType() == Message.Type.command || message.getType() == Message.Type.request ||
            message.getType() == Message.Type.change) {

            sendMessage(currentController.handleMessage(message));
            return true;
        }
        return false;
    }

    public boolean initialHandshake() {
        return true;
    }

    public Message sendAndWaitForResponse(Message message, int timeoutMilli) {
        sendMessage(message);
        try {
            if (initialized) return receivedMessagesQueue.poll(timeoutMilli, TimeUnit.MILLISECONDS);
            socket.setSoTimeout(timeoutMilli);
            var result = JSONUtils.fromJson(dataInputStream.readUTF());
            socket.setSoTimeout(0);
            return result;
        } catch (Exception e) {
            System.err.println("Request Timed out.");
            return null;
        }
    }

    public synchronized void sendMessage(Message message) {
        String JSONString = JSONUtils.toJson(message);

        try {
            dataOutputStream.writeUTF(JSONString);
            dataOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
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
                String receivedStr = dataInputStream.readUTF();
                Message message = JSONUtils.fromJson(receivedStr);
                boolean handled = handleMessage(message);
                if (!handled) try {
                    receivedMessagesQueue.put(message);
                } catch (InterruptedException e) {}
            } catch (Exception e) {
                e.printStackTrace();
                break;
            }
        }

        end();
    }

    public String getOtherSideIP() {
        return otherSideIP;
    }

    public void setOtherSideIP(String otherSideIP) {
        this.otherSideIP = otherSideIP;
    }

    public int getOtherSidePort() {
        return otherSidePort;
    }

    public void setOtherSidePort(int otherSidePort) {
        this.otherSidePort = otherSidePort;
    }

    public void end() {
        end.set(true);
        try {
            if (clientUser != null)
                AppServer.getOnlineUsers().remove(clientUser);
            AppServer.getClientHandlers().remove(this);
            socket.close();
        } catch (IOException e) {}
    }

    public boolean equals(String IP, int port) {
        return (otherSideIP.equals(IP) && otherSidePort == port);
    }

    public Socket getSocket() {
        return socket;
    }

    public DataInputStream getDataInputStream() {
        return dataInputStream;
    }

    public DataOutputStream getDataOutputStream() {
        return dataOutputStream;
    }

    public Controller getCurrentController() {
        return currentController;
    }

    public void setCurrentController(Controller currentController) {
        this.currentController = currentController;
    }

    public User getClientUser() {
        return clientUser;
    }

    public void setClientUser(User clientUser) {
        this.clientUser = clientUser;
    }
}
