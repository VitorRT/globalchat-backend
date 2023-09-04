package br.com.vitordev.globalchat.domain.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.vitordev.globalchat.domain.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, UUID>{
    boolean existsByUsername(String username);
    Optional<UserEntity> findByUsername(String username);
}
