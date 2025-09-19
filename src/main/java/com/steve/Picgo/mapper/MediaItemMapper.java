package com.steve.Picgo.mapper;

import com.steve.Picgo.dtos.MediaItemDTO;
import com.steve.Picgo.dtos.request.MediaItemRequest;
import com.steve.Picgo.entites.MediaItem;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MediaItemMapper {
    MediaItem toMediaItem(MediaItemRequest request);

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "type", source = "type")
    @Mapping(target = "url", source = "url")
    MediaItemDTO toMediaItemDTO(MediaItem mediaItem);
}