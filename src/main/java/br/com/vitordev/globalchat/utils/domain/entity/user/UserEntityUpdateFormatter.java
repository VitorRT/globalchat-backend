package br.com.vitordev.globalchat.utils.domain.entity.user;

import java.time.LocalDateTime;

import br.com.vitordev.globalchat.api.request.UserPutRequest;
import br.com.vitordev.globalchat.domain.entity.UserEntity;

public class UserEntityUpdateFormatter {
     public static void updateFormat(UserEntity entity, UserPutRequest request) {
        if (request.formalName() != null && request.formalName() != "") {
            entity.setFormalName(request.formalName());
        }
        entity.setUpdatedAt(LocalDateTime.now());
     }
}
