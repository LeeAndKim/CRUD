package com.yongin.whichSunday.board.service;

import com.yongin.whichSunday.board.vo.BoardVO;

import java.util.List;
import java.util.Optional;

public interface BoardService {

    BoardVO save(BoardVO boardVO);

    Optional<BoardVO> findById(long id);

    List<BoardVO> findAll();

    void update(long id);

    void delete(long id);
}
