package com.example.demo.common.component;

import lombok.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Getter
@AllArgsConstructor
@Builder
public class PageRequestFileVo {
    private int page;
    private int size;
    private String type;
    private String keyword;


    public PageRequestFileVo( ) {
        this.page =1;
        this.size = 10;
    }
    public Pageable getPageable(Sort sort){
        return PageRequest.of(page -1, size, sort);
    }
}
