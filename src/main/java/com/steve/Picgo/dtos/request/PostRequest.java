package com.steve.Picgo.dtos.request;

import com.steve.Picgo.dtos.MediaItemDTO;
import com.steve.Picgo.enums.PostVisibility;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PostRequest {
    String caption;
    List<String> hashtags;
    List<String> mentions;
    PostVisibility visibility;
    Double latitude;
    Double longitude;
    String locationName;
    List<MediaItemRequest> media;
}