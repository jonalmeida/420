package com.jonalmeida.project420;

public class TextMessage {
    protected String name;
    protected String message;
    protected long timestamp;
    protected boolean receipient = false;

    public TextMessage() {
        this.name = null;
        this.message = null;
        this.timestamp = 0;
    }

    public TextMessage(String name, String message, Long timestamp) {
        this.name = name;
        this.message = message;
        this.timestamp = timestamp;
    }
}
