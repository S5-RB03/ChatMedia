package com.sevyh.sevyhchatmediaservice.api.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class ChatMessage {
    
    private UUID id;
    private UUID userId;
    private String content;
    private MediaMetadata mediaMetadata;
    private LocalDateTime createdAt;

    // Empty constructor
    public ChatMessage(){

    }

    // Constructor
    public ChatMessage(UUID id, UUID userId, String content, MediaMetadata mediaMetadata, LocalDateTime createdAt) {
        this.id = id;
        this.userId = userId;
        this.content = content;
        this.mediaMetadata = mediaMetadata;
        this.createdAt = createdAt;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public MediaMetadata getMediaMetadata() {
        return mediaMetadata;
    }

    public void setMediaMetadata(MediaMetadata mediaMetadata) {
        this.mediaMetadata = mediaMetadata;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }


}
