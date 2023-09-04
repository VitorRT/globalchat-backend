package br.com.vitordev.globalchat.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@ResponseStatus(HttpStatus.BAD_REQUEST)
@AllArgsConstructor
@NoArgsConstructor
public class UserAlreadyExistsException extends Exception {
    private Integer status;
    private String message;

    public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }   
}   

