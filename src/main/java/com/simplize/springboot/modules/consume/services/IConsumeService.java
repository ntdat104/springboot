package com.simplize.springboot.modules.consume.services;

import org.springframework.http.ResponseEntity;

import com.simplize.springboot.models.ResponseObject;

public interface IConsumeService {
    ResponseEntity<ResponseObject> getPosts();
    ResponseEntity<ResponseObject> getPostById(Long id);
}
