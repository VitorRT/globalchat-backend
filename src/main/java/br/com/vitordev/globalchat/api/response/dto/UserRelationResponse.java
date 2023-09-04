package br.com.vitordev.globalchat.api.response.dto;

import java.util.UUID;

import br.com.vitordev.globalchat.domain.entity.UserEntity;

public record UserRelationResponse(
    UUID id,
    String username
){
    public UserRelationResponse(UserEntity entity) {
        this(
            entity.getId(),
            entity.getUsername()
        );
    }
}
