package com.sevyh.sevyhchatmediaservice.api;

import com.sevyh.sevyhchatmediaservice.api.model.ApiResponse;
import com.sevyh.sevyhchatmediaservice.api.model.Message;
import com.sevyh.sevyhchatmediaservice.api.model.MessageType;
import com.sevyh.sevyhchatmediaservice.api.model.MediaMetadata;
import com.sevyh.sevyhchatmediaservice.service.ChatMediaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.Instant;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/chatmedia")
public class ChatMediaController {

    private final ChatMediaService chatMediaService;

    public ChatMediaController(ChatMediaService chatMediaService) {
        this.chatMediaService = chatMediaService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Message>> createMessageWithMedia(
            @RequestParam("senderId") UUID senderId,
            @RequestParam("receiverId") UUID receiverId,
            @RequestParam("textContent") String textContent,
            @RequestParam("messageType") MessageType messageType,
            @RequestParam("media") MultipartFile media) {
        Instant timestamp = Instant.now();
        Message Message = chatMediaService.createMessageWithMedia(senderId, receiverId, textContent, timestamp, messageType, media);
        ApiResponse<Message> response = new ApiResponse<>(true, "Chat message created successfully", Message);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{messageId}")
    public ResponseEntity<ApiResponse<Message>> getMessageById(@PathVariable UUID messageId) {
        Message Message = chatMediaService.getMessageById(messageId);
        ApiResponse<Message> response = new ApiResponse<>(true, "Chat message retrieved successfully", Message);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/media/{mediaId}")
    public ResponseEntity<ApiResponse<MediaMetadata>> getMediaMetadataById(@PathVariable UUID mediaId) {
        MediaMetadata mediaMetadata = chatMediaService.getMediaMetadataById(mediaId);
        ApiResponse<MediaMetadata> response = new ApiResponse<>(true, "Media metadata retrieved successfully", mediaMetadata);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
