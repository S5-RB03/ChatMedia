package com.sevyh.sevyhchatmediaservice.api.model;

import java.util.UUID;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Table("media_metadata")
public class MediaMetadata {

    @PrimaryKey
    private UUID id;
    private UUID conversationId;
    private String mediaType;
    private long size;
    private String fileLocation;

    public MediaMetadata() {
    }

    public MediaMetadata(UUID id, String mediaType, long size, String fileLocation) {
        this.id = id;
        this.mediaType = mediaType;
        this.size = size;
        this.fileLocation = fileLocation;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getConversationId() {
        return conversationId;
    }

    public void setConversationId(UUID conversationId) {
        this.conversationId = conversationId;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getfileLocation() {
        return fileLocation;
    }

    public void setfileLocation(String fileLocation) {
        this.fileLocation = fileLocation;
    }

}
