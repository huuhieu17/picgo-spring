package com.steve.Picgo.dtos;

import com.steve.Picgo.dtos.response.PostResponse;
import com.steve.Picgo.enums.MediaType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MediaItemDTO {
    UUID id;

    MediaType type;

    String url;
}
