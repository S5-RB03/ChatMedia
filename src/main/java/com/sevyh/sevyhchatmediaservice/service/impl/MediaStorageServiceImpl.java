package com.sevyh.sevyhchatmediaservice.service.impl;

import com.sevyh.sevyhchatmediaservice.api.model.MediaMetadata;
import com.sevyh.sevyhchatmediaservice.service.MediaStorageService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service
public class MediaStorageServiceImpl implements MediaStorageService {

    @Override
    public MediaMetadata storeMediaAndGetMetadata(MultipartFile media) {
        
        // For the purpose of this example, we're not storing the media file.
        // In a real application, you should store the media file and retrieve its metadata, such as size and MIME type.

        UUID mediaId = UUID.randomUUID(); // Generate a random UUID as a media ID

        MediaMetadata mediaMetadata = new MediaMetadata();
        mediaMetadata.setId(mediaId);
        mediaMetadata.setMediaType(media.getContentType());
        mediaMetadata.setSize(media.getSize());

        // Set any other metadata fields as needed.

        return mediaMetadata;
    }
}
