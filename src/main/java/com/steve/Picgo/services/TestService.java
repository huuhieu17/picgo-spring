package com.steve.Picgo.services;

import com.steve.Picgo.dtos.request.TestCreationRequest;
import com.steve.Picgo.entites.Test;
import com.steve.Picgo.exceptions.AppException;
import com.steve.Picgo.mapper.TestMapper;
import com.steve.Picgo.repositories.TestRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,  makeFinal = true)
public class TestService {

    TestRepository testRepository;
    TestMapper testMapper;

    public Test createRequestTest(TestCreationRequest request) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        request.setName(passwordEncoder.encode(request.getName()));
        if(testRepository.existsTestByEmail(request.getEmail())){
            throw new AppException(HttpStatus.BAD_REQUEST, "Email Already Exists");
        }
        Test testData = testMapper.toTest(request);

        return testRepository.save(testData);
    }

    public List<Test> getTests() {
        return testRepository.findAll();
    }

    public Test getTestById(String id) {
        Optional<Test> data = testRepository.findById(id);
        if (data.isPresent()) {
            return data.get();
        }
        throw new RuntimeException("User not found");
    }

    public Test updateTestById(String id, TestCreationRequest request) {
        Optional<Test> data = testRepository.findById(id);
        if (data.isPresent()) {
            Test testData = data.get();
            testData.setName(request.getName());
            testData.setEmail(request.getEmail());
            testData.setPhone(request.getPhone());
            return testRepository.save(testData);
        }
        return null;
    }

    public Boolean deleteTestById(String id) {
        Optional<Test> data = testRepository.findById(id);
        if (data.isPresent()) {
            testRepository.deleteById(id);
        }
        throw new RuntimeException("User not found");
    }
}
