package com.example.demo.infrastructure.rest_controler.openai;

import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.image.ImageClient;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class ImagesController {


    private final ImageClient imageClient;

    public ImagesController(ImageClient imageClient) {
        this.imageClient = imageClient;
    }


    @GetMapping("/generate")
    public String getUrlImage(@RequestParam("text") String text) {
        return this.imageClient.call(new ImagePrompt(text)).getResult().getOutput().getUrl();
    }
}
