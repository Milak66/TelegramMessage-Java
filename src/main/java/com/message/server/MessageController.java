package com.message.server;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping("/sendMessage")
    public ResponseEntity<String> sendMessage(@RequestBody Message message) {

        if (message.getMessage() == null ||
                message.getMessage().trim().isEmpty()) {
            return ResponseEntity.badRequest()
                    .body("Message cannot be empty");
        }

        try {
            messageService.sendMessage(message.getMessage());

            return ResponseEntity.ok("Message sent");

        } catch (Exception e) {

            System.err.println("=== TELEGRAM ERROR ===");
            e.printStackTrace();
            System.err.println("======================");

            return ResponseEntity.internalServerError()
                    .body("Failed to send message: " + e.getMessage());
        }
    }
}