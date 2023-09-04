package br.com.vitordev.globalchat.api.controller;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.vitordev.globalchat.api.request.UserPostRequest;
import br.com.vitordev.globalchat.api.request.UserPutRequest;
import br.com.vitordev.globalchat.api.response.UserResponse;
import br.com.vitordev.globalchat.domain.exception.UserAlreadyExistsException;
import br.com.vitordev.globalchat.domain.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
    private UserService service;

    @Deprecated
    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<UserResponse> create(@RequestBody UserPostRequest request) throws UserAlreadyExistsException {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));
    }

    @PutMapping("{id}")
    public ResponseEntity<UserResponse> update(@PathVariable UUID id, @RequestBody UserPutRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> destroy(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();  
    }

    @GetMapping("{id}")
    public ResponseEntity<UserResponse> show(@PathVariable UUID id) {
        return ResponseEntity.ok(service.show(id));
    }
}
