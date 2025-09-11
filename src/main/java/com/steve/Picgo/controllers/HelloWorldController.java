package com.steve.Picgo.controllers;

import com.steve.Picgo.dtos.response.ApiResponse;
import com.steve.Picgo.dtos.request.TestCreationRequest;
import com.steve.Picgo.entites.Test;
import com.steve.Picgo.services.TestService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class HelloWorldController {

    TestService testService;

    @GetMapping("")
    public String test(){
        return "test";
    }

    @PostMapping("")
    public ApiResponse<Test> createTest(@RequestBody @Valid TestCreationRequest test){
        ApiResponse<Test> response = new ApiResponse<>();
        response.setData(testService.createRequestTest(test));
        return response;
    }

    @GetMapping("/get-all-test")
    public List<Test> getAllTest(){
        return testService.getTests();
    }

    @GetMapping("/find-by-id/{testId}")
    public Test getTestById(@PathVariable("testId") String testId){
        return testService.getTestById(testId);
    }

    @PutMapping("/update-test/{testId}")
    public Test updateTest(
            @PathVariable String testId,
            @RequestBody TestCreationRequest test
    ){
        return testService.updateTestById(testId, test);
    }

    @DeleteMapping("/{testId}")
    public Boolean deleteTest(@PathVariable("testId") String testId){
        return testService.deleteTestById(testId);
    }
}
