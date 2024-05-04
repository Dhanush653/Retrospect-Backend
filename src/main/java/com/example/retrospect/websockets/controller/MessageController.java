package com.example.retrospect.websockets.controller;


import com.example.retrospect.websockets.entity.Message;
import com.example.retrospect.websockets.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/message")

@RequiredArgsConstructor
@CrossOrigin(allowedHeaders = "*")
public class MessageController {

    private final MessageService messageService;

    @CrossOrigin("*")
    @GetMapping("/{room}")
    public ResponseEntity<List<Message>>getMessages(@PathVariable String room) {
        return ResponseEntity.ok(messageService.getAllMessages(room));
    }
}
