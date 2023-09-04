package br.com.vitordev.globalchat.domain.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.vitordev.globalchat.api.request.UserAuthRequest;
import br.com.vitordev.globalchat.api.response.UserResponse;
import br.com.vitordev.globalchat.domain.entity.UserEntity;
import br.com.vitordev.globalchat.domain.exception.UserInvalidCredentialsException;
import br.com.vitordev.globalchat.domain.repository.UserRepository;
import br.com.vitordev.globalchat.utils.UserFormat.UsernameFormat;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AuthService {
    private UserRepository userRepository;

    @Deprecated
    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public UserResponse login(UserAuthRequest request) throws UserInvalidCredentialsException {
        String username = UsernameFormat.format(request.username());
        UserEntity entity = getUserEntityByUsername(username);
        UserResponse user = new UserResponse(entity);
        if(!entity.getPassword().equals(request.password())) {
            throw new UserInvalidCredentialsException(HttpStatus.BAD_REQUEST.value(),"credenciais erradas.");
        }
        log.info("[ logged ] - user '{}' logado!", user.username());
        return user;
    }

    private UserEntity getUserEntityByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(
            () -> new EntityNotFoundException("credenciais erradas.")
        );
    }
}
