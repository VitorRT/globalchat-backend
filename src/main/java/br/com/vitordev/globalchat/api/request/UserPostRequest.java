package br.com.vitordev.globalchat.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UserPostRequest(
    String username,
    @JsonProperty(value = "formal_name")
    String formalName,
    String password
) { }
