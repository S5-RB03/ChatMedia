package com.sevyh.sevyhchatmediaservice.api;

import com.sevyh.sevyhchatmediaservice.api.model.ApiResponse;
import com.sevyh.sevyhchatmediaservice.api.model.ChatMessage;
import com.sevyh.sevyhchatmediaservice.api.model.MediaMetadata;
import com.sevyh.sevyhchatmediaservice.service.ChatMediaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/chatmedia")
public class ChatMediaController {

    private final ChatMediaService chatMediaService;

    public ChatMediaController(ChatMediaService chatMediaService) {
        this.chatMediaService = chatMediaService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ChatMessage>> createChatMessageWithMedia(
            @RequestParam("userId") UUID userId,
            @RequestParam("content") String content,
            @RequestParam("media") MultipartFile media,
            @RequestParam("createdAt") String createdAt) {
        ChatMessage chatMessage = chatMediaService.createChatMessageWithMedia(userId, content, media, createdAt);
        ApiResponse<ChatMessage> response = new ApiResponse<>(true, "Chat message created successfully", chatMessage);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{messageId}")
    public ResponseEntity<ApiResponse<ChatMessage>> getChatMessageById(@PathVariable UUID messageId) {
        ChatMessage chatMessage = chatMediaService.getChatMessageById(messageId);
        ApiResponse<ChatMessage> response = new ApiResponse<>(true, "Chat message retrieved successfully", chatMessage);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/media/{mediaId}")
    public ResponseEntity<ApiResponse<MediaMetadata>> getMediaMetadataById(@PathVariable UUID mediaId) {
        MediaMetadata mediaMetadata = chatMediaService.getMediaMetadataById(mediaId);
        ApiResponse<MediaMetadata> response = new ApiResponse<>(true, "Media metadata retrieved successfully", mediaMetadata);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
