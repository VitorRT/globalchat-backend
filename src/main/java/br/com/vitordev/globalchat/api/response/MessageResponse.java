package br.com.vitordev.globalchat.api.response;

import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.vitordev.globalchat.api.response.dto.UserRelationResponse;
import br.com.vitordev.globalchat.domain.entity.MessageEntity;

public record MessageResponse(
    UUID id,
    UserRelationResponse user,
    String content,
    @JsonProperty(value = "created_at")
    LocalDateTime createdAt,
    @JsonProperty(value = "updated_at")
    LocalDateTime updatedAt
) {
    public MessageResponse(MessageEntity entity) {
        this(
            entity.getId(),
            new UserRelationResponse(entity.getUser()),
            entity.getContent(),
            entity.getCreatedAt(),
            entity.getUpdatedAt()
        );
    }
} 
