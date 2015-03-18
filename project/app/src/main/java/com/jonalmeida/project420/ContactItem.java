package com.jonalmeida.project420;

public class ContactItem {
    private String name, lastConversationLine;
    private String profileImage; // String?
    private String dominantColor; // To set the color of the conversation window

    public ContactItem(String name, String lastConversationLine, String profileImage) {
        this.name = name;
        this.lastConversationLine = lastConversationLine;
        this.profileImage = profileImage;
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
}
