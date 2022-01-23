package com.yongin.whichSunday.board.controller;

import com.yongin.whichSunday.board.service.impl.BoardServiceImpl;
import com.yongin.whichSunday.board.vo.BoardVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("boards")
public class BoardController {
    BoardServiceImpl boardService = new BoardServiceImpl();

    @GetMapping
    public String boards(Model model) {
        List<BoardVO> list = boardService.findAll();
        model.addAttribute("list", list);
        return "board/boards";
    }

}
