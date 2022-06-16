package com.example.alfaBankTask.service;


import com.example.alfaBankTask.client.FeignGifClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class GifServiceImpl implements GifService{
    private FeignGifClient feignGifClient;
    @Value("${gif_key}")
    private String gifKey;

    @Autowired
    public GifServiceImpl(FeignGifClient feignGifClient) {
        this.feignGifClient = feignGifClient;
    }

    @Override
    public ResponseEntity<Map> getGif(String tag) {
        ResponseEntity<Map> responseEntity=feignGifClient.getGif(this.gifKey, tag);
        return responseEntity;
    }
}
