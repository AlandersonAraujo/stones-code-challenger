package br.com.alphablack.stone.exception;

public class MemcachedInitializationException extends RuntimeException {

    public MemcachedInitializationException(String message) {
        super(message);
    }

    public MemcachedInitializationException(String message, Throwable cause) {
        super(message, cause);
    }
}
