package com.steve.Picgo.mapper;

import com.steve.Picgo.dtos.request.TestCreationRequest;
import com.steve.Picgo.entites.Test;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TestMapper {
    Test toTest(TestCreationRequest testCreationRequest);
}
