package br.com.vitordev.globalchat.infra.config.webscoket;

import org.springframework.context.event.EventListener;
// import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class WebSocketEventListener {
   // private SimpMessageSendingOperations messageTemplate;

    // @Deprecated
    // public WebSocketEventListener(SimpMessageSendingOperations messageTemplate) {
    //     this.messageTemplate = messageTemplate;
    // } 

    @EventListener
    public void webSocketConnectListenerHandler(SessionConnectEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
		String username = (String) headerAccessor.getSessionAttributes().get("username"); 
        if(username == null) username = "unknow user";
        log.info("[ Connect Event ] - '{}' conectou! :3", username);
    } 

    @EventListener
    public void webSocketDisconnectListenerHandler(SessionDisconnectEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        String username = (String) headerAccessor.getSessionAttributes().get("username"); 
        if (username == null) username = "unknown user";
        log.info("[ Disconnect Event ] - '{}' desconectou! :(", username);
    } 
}
