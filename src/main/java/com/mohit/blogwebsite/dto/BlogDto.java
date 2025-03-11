package com.mohit.blogwebsite.dto;

import lombok.*;

import java.time.Instant;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class BlogDto {
    private  String id;
    private String title;
    private String content;
    private String author;
    private Instant createdAt;
}
