package com.jonalmeida.project420;

public class ContactItem {
    private String name, lastConversationLine;
    private String profileImage; // String?
    private String dominantColor; // To set the color of the conversation window
    private int thread_id;
    private String address; // Phone number

    public ContactItem(String name, String lastConversationLine, String profileImage) {
        this.name = name;
        this.lastConversationLine = lastConversationLine;
        this.profileImage = profileImage;
    }

    public ContactItem(String name, String lastConversationLine, String address, int thread_id) {
        this.name = name;
        this.lastConversationLine = lastConversationLine;
        this.address = address;
        this.thread_id = thread_id;
    }

    public ContactItem(String name, String lastConversationLine) {
        this.name = name;
        this.lastConversationLine = lastConversationLine;
    }

    public String getDominantColor() {
        return dominantColor;
    }

    public void setDominantColor(String dominantColor) {
        this.dominantColor = dominantColor;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public String getName() {
        return name;
    }

    public String getLastConversationLine() {
        return lastConversationLine;
    }

    public int getThread_id() {
        return thread_id;
    }

    public String getAddress() {
        return address;
    }
}
