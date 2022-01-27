package com.yongin.whichSunday.board.controller;

import com.yongin.whichSunday.board.service.impl.BoardServiceImpl;
import com.yongin.whichSunday.board.vo.BoardVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/boards")
public class BoardController {
    BoardServiceImpl boardService = new BoardServiceImpl();

    @GetMapping
    public String boards(Model model) {
        List<BoardVO> list = boardService.findAll();
        model.addAttribute("list", list);
        return "/board/boards";
    }

    @GetMapping("/{boardId}")
    public String board(@PathVariable long boardId, Model model) {
        BoardVO board = boardService.findById(boardId);
        model.addAttribute("board", board);
        return "/board/board";
    }

    @GetMapping("/addBoard")
    public String getBoardForm(Model model) {
        BoardVO board = new BoardVO();
        model.addAttribute("board", board);
        return "/board/addForm";
    }

    @PostMapping("/addBoard")
    public String addBoard(BoardVO board, RedirectAttributes redirectAttributes) {
        BoardVO savedBoard = boardService.save(board);
        redirectAttributes.addAttribute("boardId", savedBoard.getId());
        return "redirect:/boards/{boardId}";
    }

    @GetMapping("/editBoard/{boardId}")
    public String editBoardForm(@PathVariable long boardId, Model model) {
        BoardVO board = boardService.findById(boardId);
        model.addAttribute("board", board);
        return "/board/editForm";
    }

    @PostMapping("/editBoard/{boardId}")
    public String editBoard(@PathVariable long boardId, BoardVO board, RedirectAttributes redirectAttributes) {
        boardService.update(boardId, board);
        redirectAttributes.addAttribute("boardId", boardId);
        return "redirect:/boards/{boardId}";
    }

    @GetMapping("/deleteBoard/{boardId}")
    public String deleteBoard(@PathVariable long boardId) {
        boardService.delete(boardId);
        return "redirect:/boards";
    }


}
