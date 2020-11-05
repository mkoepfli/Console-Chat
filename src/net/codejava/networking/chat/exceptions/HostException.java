package net.codejava.networking.chat.exceptions;

public class HostException extends RuntimeException {
    public HostException(String errorMessage, Throwable error) {
        super(errorMessage, error);
    }
}