package com.example.demo.article.model;

import com.example.demo.board.model.Board;
import com.example.demo.user.model.User;
import lombok.*;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@ToString
public class ArticleDto {
    private Long id;
    private String title;
    private String description;
    private String writerId;
    private Long boardId;
    private LocalDateTime regDate;
    private LocalDateTime modDate;
}
