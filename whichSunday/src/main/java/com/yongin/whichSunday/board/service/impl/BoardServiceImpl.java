package com.yongin.whichSunday.board.service.impl;

import com.yongin.whichSunday.board.service.BoardService;
import com.yongin.whichSunday.board.vo.BoardVO;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.*;

@Service
public class BoardServiceImpl implements BoardService {

    private static Map<Long, BoardVO> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public BoardVO save(BoardVO boardVO) {
        boardVO.setId(++sequence);
        boardVO.setDate(LocalDate.now().toString());
        store.put(boardVO.getId(), boardVO);
        return boardVO;
    }

    @Override
    public BoardVO findById(long id) {
        return store.get(id);
    }

    @Override
    public List<BoardVO> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public void update(long id, BoardVO board) {
        BoardVO byIdBoard = findById(id);
        byIdBoard.setTitle(board.getTitle());
        byIdBoard.setContent(board.getContent());
        byIdBoard.setAttachFile(board.getAttachFile());
    }

    @Override
    public void delete(long id) {
        store.remove(id);
    }

    @PostConstruct
    void init() {
        BoardVO boardVO1 = new BoardVO("test1", "내용1", "10");
        BoardVO boardVO2 = new BoardVO("test2", "내용2", "10");
        BoardVO boardVO3 = new BoardVO("test3", "내용3", "10");

        save(boardVO1);
        save(boardVO2);
        save(boardVO3);
    }
}
