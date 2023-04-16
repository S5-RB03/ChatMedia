package com.sevyh.sevyhchatmediaservice.service.impl;

import com.sevyh.sevyhchatmediaservice.api.model.MediaMetadata;
import com.sevyh.sevyhchatmediaservice.repository.MediaMetadataRepository;
import com.sevyh.sevyhchatmediaservice.service.MediaStorageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.ByteBuffer;
import java.time.Instant;
import java.util.UUID;

@Service
public class MediaStorageServiceImpl implements MediaStorageService {

    @Autowired
    private MediaMetadataRepository mediaMetadataRepository;

    @Override
    public MediaMetadata storeMediaAndGetMetadata(MultipartFile media, UUID senderId, UUID receiverId) {
        
        UUID mediaId = UUID.randomUUID(); // Generate a random UUID as a media ID
        String mediaType = media.getContentType();
        Long mediaSize = media.getSize();

        // TODO: Store the media in a storage service
        String fileLocation = "https://placekitten.com/200/300";

        Instant timestamp = Instant.now();

        UUID conversationId = this.generateConversationId(mediaId, mediaId);

        MediaMetadata mediaMetadata = new MediaMetadata(mediaId, conversationId, mediaType, mediaSize, fileLocation, timestamp);

        // Save the media metadata in the database
        mediaMetadataRepository.save(mediaMetadata);

        return mediaMetadata;
    }

    private UUID generateConversationId(UUID senderId, UUID receiverId) {
        ByteBuffer buffer = ByteBuffer.allocate(32);
        
        UUID firstId, secondId;
        if (senderId.compareTo(receiverId) < 0) {
            firstId = senderId;
            secondId = receiverId;
        } else {
            firstId = receiverId;
            secondId = senderId;
        }
    
        buffer.putLong(firstId.getMostSignificantBits());
        buffer.putLong(firstId.getLeastSignificantBits());
        buffer.putLong(secondId.getMostSignificantBits());
        buffer.putLong(secondId.getLeastSignificantBits());
    
        byte[] combinedBytes = buffer.array();
        UUID conversationId = UUID.nameUUIDFromBytes(combinedBytes);
        return conversationId;
    }
}
