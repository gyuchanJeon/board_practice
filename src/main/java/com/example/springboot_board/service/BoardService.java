package com.example.springboot_board.service;

import com.example.springboot_board.dto.BoardDTO;
import com.example.springboot_board.entity.Board;
import com.example.springboot_board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Log4j2
@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;

    public String insertArticle(BoardDTO boardDTO) {
        Board savedEntity = boardRepository.save(boardDTO.toEntity());
        if (savedEntity != null) {
            return "success";
        }
        return "failure";
    }

    public String updateArticle(BoardDTO boardDTO) {
        Optional<Board> boardOpt = boardRepository.findById(boardDTO.getNo());
        if (boardOpt.isPresent()) {
            Board board = boardOpt.get();
            log.info("no로 찾은 board : " + board);
            board.setTitle(boardDTO.getTitle());
            board.setContent(boardDTO.getContent());
            boardRepository.save(board);
            return "success";
        }
        return "failure";
    }

    public Page<BoardDTO> selectAllArticles(int page) {
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(new Sort.Order(Sort.Direction.ASC, "no"));
        Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));
        return this.boardRepository.findAll(pageable).map(Board::toDto);
    }

    public String deleteArticle(int no) {
        boardRepository.deleteById(no);
        Optional<Board> boardOpt = boardRepository.findById(no);
        if (boardOpt.isEmpty()) {
            return "success";
        }
        return "failure";
    }
}
