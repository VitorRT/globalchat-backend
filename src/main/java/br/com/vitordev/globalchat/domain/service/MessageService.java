package br.com.vitordev.globalchat.domain.service;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.vitordev.globalchat.api.request.MessagePostRequest;
import br.com.vitordev.globalchat.api.response.MessageResponse;
import br.com.vitordev.globalchat.domain.entity.MessageEntity;
import br.com.vitordev.globalchat.domain.entity.UserEntity;
import br.com.vitordev.globalchat.domain.repository.MessageRepository;
import br.com.vitordev.globalchat.domain.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class MessageService {
    private MessageRepository messageRepository;
    private UserRepository userRepository;

    @Deprecated
    public MessageService(MessageRepository messageRepository, UserRepository userRepository) {
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
    }

    public MessageResponse create(MessagePostRequest request) {
        UserEntity user = getUserEntityById(request.userId());
        MessageEntity entity = new MessageEntity(request);
        entity.setUser(user);
        return new MessageResponse(messageRepository.save(entity));
    }

    public Page<MessageResponse> list(Pageable pageable) {
        return messageRepository.findAll(pageable).map(MessageResponse::new);
    }

    private UserEntity getUserEntityById(UUID id) {
        return userRepository.findById(id).orElseThrow(
            () -> new EntityNotFoundException("Nenhum usuário foi encontrado com esse id.")
        );
    }
}
