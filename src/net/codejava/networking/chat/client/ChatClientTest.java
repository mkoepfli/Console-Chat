package net.codejava.networking.chat.client;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChatClientTest {

    @Test
    void getHostname() {
        String expected = "localhost";
        ChatClient client = new ChatClient("localhost", 5464);
        assertEquals(expected, client.getHostname());
    }

    @Test
    void getPort() {
        int expected = 5464;
        ChatClient client = new ChatClient("localhost", 5464);
        assertEquals(expected, client.getPort());
    }
}