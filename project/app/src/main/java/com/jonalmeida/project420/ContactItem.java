package com.jonalmeida.project420;

public class ContactItem {
    private String displayName, lastConversationLine;
    private String profileImage; // String?
    private String personId;
    private String dominantColor; // To set the color of the conversation window
    private int thread_id;
    private String address; // Phone number

    public ContactItem(String displayName, String lastConversationLine, String address, int thread_id) {
        this.displayName = displayName;
        this.lastConversationLine = lastConversationLine;
        this.address = address;
        this.thread_id = thread_id;
    }

    public ContactItem(String displayName, String personId, String lastConversationLine) {
        this.displayName = displayName;
        this.personId = personId;
        this.lastConversationLine = lastConversationLine;
    }

    public ContactItem(String displayName, String lastConversationLine) {
        this.displayName = displayName;
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

    public String getDisplayName() {
        return displayName;
    }

    public String getLastConversationLine() {
        return lastConversationLine;
    }

    public int getThreadId() {
        return thread_id;
    }

    public String getAddress() {
        return address;
    }

    public String getPersonId() {
        return personId;
    }

    public String toString() {
        return "displayName: " + displayName + " personId: " + personId + " threadId: " + thread_id;
    }
}
