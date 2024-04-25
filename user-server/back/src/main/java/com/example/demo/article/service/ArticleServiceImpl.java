package com.example.demo.article.service;

import com.example.demo.article.model.Article;
import com.example.demo.article.model.ArticleDto;
import com.example.demo.article.repository.ArticleRepository;
import com.example.demo.board.model.Board;
import com.example.demo.common.component.Messenger;
import com.example.demo.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ArticleServiceImpl implements ArticleService {
    private final ArticleRepository re;


    @Override
    public Messenger save(ArticleDto article) {
        re.save(dtoToEntity(article));
        return Messenger.builder()
                .message("SUCCESS")
                .id(article.getBoardId())
                .build();
    }

    @Override
    public Messenger deleteById(Long id) {
        re.deleteById(id);
        return new Messenger();
    }

    @Override
    public Messenger modify(ArticleDto article) {
        return new Messenger();
    }

    @Override
    public List<ArticleDto> findAll() {
        return re.findAllByOrderByIdDesc().stream().map(i->entityToDto(i)).toList();
    }

    @Override
    public Optional<ArticleDto> findById(Long id) {
        ArticleDto dto = entityToDto(Objects.requireNonNull(re.findById(id).orElse(null)));
        return Optional.ofNullable(dto);
    }

    @Override
    public long count() {
        return re.count();
    }

    @Override
    public boolean existsById(Long id) {
        return re.existsById(id);
    }

    @Override
    public List<ArticleDto> findAllByBoardId(Long id) {
        return re.findAllByBoardId(id).stream().map(i->entityToDto(i)).toList();
    }
}
