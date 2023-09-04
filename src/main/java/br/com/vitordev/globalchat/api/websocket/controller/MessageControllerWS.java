package br.com.vitordev.globalchat.api.websocket.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.vitordev.globalchat.api.request.MessagePostRequest;
import br.com.vitordev.globalchat.api.response.MessageResponse;
import br.com.vitordev.globalchat.api.response.UserResponse;
import br.com.vitordev.globalchat.domain.service.MessageService;
import br.com.vitordev.globalchat.domain.service.PongService;
import br.com.vitordev.globalchat.domain.service.UserService;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class MessageControllerWS {
    @Autowired
    private UserService userService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private PongService pongService;
    
    @MessageMapping("/global.send")
    @SendTo("/topic/global")
    public MessageResponse receiveMessage(@Payload MessagePostRequest request) {
        log.info(request.toString());
        log.info(request.content());
        return messageService.create(request);
    }

    @MessageMapping("/global.join")
    @SendTo("/topic/global")
    public void addUser(@Payload String userId, SimpMessageHeaderAccessor headerAccessor) {
        UserResponse user = findUserById(userId);
        headerAccessor.getSessionAttributes().put("username", user.username());
    }

    @MessageMapping("/queque.latency")
    @SendTo("/queue/{userId}")
    public double responseConnectionLatency(@PathVariable String userId) {
        findUserById(userId);   
        return pongService.pong();
    }


    private UserResponse findUserById(String userId) {
        UUID id = UUID.fromString(userId);
        return userService.show(id);
    } 
}
