package com.example.metabackend.controller;


import com.example.metabackend.data.dto.wsDTO.ChatDTO;
import com.example.metabackend.data.dto.wsDTO.SendChatDTO;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class ScocketController {

    @MessageMapping("/chat")
    @SendTo("/topic/chat")
    public ChatDTO sendMessage(SendChatDTO sendChatDTO) {
        ChatDTO chatDTO = new ChatDTO();
        chatDTO.setCommand(sendChatDTO.getNickname()+": "+sendChatDTO.getCommand());
        return chatDTO;
    }
}
