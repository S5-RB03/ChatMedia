package com.sevyh.sevyhchatmediaservice.api;

import com.sevyh.sevyhchatmediaservice.api.model.ChatMessage;
import com.sevyh.sevyhchatmediaservice.api.model.MediaMetadata;
import com.sevyh.sevyhchatmediaservice.service.ChatMediaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ChatMediaController.class)
public class ChatMediaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ChatMediaService chatMediaService;

    @Test
    public void createChatMessageWithMediaTest() throws Exception {
        // Prepare test data
        UUID userId = UUID.randomUUID();
        String content = "Test message";
        MockMultipartFile media = new MockMultipartFile("media", "test.jpg", MediaType.IMAGE_JPEG_VALUE, "image".getBytes());
        LocalDateTime createdAt = LocalDateTime.parse("2023-04-11T10:30:00");

        MediaMetadata mediaMetadata = new MediaMetadata(UUID.randomUUID(), MediaType.IMAGE_JPEG_VALUE, 1024L, "https://example.com/test.jpg");
        ChatMessage chatMessage = new ChatMessage(UUID.randomUUID(), userId, content, mediaMetadata, createdAt);

        // Configure the mock ChatMediaService
        when(chatMediaService.createChatMessageWithMedia(any(UUID.class), anyString(), any(MultipartFile.class), any(LocalDateTime.class))).thenReturn(chatMessage);

        // Perform the test
        mockMvc.perform(multipart("/api/v1/chatmedia")
                .file(media)
                .param("userId", userId.toString())
                .param("content", content)
                .param("createdAt", createdAt.toString()))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.message").value("Chat message created successfully"))
                .andExpect(jsonPath("$.data.id").value(chatMessage.getId().toString()))
                .andExpect(jsonPath("$.data.mediaMetadata.mediaType").value(MediaType.IMAGE_JPEG_VALUE))
                .andExpect(jsonPath("$.data.mediaMetadata.size").value(1024L))
                .andExpect(jsonPath("$.data.mediaMetadata.url").value("https://example.com/test.jpg"));

        // Verify the interaction with the ChatMediaService
        verify(chatMediaService, times(1)).createChatMessageWithMedia(any(UUID.class), anyString(), any(MultipartFile.class), any(LocalDateTime.class));
    }
    @Test
    public void getChatMessageByIdTest() throws Exception {
        // Prepare test data
        UUID messageId = UUID.randomUUID();
        UUID userId = UUID.randomUUID();
        String content = "Test message";
        MediaMetadata mediaMetadata = new MediaMetadata(UUID.randomUUID(), MediaType.IMAGE_JPEG_VALUE, 1024L, "https://example.com/test.jpg");
        LocalDateTime createdAt = LocalDateTime.parse("2023-04-11T10:30:00");
        ChatMessage chatMessage = new ChatMessage(messageId, userId, content, mediaMetadata, createdAt);
    
        // Configure the mock ChatMediaService
        when(chatMediaService.getChatMessageById(any(UUID.class))).thenReturn(chatMessage);
    
        // Perform the test
        mockMvc.perform(get("/api/v1/chatmedia/{messageId}", messageId.toString()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.message").value("Chat message retrieved successfully"))
                .andExpect(jsonPath("$.data.id").value(chatMessage.getId().toString()))
                .andExpect(jsonPath("$.data.userId").value(userId.toString()))
                .andExpect(jsonPath("$.data.content").value(content))
                .andExpect(jsonPath("$.data.mediaMetadata.id").value(mediaMetadata.getId().toString()))
                .andExpect(jsonPath("$.data.mediaMetadata.mediaType").value(MediaType.IMAGE_JPEG_VALUE))
                .andExpect(jsonPath("$.data.mediaMetadata.size").value(1024L))
                .andExpect(jsonPath("$.data.mediaMetadata.url").value("https://example.com/test.jpg"));
    
        // Verify the interaction with the ChatMediaService
        verify(chatMediaService, times(1)).getChatMessageById(any(UUID.class));
    }

    @Test
    public void getMediaMetadataByIdTest() throws Exception {
        // Prepare test data
        UUID mediaId = UUID.randomUUID();
        MediaMetadata mediaMetadata = new MediaMetadata(mediaId, MediaType.IMAGE_JPEG_VALUE, 1024L, "https://example.com/test.jpg");
    
        // Configure the mock ChatMediaService
        when(chatMediaService.getMediaMetadataById(any(UUID.class))).thenReturn(mediaMetadata);
    
        // Perform the test
        mockMvc.perform(get("/api/v1/chatmedia/media/{mediaId}", mediaId.toString()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.message").value("Media metadata retrieved successfully"))
                .andExpect(jsonPath("$.data.id").value(mediaMetadata.getId().toString()))
                .andExpect(jsonPath("$.data.mediaType").value(MediaType.IMAGE_JPEG_VALUE))
                .andExpect(jsonPath("$.data.size").value(1024L))
                .andExpect(jsonPath("$.data.url").value("https://example.com/test.jpg"));
    
        // Verify the interaction with the ChatMediaService
        verify(chatMediaService, times(1)).getMediaMetadataById(any(UUID.class));
    }
}