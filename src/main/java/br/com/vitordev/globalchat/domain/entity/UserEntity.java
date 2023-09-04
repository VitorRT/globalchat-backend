package br.com.vitordev.globalchat.domain.entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import br.com.vitordev.globalchat.api.request.UserPostRequest;
import br.com.vitordev.globalchat.api.request.UserPutRequest;
import br.com.vitordev.globalchat.utils.domain.entity.user.UserEntityUpdateFormatter;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true)
    private String username;

    @Column(name = "formal_name")
    private String formalName;

    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<MessageEntity> messages;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Deprecated
    public UserEntity() { this.timestampToNow(); }

    public UserEntity(UserPostRequest request) {
        this.username = request.username();
        this.formalName = request.formalName();
        this.password = request.password();
        this.timestampToNow();
    }

    private void timestampToNow() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public void toUpdate(UserPutRequest request) {
        UserEntityUpdateFormatter.updateFormat(this, request);
    }
}
