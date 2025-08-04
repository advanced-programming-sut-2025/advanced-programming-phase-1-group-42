package com.StardewValley.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;

public class ClientListener extends Thread {
    private static int port;
    private static String IP;
    private static ServerSocket serverSocket;
    private static ExecutorService threadPool;

    public ClientListener(int port) throws IOException {
        serverSocket = new ServerSocket(port);
    }

    @Override
    public void run() {
        while (!AppServer.isEnded()) {
            try {
                Socket socket = serverSocket.accept();
                if (socket != null) {
                    new ClientHandler(socket).start();
                    System.out.println("New client connected");
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
