package br.com.vitordev.globalchat.api.response;

import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.vitordev.globalchat.domain.entity.UserEntity;

public record UserResponse(
    UUID id,
    String username,
    @JsonProperty(value = "formal_name")
    String formalName,
    @JsonProperty(value = "created_at")
    LocalDateTime createdAt,
    @JsonProperty(value = "updated_at")
    LocalDateTime updatedAt
) { 
    public UserResponse(UserEntity entity) {
        this(
            entity.getId(),
            entity.getUsername(),
            entity.getFormalName(),
            entity.getCreatedAt(),
            entity.getUpdatedAt()
        );
    }
}
