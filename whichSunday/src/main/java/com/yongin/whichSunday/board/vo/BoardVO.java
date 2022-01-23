package com.yongin.whichSunday.board.vo;

import lombok.Data;

@Data
public class BoardVO {
    private Long id;
    private String title;
    private String content;
    private String date;

    public BoardVO() {
    }

    public BoardVO(String title, String content, String date) {
        this.title = title;
        this.content = content;
        this.date = date;
    }
}
