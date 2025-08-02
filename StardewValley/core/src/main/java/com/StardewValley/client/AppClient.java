package com.StardewValley.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class AppClient {
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;

    public void connect(String host, int port) throws IOException {
        socket = new Socket(host, port);
        in = new DataInputStream(socket.getInputStream());
        out = new DataOutputStream(socket.getOutputStream());
    }

    public void sendMessage(String message) throws IOException {
        out.writeUTF(message);
        out.flush();
    }

    public String receiveMessage() throws IOException {
        return in.readUTF();
    }

    public void close() throws IOException {
        socket.close();
    }
}
