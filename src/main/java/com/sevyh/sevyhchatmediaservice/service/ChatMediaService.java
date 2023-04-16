package com.sevyh.sevyhchatmediaservice.service;

import com.sevyh.sevyhchatmediaservice.api.model.Message;
import com.sevyh.sevyhchatmediaservice.api.model.MessageType;
import com.sevyh.sevyhchatmediaservice.api.model.MediaMetadata;
import org.springframework.web.multipart.MultipartFile;

import java.time.Instant;
import java.util.UUID;

public interface ChatMediaService {

    Message createMessageWithMedia(UUID senderId, UUID receiverId, String textContent, Instant timestamp, MessageType messageType, MultipartFile media);

    Message getMessageById(UUID messageId);

    MediaMetadata getMediaMetadataById(UUID mediaId);
    
}
