package br.com.vitordev.globalchat.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UserPutRequest(
    @JsonProperty(value = "formal_name")
    String formalName
) {}
