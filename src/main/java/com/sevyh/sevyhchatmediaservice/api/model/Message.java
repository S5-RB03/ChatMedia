package com.sevyh.sevyhchatmediaservice.api.model;

import java.time.Instant;
import java.util.UUID;

public class Message {

    private UUID id;
    private UUID conversationId; 
    private String textContent;
    private UUID senderId;
    private UUID receiverId;
    private Instant timestamp;
    private MessageType messageType;

    public Message() {
    }

    public Message(UUID senderId, UUID receiverId, String textContent, Instant timestamp, MessageType messageType) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.textContent = textContent;
        this.timestamp = timestamp;
        this.messageType = messageType;
    }

    public Message(UUID id, UUID conversationId, String textContent, UUID senderId, UUID receiverId, Instant timestamp, MessageType messageType) {
        this.id = id;
        this.conversationId = conversationId;
        this.textContent = textContent;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.timestamp = timestamp;
        this.messageType = messageType;
    }

    public UUID getMessageId() {
        return id;
    }

    public void setMessageId(UUID id) {
        this.id = id;
    }

    public UUID getConversationId() {
        return conversationId;
    }

    public void setConversationId(UUID conversationId) {
        this.conversationId = conversationId;
    }

    public String getTextContent() {
        return textContent;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }

    public UUID getSenderId() {
        return senderId;
    }

    public void setSenderId(UUID senderId) {
        this.senderId = senderId;
    }

    public UUID getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(UUID reveiverId) {
        this.receiverId = reveiverId;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }

}
