package com.sevyh.sevyhchatmediaservice.service.impl;

import com.sevyh.sevyhchatmediaservice.api.model.Message;
import com.sevyh.sevyhchatmediaservice.api.model.MessageType;
import com.sevyh.sevyhchatmediaservice.messaging.RabbitMQSender;
import com.sevyh.sevyhchatmediaservice.api.model.MediaMetadata;
import com.sevyh.sevyhchatmediaservice.repository.MessageRepository;
import com.sevyh.sevyhchatmediaservice.repository.MediaMetadataRepository;
import com.sevyh.sevyhchatmediaservice.service.ChatMediaService;
import com.sevyh.sevyhchatmediaservice.service.MediaStorageService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.Instant;
import java.util.UUID;

@Service
public class ChatMediaServiceImpl implements ChatMediaService {

    private final MessageRepository MessageRepository;
    private final MediaMetadataRepository mediaMetadataRepository;
    private final MediaStorageService mediaStorageService;
    private final RabbitMQSender rabbitMQSender;

    public ChatMediaServiceImpl(MessageRepository MessageRepository,
                                 MediaMetadataRepository mediaMetadataRepository,
                                 MediaStorageService mediaStorageService,
                                 RabbitMQSender rabbitMQSender) {
        this.MessageRepository = MessageRepository;
        this.mediaMetadataRepository = mediaMetadataRepository;
        this.mediaStorageService = mediaStorageService;
        this.rabbitMQSender = rabbitMQSender;
    }

    @Override
    public Message createMessageWithMedia(UUID senderId, UUID receiverId, String textContent, Instant timestamp, MessageType messageType, MultipartFile media) {
        // Create a new chat message
        Message message = new Message(
            senderId,
            receiverId,
            textContent,
            timestamp,
            messageType
        );

        // generate a random UUID for the message
        message.setMessageId(UUID.randomUUID());

        // Send the message to the message queue with RabbitMQSender
        rabbitMQSender.send(message);

        // Save the media and get its metadata
        MediaMetadata mediaMetadata = mediaStorageService.storeMediaAndGetMetadata(media, senderId, receiverId);

        // Save the media metadata in the database
        mediaMetadataRepository.save(mediaMetadata);

        return message;
    }

    @Override
    public Message getMessageById(UUID messageId) {
        return MessageRepository.findById(messageId).orElse(null);
    }

    @Override
    public MediaMetadata getMediaMetadataById(UUID mediaId) {
        return mediaMetadataRepository.findById(mediaId).orElse(null);
    }
    
}
