/*
package com.example.metabackend.controller;


import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class ScocketController {

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting sendMessage(HelloMessage helloMessage) {
        Greeting g =  new Greeting();
        g.setContent(helloMessage.getName());
        try {
            Thread.sleep(3000); //1초 대기
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return g;
    }
}
*/
