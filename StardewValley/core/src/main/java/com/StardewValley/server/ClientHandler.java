package com.StardewValley.server;

import com.StardewValley.models.ConnectionThread;
import com.StardewValley.models.JSONUtils;
import com.StardewValley.models.Message;
import com.StardewValley.server.controllers.Controller;
import com.StardewValley.server.controllers.RegisterMenuController;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class ClientHandler extends Thread {

    protected final DataInputStream dataInputStream;
    protected final DataOutputStream dataOutputStream;
    protected Socket socket;
    protected AtomicBoolean end;
    private Controller currentController;

    protected ClientHandler(Socket socket) throws IOException {
        this.socket = socket;
        this.dataInputStream = new DataInputStream(socket.getInputStream());
        this.dataOutputStream = new DataOutputStream(socket.getOutputStream());
        this.end = new AtomicBoolean(false);
        currentController = new RegisterMenuController(this);

    }

//    public Message sendAndWaitForResponse(Message message) {
//        sendMessage(message);
//        try {
//            socket.setSoTimeout(0);
//            return JSONUtils.fromJson(dataInputStream.readUTF());
//        } catch (Exception e) {
//            System.err.println("Request Timed out.");
//            return null;
//        }
//    }

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
        while (!end.get()) {
            try {
                String receivedStr = dataInputStream.readUTF();
                Message message = JSONUtils.fromJson(receivedStr);
                handleMessage(message);
            } catch (Exception e) {
                e.printStackTrace();
                break;
            }
            finally {
                this.end.set(true);
            }
        }

        end();
    }

    public void end() {
        end.set(true);
        try {
            socket.close();
        } catch (IOException e) {}
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

    protected boolean handleMessage(Message message) {
        if (message.getType() == Message.Type.command || message.getType() == Message.Type.request ||
            message.getType() == Message.Type.change) {

            sendMessage(currentController.handleMessage(message));
            return true;
        }
        return false;
    }

    public Controller getCurrentController() {
        return currentController;
    }

    public void setCurrentController(Controller currentController) {
        this.currentController = currentController;
    }
}
