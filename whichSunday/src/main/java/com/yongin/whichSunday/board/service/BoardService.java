package com.yongin.whichSunday.board.service;

import com.yongin.whichSunday.board.vo.BoardVO;

import java.util.List;

public interface BoardService {

    BoardVO save(BoardVO boardVO);

    BoardVO findById(long id);

    List<BoardVO> findAll();

    void update(long id, BoardVO board);

    void delete(long id);
}
