package net.codejava.networking.chat.client;

import net.codejava.networking.chat.exceptions.HostException;

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

    public String getHostname() {
        return hostname;
    }

    public int getPort() {
        return port;
    }

    /**
     * Verbindet sich mit dem Server
     * @param args Kommandozeilenparameter
     */
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
            throw new HostException("Host Error: ", error);
            //System.out.println("Server nicht gefunden: " + error.getMessage());
        } catch (IOException error) {
            System.out.println("Error: " + error.getMessage());
        }

    }

    /**
     * @param userName setzt den Username
     */
    void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return gibt den Username zurück
     */
    String getUserName() {
        return this.userName;
    }
}