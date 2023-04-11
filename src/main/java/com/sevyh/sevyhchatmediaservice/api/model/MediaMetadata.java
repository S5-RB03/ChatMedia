package com.sevyh.sevyhchatmediaservice.api.model;

import java.util.UUID;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Table("media_metadata")
public class MediaMetadata {

    @PrimaryKey
    private UUID id;
    private String mediaType;
    private long size;
    private String url;

    public MediaMetadata() {
    }

    public MediaMetadata(UUID id, String mediaType, long size, String url) {
        this.id = id;
        this.mediaType = mediaType;
        this.size = size;
        this.url = url;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
