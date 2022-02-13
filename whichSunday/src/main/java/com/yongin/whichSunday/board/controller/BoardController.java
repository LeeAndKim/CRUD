package com.yongin.whichSunday.board.controller;

import com.yongin.whichSunday.board.service.impl.BoardServiceImpl;
import com.yongin.whichSunday.board.vo.BoardVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/boards")
@RequiredArgsConstructor
public class BoardController {

    private final BoardServiceImpl boardService;

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
    public String addBoard(@Validated @ModelAttribute(name = "board") BoardVO board, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            log.info("bindingResult={}", bindingResult);
            return "/board/addForm";
        }


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
    public String editBoard(@PathVariable long boardId, @Validated @ModelAttribute(name = "board") BoardVO board, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            log.info("bindingResult={}", bindingResult);

            return "/board/editForm";
        }

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
