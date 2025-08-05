package com.StardewValley.client;

import com.StardewValley.models.ConnectionThread;
import com.StardewValley.models.JSONUtils;
import com.StardewValley.models.Message;
import com.StardewValley.models.interactions.User;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class ServerHandler {
    protected DataInputStream dataInputStream;
    protected DataOutputStream dataOutputStream;
    protected Socket socket;

    public ServerHandler(Socket socket) throws IOException {
        this.socket = socket;
        this.dataInputStream = new DataInputStream(socket.getInputStream());
        this.dataOutputStream = new DataOutputStream(socket.getOutputStream());
    }

    public Message sendAndWaitForResponse(Message message) {
        sendMessage(message);
        try {
            return JSONUtils.fromJson(dataInputStream.readUTF());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void sendMessage(Message message) {
        String JSONString = JSONUtils.toJson(message);
        try {
            dataOutputStream.writeUTF(JSONString);
            dataOutputStream.flush();


        } catch (IOException e) {
            e.printStackTrace();
        }
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
}
