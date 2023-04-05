package com.sevyh.sevyhchatmediaservice.service;

import com.sevyh.sevyhchatmediaservice.api.model.MediaMetadata;
import org.springframework.web.multipart.MultipartFile;

public interface MediaStorageService {

    MediaMetadata storeMediaAndGetMetadata(MultipartFile media);

}
