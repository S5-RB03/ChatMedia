package com.sevyh.sevyhchatmediaservice.repository;

import com.sevyh.sevyhchatmediaservice.api.model.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, UUID> {
}
