package com.yongin.whichSunday.board.controller;

import com.yongin.whichSunday.board.service.impl.BoardServiceImpl;
import com.yongin.whichSunday.board.vo.BoardVO;
import com.yongin.whichSunday.board.vo.UploadFile;
import com.yongin.whichSunday.common.attachfile.FileStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.file.ConfigurationSource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriUtils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/boards")
@RequiredArgsConstructor
public class BoardController {

    private final BoardServiceImpl boardService;
    private final FileStore fileStore;

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
    public String addBoard(@Validated @ModelAttribute(name = "board") BoardForm form, BindingResult bindingResult, RedirectAttributes redirectAttributes) throws IOException {

        if (bindingResult.hasErrors()) {
            log.info("bindingResult={}", bindingResult);
            return "/board/addForm";
        }

        UploadFile uploadFile = fileStore.storeFile(form.getAttachFile());
        BoardVO board = new BoardVO();
        board.setContent(form.getContent());
        board.setTitle(form.getTitle());
        board.setAttachFile(uploadFile);

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

    @GetMapping("/attach/{boardId}")
    public ResponseEntity<Resource> downloadAttach(@PathVariable long boardId) throws MalformedURLException {
        BoardVO boardById = boardService.findById(boardId);
        String storeFileName = boardById.getAttachFile().getStoreFileName();
        String uploadFileName = boardById.getAttachFile().getUploadFileName();

        UrlResource urlResource = new UrlResource("file:" + fileStore.getFullPath(storeFileName));

        String encodedUploadFileName = UriUtils.encode(uploadFileName, StandardCharsets.UTF_8);
        String contentDisposition = "attachment; filename=\"" + encodedUploadFileName + "\"";

        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition).body(urlResource);
    }
}
