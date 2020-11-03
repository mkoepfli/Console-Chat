package net.codejava.networking.chat.client;


import java.io.*;
import java.net.*;
import java.util.Scanner;

public class WriteThread extends Thread {
    private PrintWriter writer;
    private Socket socket;
    private ChatClient client;

    public void run() {
        //Console console = System.console();

        Scanner myObj = new Scanner(System.in);
        System.out.println("\nGib deinen Username ein: ");

        String userName = myObj.nextLine();

        // String userName = console.readLine("\nGib deinen Username ein: ");
        client.setUserName(userName);
        writer.println(userName);

        String text;

        do {
            // text = console.readLine("|" + userName + "|: ");
            // writer.println(text);

            Scanner input = new Scanner(System.in);
            System.out.println("[" + userName + "]:");

            text = input.nextLine();
            writer.println(text);

        } while (!text.equals("Tschau"));

        try {
            socket.close();
        } catch (IOException error) {
            System.out.println("Error beim Text übermitteln: " + error.getMessage());
        }
    }

    public WriteThread(Socket socket, ChatClient client) {
        this.socket = socket;
        this.client = client;

        try {
            OutputStream output = socket.getOutputStream();
            writer = new PrintWriter(output, true);
        } catch (IOException error) {
            System.out.println("Error beim ausgeben: " + error.getMessage());
            error.printStackTrace();
        }
    }
}