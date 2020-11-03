package net.codejava.networking.chat.client;

import java.net.*;
import java.io.*;

public class ChatClient {
    private String hostname;
    private int port;
    private String userName;

    public ChatClient(String hostname, int port) {
        this.hostname = hostname;
        this.port = port;
    }

    public static void main(String[] args) {

        String hostname = "localhost";
        int port = 5464;

        ChatClient client = new ChatClient(hostname, port);
        client.execute();
    }

    public void execute() {
        try {
            Socket socket = new Socket(hostname, port);

            System.out.println("Erfolgreich mit dem Server verbunden");

            new ReadThread(socket, this).start();
            new WriteThread(socket, this).start();

        } catch (UnknownHostException error) {
            System.out.println("Server nicht gefunden: " + error.getMessage());
        } catch (IOException error) {
            System.out.println("Error: " + error.getMessage());
        }

    }

    void setUserName(String userName) {
        this.userName = userName;
    }

    String getUserName() {
        return this.userName;
    }
}