package com.greenfoxacademy.backend.service;

import com.greenfoxacademy.backend.model.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TestService {

    public Test hello() {
        Test test = new Test();
        test.setMessage("hello");
        return test;
    }

}
