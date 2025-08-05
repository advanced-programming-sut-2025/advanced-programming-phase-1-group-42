package com.StardewValley.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ClientListener extends Thread {
    private static int port;
    private static String IP;
    private static ServerSocket serverSocket;
    private static ExecutorService threadPool;

    public ClientListener(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        threadPool = Executors.newCachedThreadPool();
    }

    @Override
    public void run() {
        while (!AppServer.isEnded()) {
            try {
                Socket socket = serverSocket.accept();
                if (socket != null && !socket.isClosed()) {
                    threadPool.execute(new ClientHandler(socket));
                    System.out.println("New client connected with port: " + socket.getPort());
                }
            }
            catch (IOException e) {
                e.printStackTrace();
                break;
            }
        }
    }
}
