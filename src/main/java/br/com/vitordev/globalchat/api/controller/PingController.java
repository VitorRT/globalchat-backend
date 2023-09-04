package br.com.vitordev.globalchat.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/ping")
@Slf4j
public class PingController {
    
    @GetMapping
    public ResponseEntity<String> sendPong() {
        log.info("[ connected ] - cliente conectado ao servidor.");
        return ResponseEntity.ok("pong!");
    }
}
