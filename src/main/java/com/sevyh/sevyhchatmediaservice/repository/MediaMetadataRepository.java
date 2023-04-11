package com.sevyh.sevyhchatmediaservice.repository;

import com.sevyh.sevyhchatmediaservice.api.model.MediaMetadata;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MediaMetadataRepository extends CassandraRepository<MediaMetadata, UUID> {
}
