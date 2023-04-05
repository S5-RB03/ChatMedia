package com.sevyh.sevyhchatmediaservice.service.impl;

import com.sevyh.sevyhchatmediaservice.api.model.ChatMessage;
import com.sevyh.sevyhchatmediaservice.api.model.MediaMetadata;
import com.sevyh.sevyhchatmediaservice.repository.ChatMessageRepository;
import com.sevyh.sevyhchatmediaservice.repository.MediaMetadataRepository;
import com.sevyh.sevyhchatmediaservice.service.ChatMediaService;
import com.sevyh.sevyhchatmediaservice.service.MediaStorageService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class ChatMediaServiceImpl implements ChatMediaService {

    private final ChatMessageRepository chatMessageRepository;
    private final MediaMetadataRepository mediaMetadataRepository;
    private final MediaStorageService mediaStorageService;

    public ChatMediaServiceImpl(ChatMessageRepository chatMessageRepository,
                                 MediaMetadataRepository mediaMetadataRepository,
                                 MediaStorageService mediaStorageService) {
        this.chatMessageRepository = chatMessageRepository;
        this.mediaMetadataRepository = mediaMetadataRepository;
        this.mediaStorageService = mediaStorageService;
    }

    @Override
    public ChatMessage createChatMessageWithMedia(UUID userId, String content, MultipartFile media, String createdAt) {
        // Save the media and get its metadata
        MediaMetadata mediaMetadata = mediaStorageService.storeMediaAndGetMetadata(media);

        // Save the media metadata in the database
        mediaMetadataRepository.save(mediaMetadata);

        // Create a new chat message
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setUserId(userId);
        chatMessage.setContent(content);
        chatMessage.setMediaMetadata(mediaMetadata);
        chatMessage.setCreatedAt(LocalDateTime.parse(createdAt));


        // Save the chat message in the database
        chatMessageRepository.save(chatMessage);

        return chatMessage;
    }

    @Override
    public ChatMessage getChatMessageById(UUID messageId) {
        return chatMessageRepository.findById(messageId).orElse(null);
    }

    @Override
    public MediaMetadata getMediaMetadataById(UUID mediaId) {
        return mediaMetadataRepository.findById(mediaId).orElse(null);
    }

}
