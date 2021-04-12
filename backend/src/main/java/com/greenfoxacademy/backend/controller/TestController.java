package com.greenfoxacademy.backend.controller;

import com.greenfoxacademy.backend.model.Test;
import com.greenfoxacademy.backend.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {

    private TestService testService;

    @Autowired
    public TestController(TestService testService) {
        this.testService = testService;
    }

    @GetMapping("/test")
    public ResponseEntity<Test> test() {
        return ResponseEntity.ok(testService.hello());
    }

}
