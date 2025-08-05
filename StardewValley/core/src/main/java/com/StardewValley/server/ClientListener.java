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
    }

    @Override
    public void run() {
        threadPool = Executors.newCachedThreadPool();

        while (!AppServer.isEnded()) {
            try {
                Socket socket = serverSocket.accept();
                if (socket != null && !socket.isClosed()) {
                    threadPool.execute(new ClientHandler(socket));
                    System.out.println("New client connected with port: " + socket.getPort());
                }
            }
            catch (IOException e) {
                threadPool.shutdownNow();
                try {
                    serverSocket.close();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                e.printStackTrace();
                break;
            }
        }

        try {
            threadPool.shutdownNow();
            serverSocket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
