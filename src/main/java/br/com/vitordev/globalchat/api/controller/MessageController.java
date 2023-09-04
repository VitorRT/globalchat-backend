package br.com.vitordev.globalchat.api.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.vitordev.globalchat.api.response.MessageResponse;
import br.com.vitordev.globalchat.domain.service.MessageService;

@RestController
@RequestMapping("/messages")
public class MessageController {
    private MessageService service;

    @Deprecated
    public MessageController(MessageService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Page<MessageResponse>> list(@PageableDefault(size = 20) Pageable pageable) {
        return ResponseEntity.ok(service.list(pageable));
    }
}
