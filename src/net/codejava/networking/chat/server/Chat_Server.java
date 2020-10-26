package net.codejava.networking.chat.server;

import java.io.*;
import java.net.*;
import java.util.*;

public class Chat_Server {
    private int port;
    private Set<String> userNames = new HashSet<>();
    private Set<UserThread> userThreads = new HashSet<>();

    public Chat_Server(int port) {
        this.port = port;
    }

    public void execute() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {

            System.out.println("Port: " + port);

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Neuer Benutzer ist gejoint");

                UserThread newUser = new UserThread(socket, this);
                userThreads.add(newUser);
                newUser.start();

            }

        } catch (IOException ex) {
            System.out.println("Server Error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        if (args.length < 1) {
            System.exit(0);
        }

        int port = Integer.parseInt(args[0]);

        Chat_Server server = new Chat_Server(port);
        server.execute();
    }

    //Nachricht versenden


    //fügt neue Benutzer hinzu
    void addUserName(String userName) {
        userNames.add(userName);
    }

    //Wenn in User leaved

    Set<String> getUserNames() {
        return this.userNames;
    }

    boolean hasUsers() {
        return !this.userNames.isEmpty();
    }
}