package com.simplize.springboot.consume.services;

import org.springframework.http.ResponseEntity;

import com.simplize.springboot.models.ResponseObject;

public interface IConsumeService {
    ResponseEntity<ResponseObject> getPosts();
}
