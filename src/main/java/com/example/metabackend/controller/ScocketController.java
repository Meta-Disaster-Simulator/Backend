package com.example.metabackend.controller;


import com.example.metabackend.data.dto.StatusDTO;
import com.example.metabackend.data.dto.wsDTO.ChatDTO;
import com.example.metabackend.data.dto.wsDTO.SendChatDTO;
import com.example.metabackend.service.memberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class ScocketController {


    @Autowired
    private memberService memberService;

    @MessageMapping("/chat")
    @SendTo("/topic/chat")
    public ChatDTO sendMessage(SendChatDTO sendChatDTO) {
        ChatDTO chatDTO = new ChatDTO();
        chatDTO.setCommand(sendChatDTO.getNickname()+": "+sendChatDTO.getCommand());
        return chatDTO;
    }
    @MessageMapping("/disconnect")
    public void disconnect(String id){
        StatusDTO logoutDTO = new StatusDTO();
        logoutDTO.setId(id);
        memberService.logout(logoutDTO);
    }

}
