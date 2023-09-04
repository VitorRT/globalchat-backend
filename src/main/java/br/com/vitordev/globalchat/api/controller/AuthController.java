package br.com.vitordev.globalchat.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.vitordev.globalchat.api.request.UserAuthRequest;
import br.com.vitordev.globalchat.api.response.UserResponse;
import br.com.vitordev.globalchat.domain.exception.UserInvalidCredentialsException;
import br.com.vitordev.globalchat.domain.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private AuthService service;

    @Deprecated
    public AuthController(AuthService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<UserResponse> login(@RequestBody UserAuthRequest request) throws UserInvalidCredentialsException {
        return ResponseEntity.ok(service.login(request));
    }
}