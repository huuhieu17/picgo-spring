package com.steve.Picgo.mapper;

import com.steve.Picgo.dtos.request.PostRequest;
import com.steve.Picgo.dtos.response.PostResponse;
import com.steve.Picgo.entites.Post;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = {MediaItemMapper.class})
public interface PostMapper {
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "caption", source = "caption")
    @Mapping(target = "hashtags", source = "hashtags")
    @Mapping(target = "mentions", source = "mentions")
    @Mapping(target = "visibility", source = "visibility")
    @Mapping(target = "latitude", source = "latitude")
    @Mapping(target = "longitude", source = "longitude")
    @Mapping(target = "locationName", source = "locationName")
    @Mapping(target = "media", source = "media")
    Post toPostEntity(PostRequest postRequest);

    @Mapping(target = "caption", source = "caption")
    @Mapping(target = "hashtags", source = "hashtags")
    @Mapping(target = "mentions", source = "mentions")
    @Mapping(target = "visibility", source = "visibility")
    @Mapping(target = "latitude", source = "latitude")
    @Mapping(target = "longitude", source = "longitude")
    @Mapping(target = "locationName", source = "locationName")
    @Mapping(target = "media", source = "media")
    PostResponse toPostResponse(Post post);
}
