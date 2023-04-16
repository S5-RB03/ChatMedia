package com.sevyh.sevyhchatmediaservice.api;

import com.sevyh.sevyhchatmediaservice.api.model.Message;
import com.sevyh.sevyhchatmediaservice.api.model.MediaMetadata;
import com.sevyh.sevyhchatmediaservice.api.model.MessageType;
import com.sevyh.sevyhchatmediaservice.service.ChatMediaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.multipart.MultipartFile;

import java.time.Instant;
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
    public void createMessageWithMediaTest() throws Exception {
        // Prepare test data
        UUID senderId = UUID.randomUUID();
        UUID receiverId = UUID.randomUUID();
        String textContent = "Test message";
        MessageType messageType = MessageType.IMAGE;
        Instant timestamp = Instant.parse("2023-04-11T10:30:00Z");
        MockMultipartFile media = new MockMultipartFile("media", "test.jpg", MediaType.IMAGE_JPEG_VALUE, "image".getBytes());

        MediaMetadata mediaMetadata = new MediaMetadata(UUID.randomUUID(), UUID.randomUUID(), MediaType.IMAGE_JPEG_VALUE, 1024L, "https://example.com/test.jpg", timestamp);
        Message message = new Message(UUID.randomUUID(), null, textContent, senderId, receiverId, timestamp, messageType);

        // Configure the mock ChatMediaService
        when(chatMediaService.createMessageWithMedia(any(UUID.class), any(UUID.class), anyString(), any(Instant.class), any(MessageType.class), any(MultipartFile.class))).thenReturn(message);

        // Perform the test
        mockMvc.perform(multipart("/api/v1/chatmedia")
                .file(media)
                .param("senderId", senderId.toString())
                .param("receiverId", receiverId.toString())
                .param("textContent", textContent)
                .param("timestamp", timestamp.toString())
                .param("messageType", messageType.toString()))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.message").value("Chat message created successfully"))
                .andExpect(jsonPath("$.data.messageId").value(message.getMessageId().toString()))
                .andExpect(jsonPath("$.data.senderId").value(senderId.toString()))
                .andExpect(jsonPath("$.data.receiverId").value(receiverId.toString()))
                .andExpect(jsonPath("$.data.textContent").value(textContent));

        // Verify the interaction with the ChatMediaService
        verify(chatMediaService, times(1)).createMessageWithMedia(any(UUID.class), any(UUID.class), anyString(), any(Instant.class), any(MessageType.class), any(MultipartFile.class));
    }

    @Test
    public void getMessageByIdTest() throws Exception {
        // Prepare test data
        UUID messageId = UUID.randomUUID();
        UUID senderId = UUID.randomUUID();
        UUID receiverId = UUID.randomUUID();
        String textContent = "Test message";
        MessageType messageType = MessageType.IMAGE;
        Instant timestamp = Instant.parse("2023-04-11T10:30:00Z");

        Message message = new Message(senderId, receiverId, textContent, timestamp, messageType);

        // Configure the mock ChatMediaService
        when(chatMediaService.getMessageById(any(UUID.class))).thenReturn(message);

        // Perform the test
        mockMvc.perform(get("/api/v1/chatmedia/{messageId}", messageId.toString()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.message").value("Chat message retrieved successfully"))
                .andExpect(jsonPath("$.data.messageId").value(message.getMessageId().toString()))
                .andExpect(jsonPath("$.data.senderId").value(senderId.toString()))
                .andExpect(jsonPath("$.data.receiverId").value(receiverId.toString()))
                .andExpect(jsonPath("$.data.textContent").value(textContent))
                .andExpect(jsonPath("$.data.timestamp").value(timestamp.toString()))
                .andExpect(jsonPath("$.data.messageType").value(messageType.toString()));

        // Verify the interaction with the ChatMediaService
        verify(chatMediaService, times(1)).getMessageById(any(UUID.class));
    }


    @Test
    public void getMediaMetadataByIdTest() throws Exception {
        // Prepare test data
        UUID mediaId = UUID.randomUUID();
        Instant timestamp = Instant.parse("2023-04-11T10:30:00Z");
        MediaMetadata mediaMetadata = new MediaMetadata(mediaId, UUID.randomUUID(), MediaType.IMAGE_JPEG_VALUE, 1024L, "https://example.com/test.jpg", timestamp);
    
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