package br.com.vitordev.globalchat.domain.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.vitordev.globalchat.domain.entity.MessageEntity;

public interface MessageRepository extends JpaRepository<MessageEntity, UUID> {
    
}
