package com.steve.Picgo.dtos.request;

import com.steve.Picgo.enums.MediaType;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MediaItemRequest {
    MediaType type;

    String url;
}
