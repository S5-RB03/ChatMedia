package com.sevyh.sevyhchatmediaservice.service;

import com.sevyh.sevyhchatmediaservice.api.model.ChatMessage;
import com.sevyh.sevyhchatmediaservice.api.model.MediaMetadata;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.UUID;

public interface ChatMediaService {

    ChatMessage createChatMessageWithMedia(UUID userId, String content, MultipartFile media, LocalDateTime createdAt);

    ChatMessage getChatMessageById(UUID messageId);

    MediaMetadata getMediaMetadataById(UUID mediaId);

}
