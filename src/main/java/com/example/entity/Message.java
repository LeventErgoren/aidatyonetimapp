package com.example.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "messages")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;
    private String targetAudience; 
    private LocalDateTime sentDate;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private Kullanici sender;
}
