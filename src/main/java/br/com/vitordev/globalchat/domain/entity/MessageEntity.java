package br.com.vitordev.globalchat.domain.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import br.com.vitordev.globalchat.api.request.MessagePostRequest;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Entity
@Table(name = "messages")
@Data
public class MessageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Lob
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_fk")
    private UserEntity user;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Deprecated
    public MessageEntity() { this.timestampToNow(); }

    public MessageEntity(MessagePostRequest request) {
        this.content = request.content();
        this.timestampToNow();
    }

    private void timestampToNow() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
}
