package com.example.springboot_board.dto;

import com.example.springboot_board.entity.Board;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardDTO {

    private int no;
    private String title;
    private String content;
    private String writer;

    public Board toEntity() {
        return Board.builder()
                .no(this.no)
                .title(this.title)
                .content(this.content)
                .writer(this.writer)
                .build();
    }
}
