package com.StardewValley.server;

import com.StardewValley.models.ConnectionThread;
import com.StardewValley.models.JSONUtils;
import com.StardewValley.models.Message;
import com.StardewValley.server.controllers.Controller;
import com.StardewValley.server.controllers.RegisterMenuController;

import java.io.IOException;
import java.net.Socket;

public class ClientHandler extends ConnectionThread {
    private Controller currentController;

    public ClientHandler(Socket socket) throws IOException {
        super(socket);

        currentController = new RegisterMenuController(this);
    }

    @Override
    public boolean initialHandshake() {
        return true;
    }

    @Override
    protected boolean handleMessage(Message message) {
        if (message.getType() == Message.Type.command || message.getType() == Message.Type.request ||
            message.getType() == Message.Type.change) {

            sendMessage(currentController.handleMessage(message));
            return true;
        }
        return false;
    }

    @Override
    public void run() {
        super.run();
    }

    public Controller getCurrentController() {
        return currentController;
    }

    public void setCurrentController(Controller currentController) {
        this.currentController = currentController;
    }
}
