package dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class Message {
    private String userName;
    private String text;
    private LocalDateTime receivedDate;

    public Message() {
    }

    public Message(String userName, String text, LocalDateTime receivedDate) {
        this.userName = userName;
        this.text = text;
        this.receivedDate = receivedDate;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getReceivedDate() {
        return receivedDate;
    }

    public void setReceivedDate(LocalDateTime receivedDate) {
        this.receivedDate = receivedDate;
    }

    @Override
    public String toString() {
        return "Message{" +
                "userName='" + userName + '\'' +
                ", text='" + text + '\'' +
                ", receivedDate=" + receivedDate +
                '}';
    }
}
