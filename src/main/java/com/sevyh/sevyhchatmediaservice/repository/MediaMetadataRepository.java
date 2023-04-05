package com.sevyh.sevyhchatmediaservice.repository;

import com.sevyh.sevyhchatmediaservice.api.model.MediaMetadata;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MediaMetadataRepository extends JpaRepository<MediaMetadata, UUID> {
}
