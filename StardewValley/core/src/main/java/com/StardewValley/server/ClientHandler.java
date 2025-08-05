package com.StardewValley.server;

import com.StardewValley.models.ConnectionThread;
import com.StardewValley.models.JSONUtils;
import com.StardewValley.models.Message;
import com.StardewValley.models.game_structure.Game;
import com.StardewValley.models.interactions.User;
import com.StardewValley.server.controllers.Controller;
import com.StardewValley.server.controllers.RegisterMenuController;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class ClientHandler extends ConnectionThread {
    private Controller currentController;

    private User clientUser;
    private Game clientGame;

    protected ClientHandler(Socket socket) throws IOException {
        super(socket);
        this.currentController = new RegisterMenuController(this);
    }

    protected boolean handleMessage(Message message) {
        if (message.getType() == Message.Type.command || message.getType() == Message.Type.request ||
            message.getType() == Message.Type.change) {

            sendMessage(currentController.handleMessage(message));
            return true;
        }
        return false;
    }

    @Override
    public boolean initialHandshake() {
        return true;
    }

    public Controller getCurrentController() {
        return currentController;
    }

    public void setCurrentController(Controller currentController) {
        this.currentController = currentController;
    }

    public User getClientUser() {
        return clientUser;
    }

    public void setClientUser(User clientUser) {
        this.clientUser = clientUser;
    }
}
