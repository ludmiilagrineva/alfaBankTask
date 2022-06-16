package com.example.alfaBankTask.service;

import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface GifService {
    ResponseEntity <Map> getGif(String tag);
}
