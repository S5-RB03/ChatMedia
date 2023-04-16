package com.sevyh.sevyhchatmediaservice.api.model;

import java.time.Instant;
import java.util.UUID;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Table("media_metadata")
public class MediaMetadata {

    @PrimaryKey("media_id")
    private UUID id;
    @Column("conversation_id")
    private UUID conversationId;
    @Column("media_type")
    private String mediaType;
    @Column("size")
    private long size;
    @Column("file_location")
    private String fileLocation;
    @Column("timestamp")
    private Instant timestamp;

    public MediaMetadata() {
    }

    public MediaMetadata(UUID id, UUID conversationId, String mediaType, long size, String fileLocation, Instant timestamp) {
        this.id = id;
        this.conversationId = conversationId;
        this.mediaType = mediaType;
        this.size = size;
        this.fileLocation = fileLocation;
        this.timestamp = timestamp;
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

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

}
