package com.simplize.springboot.modules.consume.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.simplize.springboot.modules.consume.services.IConsumeService;
import com.simplize.springboot.models.ResponseObject;

@Service
public class ConsumeService implements IConsumeService {
    
    private final RestTemplate restTemplate;

    @Autowired
    public ConsumeService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<ResponseObject> getPosts() {
        Object result = restTemplate.getForObject("https://jsonplaceholder.typicode.com/posts", Object.class);
        
        return ResponseEntity.status(HttpStatus.OK).body(
            new ResponseObject("ok", "get posts success", result)
        );
    }

    public ResponseEntity<ResponseObject> getPostById(Long id) {
        Object result = restTemplate.getForObject("https://jsonplaceholder.typicode.com/posts/" + id, Object.class);
        
        return ResponseEntity.status(HttpStatus.OK).body(
            new ResponseObject("ok", "get getPostById success", result)
        );
    }
}
