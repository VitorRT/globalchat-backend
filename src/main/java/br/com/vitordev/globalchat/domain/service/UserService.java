package br.com.vitordev.globalchat.domain.service;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.vitordev.globalchat.api.request.UserPostRequest;
import br.com.vitordev.globalchat.api.request.UserPutRequest;
import br.com.vitordev.globalchat.api.response.UserResponse;
import br.com.vitordev.globalchat.domain.entity.UserEntity;
import br.com.vitordev.globalchat.domain.exception.UserAlreadyExistsException;
import br.com.vitordev.globalchat.domain.repository.UserRepository;
import br.com.vitordev.globalchat.utils.UserFormat.UsernameFormat;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService {
    private UserRepository userRepository;

    @Deprecated
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponse create(UserPostRequest request) throws UserAlreadyExistsException {
        boolean userAlreadyExists = userRepository.existsByUsername(request.username());        
        if(userAlreadyExists == true) {
            throw new UserAlreadyExistsException(HttpStatus.BAD_REQUEST.value(), "usuário com as mesmas credencias já cadastrado.");
        }
        UserEntity entity = new UserEntity(request);
        entity.setUsername(UsernameFormat.format(request.username()));
        UserResponse response = new UserResponse(userRepository.save(entity));
        log.info("[ create ] - user {} registrado com sucesso.", response.username());
        return response;
    }

    public UserResponse update(UUID id, UserPutRequest request) { 
        UserEntity entity = getUserEntityById(id);
        entity.toUpdate(request);
        return new UserResponse(userRepository.save(entity));
    }
    public void delete(UUID id) { 
        UserEntity entity = getUserEntityById(id);
        userRepository.delete(entity);
     }

    public UserResponse show(UUID id) { 
        return new UserResponse(getUserEntityById(id));
     }   

    private UserEntity getUserEntityById(UUID id) {
        return userRepository.findById(id).orElseThrow(
            () -> new EntityNotFoundException("Nenhum usuário foi encontrado com esse id.")
        );
    }
}
