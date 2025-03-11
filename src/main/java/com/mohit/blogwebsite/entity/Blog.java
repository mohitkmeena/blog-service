package com.mohit.blogwebsite.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.Instant;

@Entity
@Builder
@Getter
@Setter
@Table(name = "blog")
@AllArgsConstructor
@RequiredArgsConstructor
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private  String id;
    @Column(nullable = false)
    @NotBlank(message = "please give a valid title")
    private String title;
    @Column(nullable = false)
    @NotBlank(message = "content can't be null")
    private String content;
    @Column(nullable = false)
    @NotBlank(message = "please give a valid author,author can't be null")
    private String author;
    private Instant createdAt;

    @PrePersist
    public void addCreatedAt(){
        this.createdAt=Instant.now();
    }
}
