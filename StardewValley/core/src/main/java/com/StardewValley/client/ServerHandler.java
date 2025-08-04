package com.StardewValley.client;

import com.StardewValley.models.ConnectionThread;
import com.StardewValley.models.Message;

import java.io.IOException;
import java.net.Socket;

public class ServerHandler extends ConnectionThread {


    public ServerHandler(Socket socket) throws IOException {
        super(socket);
    }

    @Override
    protected boolean handleMessage(Message message) {
        return false;
    }

    @Override
    public boolean initialHandshake() {
        return true;
    }
}
