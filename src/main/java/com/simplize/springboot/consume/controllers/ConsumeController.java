package com.simplize.springboot.consume.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.simplize.springboot.consume.services.impl.ConsumeService;
import com.simplize.springboot.models.ResponseObject;

@RestController
@RequestMapping(path = "/api/v1/consumes")
public class ConsumeController {

    private final ConsumeService consumeService;

    @Autowired
    public ConsumeController(ConsumeService consumeService) {
        this.consumeService = consumeService;
    }
    
    @GetMapping(path = "/posts")
    public ResponseEntity<ResponseObject> getPosts() {
        return consumeService.getPosts();
    }
}
