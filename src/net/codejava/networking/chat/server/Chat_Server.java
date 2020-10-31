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

    public static void main(String[] args) {
        int port = 5464;

        Chat_Server server = new Chat_Server(port);
        server.execute();
    }

    public void execute() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {

            System.out.println("Chat Server ist online auf folgendem Port: " + port);

            while (true) {
                Socket socket = serverSocket.accept();

                UserThread newUser = new UserThread(socket, this);
                userThreads.add(newUser);
                newUser.start();
            }

        } catch (IOException error) {
            System.out.println("Error beim Server: " + error.getMessage());
            error.printStackTrace();
        }
    }

    //Gibt die Nachricht an den Benutzer weiter
    void broadcast(String message, UserThread excludeUser) {
        for (UserThread aUser : userThreads) {
            if (aUser != excludeUser) {
                aUser.sendMessage(message);
            }
        }
    }

    void addUserName(String userName) {
        userNames.add(userName);
    }

    void removeUser(String userName, UserThread aUser) {
        boolean removed = userNames.remove(userName);
        if (removed) {
            userThreads.remove(aUser);
            System.out.println(userName + " ist nicht mehr da");
        }
    }

    Set<String> getUserNames() {
        return this.userNames;
    }

    boolean hasUsers() {
        return !this.userNames.isEmpty();
    }
}