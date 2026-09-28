package com.message.server;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.Map;

@Service
public class MessageService {

    private final RestClient restClient;

    @Value("${telegram.bot.token}")
    private String botToken;

    @Value("${telegram.chat.id}")
    private String chatId;

    public MessageService() {
        this.restClient = RestClient.create();
    }

    public void sendMessage(String message) {

        String url = "https://api.telegram.org/bot"
                + botToken
                + "/sendMessage";

        restClient.post()
                .uri(url)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Map.of(
                        "chat_id", chatId,
                        "text", message
                ))
                .retrieve()
                .toBodilessEntity();
    }
}