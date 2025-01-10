package com.example.springboot_board.controller;

import com.example.springboot_board.dto.BoardDTO;
import com.example.springboot_board.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Objects;

@Log4j2
@RequiredArgsConstructor
@Controller
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/")
    public String List(Model model, @RequestParam(value = "page", defaultValue = "0") int page) {
        Page<BoardDTO> paging = boardService.selectAllArticles(page);
        model.addAttribute("paging", paging);
        return "/list";
    }

    @GetMapping("/write")
    public String Write() {
        return "/write";
    }

    @GetMapping("/edit")
    public String Edit(@RequestParam(value = "no") int no,
                       @RequestParam(value = "title") String title,
                       @RequestParam(value = "content") String content,
                       @RequestParam(value = "writer") String writer, Model model) {
        log.info(String.valueOf(no), title, content, writer);
        model.addAttribute("no", no);
        model.addAttribute("title", title);
        model.addAttribute("content", content);
        model.addAttribute("writer", writer);
        return "/edit";
    }

    @GetMapping("/view")
    public String View(BoardDTO boardDTO, Model model) {
        log.info("view용 boardDTO : " + boardDTO);
        model.addAttribute("board", boardDTO);
        return "/view";
    }

    @GetMapping("/delete")
    public String Delete(@RequestParam(value = "no") int no) {
        String status = boardService.deleteArticle(no);
        if (Objects.equals(status, "success")) {
            return "redirect:/?status=successD";
        }
        return "redirect:/error";
    }

    @PostMapping("/write")
    public String WriteDB(BoardDTO boardDTO) {
        String status = boardService.insertArticle(boardDTO);
        if (Objects.equals(status, "success")) {
            return "redirect:/?status=successW";
        }
        return "redirect:/error";
    }

    @PostMapping("/edit")
    public String EditDB(BoardDTO boardDTO) {
        log.info("들어온 boardDTO : " + boardDTO);
        String status = boardService.updateArticle(boardDTO);
        log.info(status);
        if (Objects.equals(status, "success")) {
            return "redirect:/?status=successE";
        }
        return "redirect:/error";
    }

}
