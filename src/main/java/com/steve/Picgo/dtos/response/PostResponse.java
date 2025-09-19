package com.steve.Picgo.dtos.response;

import com.steve.Picgo.dtos.AuthorDTO;
import com.steve.Picgo.dtos.MediaItemDTO;
import com.steve.Picgo.entites.MediaItem;
import com.steve.Picgo.entites.PostMetrics;
import com.steve.Picgo.entites.UserEntity;
import com.steve.Picgo.enums.PostVisibility;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PostResponse {
    UUID id;
    String caption;
    List<String> hashtags;
    List<String> mentions;
    PostVisibility visibility;
    boolean isPinned;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
    Double latitude;
    Double longitude;
    String locationName;
    AuthorDTO author;
    PostMetrics metrics;
    List<MediaItemDTO> media;
}