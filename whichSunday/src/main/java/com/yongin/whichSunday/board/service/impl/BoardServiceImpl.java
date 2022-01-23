package com.yongin.whichSunday.board.service.impl;

import com.yongin.whichSunday.board.service.BoardService;
import com.yongin.whichSunday.board.vo.BoardVO;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

@Service
public class BoardServiceImpl implements BoardService {

    private static Map<Long, BoardVO> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public BoardVO save(BoardVO boardVO) {
        boardVO.setId(++sequence);
        store.put(boardVO.getId(), boardVO);
        return boardVO;
    }

    @Override
    public Optional<BoardVO> findById(long id) {
        return store.values().stream().filter(boardVO -> boardVO.getId().equals(id)).findAny();
    }

    @Override
    public List<BoardVO> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public void update(long id) {

    }

    @Override
    public void delete(long id) {

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
