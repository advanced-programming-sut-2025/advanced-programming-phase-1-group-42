package com.StardewValley.client;

import com.StardewValley.models.JSONUtils;
import com.StardewValley.models.Message;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class ServerHandler {
    private final DataInputStream  dataInputStream;
    private final DataOutputStream dataOutputStream;
    private final Socket socket;

    public ServerHandler(Socket socket) throws IOException {
        this.socket = socket;
        // Buffering is optional but recommended
        this.dataInputStream  = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
        this.dataOutputStream = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
    }

    /* ---------------- length‑prefixed UTF‑8 helpers ---------------- */

    private static void writeString(DataOutputStream out, String s) throws IOException {
        byte[] bytes = s.getBytes(StandardCharsets.UTF_8);
        out.writeInt(bytes.length);   // 4‑byte length prefix
        out.write(bytes);             // payload
        out.flush();
    }

    private static String readString(DataInputStream in) throws IOException {
        int len = in.readInt();       // blocks until 4 bytes read
        byte[] buf = new byte[len];
        in.readFully(buf);            // blocks until len bytes read
        return new String(buf, StandardCharsets.UTF_8);
    }

    /* ---------------------------------------------------------------- */

    public Message sendAndWaitForResponse(Message message) {
        sendMessage(message);
        try {
            String json = readString(dataInputStream);
            return JSONUtils.fromJson(json);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void sendMessage(Message message) {
        String json = JSONUtils.toJson(message);
        try {
            writeString(dataOutputStream, json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Socket getSocket() { return socket; }
    public DataInputStream getDataInputStream() { return dataInputStream; }
    public DataOutputStream getDataOutputStream() { return dataOutputStream; }

    public void closeQuietly() {
        try { socket.close(); } catch (IOException ignored) {}
    }
}
