package com.example.gemini.controller;




import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
 
@RestController
public class ChatController {
 
    private final ChatClient chatClient;
 
    public ChatController(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }
 
    @GetMapping("/chat/{message}")
    public ChatResponse chat(
            @PathVariable String message) {
        var reply = chatClient
                .prompt()
                .user(message)
                .call()
                .content();
        
        System.out.println(reply);
 
        return new ChatResponse(message, reply);
    }
 
    public record ChatResponse(String prompt, String reply) {}
}