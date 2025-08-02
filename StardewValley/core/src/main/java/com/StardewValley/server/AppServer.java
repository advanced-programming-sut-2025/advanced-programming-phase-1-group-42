package com.StardewValley.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AppServer {
    private static final int PORT = 1111;
    private ServerSocket serverSocket;
    private ExecutorService threadPool;

    public void start() throws IOException {
        serverSocket = new ServerSocket(PORT);
        threadPool = Executors.newCachedThreadPool();

        System.out.println("Server started on port " + PORT);

        while (true) {
            Socket clientSocket = serverSocket.accept();
            System.out.println("New client connected: " + clientSocket.getInetAddress() + ":" + clientSocket.getPort());
//            threadPool.execute(new ClientHandler(clientSocket));
        }
    }

    public static void main(String[] args) {
        try {
            new AppServer().start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
