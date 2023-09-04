package br.com.vitordev.globalchat.api.request;

import java.util.UUID;

public record MessagePostRequest(
    String content,
    UUID userId
) { }
