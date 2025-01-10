package com.example.springboot_board.entity;

import com.example.springboot_board.dto.BoardDTO;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "SpringBoot_Board")
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "no")
    private int no;

    @Column(name = "title")
    private String title;
    @Column(name = "content")
    private String content;
    @Column(name = "writer")
    private String writer;

    public BoardDTO toDto() {
        return BoardDTO.builder()
                .no(this.no)
                .title(this.title)
                .content(this.content)
                .writer(this.writer)
                .build();
    }

}
