package com.StardewValley.server;

import java.io.IOException;

public class ServerMain {
    public static void main(String[] args) {
        if (args.length < 2) {
            return;
        }

        try {
            int port = Integer.parseInt(args[1]);
            AppServer.setClientListener(new ClientListener(port));
            AppServer.start();
            System.out.println("Server started on port " + port);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
