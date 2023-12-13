package com.example.board.board.controller;

import com.example.board.board.dto.BoardDTO;
import com.example.board.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/board/save")
    public String saveForm(){
        return "board/save";
    }

    @PostMapping("/board/save")
    public String save(@ModelAttribute BoardDTO boardDTO){ //Spring에서 save에있는 name태그에 사용된 필드명과 DTO객체내에 필드명이 같은것끼리 setter에 값을 알아서 넣어줌
        boardService.save(boardDTO);
        return "index";
    }

    @GetMapping("/board/")
    public String findAll(Model model) {
        List<BoardDTO> boardDTOList = boardService.findAll();
        model.addAttribute("boardlist", boardDTOList);
        return "board/list";
    }
}
