package net.codejava.networking.chat.server;

import java.io.*;
import java.net.*;

/**
 * Dieser Thread ist dazu da, die Verbindung von jedem Client zum Server zu erstellen
 */

public class UserThread extends Thread {
    private Socket socket;
    private Chat_Server server;
    private PrintWriter writer;

    public UserThread(Socket socket, Chat_Server server) {
        this.socket = socket;
        this.server = server;
    }

    public void run() {
        try {
            InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            String userName = reader.readLine();
            server.addUserName(userName);
            OutputStream output = socket.getOutputStream();
            writer = new PrintWriter(output, true);

            String serverMessage = userName + " ist aufgetaucht";
            server.broadcast(serverMessage, this);

            String clientMessage;

            do {
                clientMessage = reader.readLine();
                serverMessage = "|" + userName + "|: " + clientMessage;
                server.broadcast(serverMessage, this);

            } while (!clientMessage.equals("bye"));

            server.removeUser(userName, this);
            socket.close();

            serverMessage = userName + " hat den Chat verlassen....";
            server.broadcast(serverMessage, this);

        } catch (IOException ex) {
            System.out.println("Error in UserThread: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    /**
     * Sendet die Nachricht
     * @param message ist die Nachricht
     */
    void sendMessage(String message) {
        writer.println(message);
    }
}