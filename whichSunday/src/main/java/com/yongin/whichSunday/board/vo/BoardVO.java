package com.yongin.whichSunday.board.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
public class BoardVO {

    private Long id;

    @NotEmpty
    private String title;
    @NotEmpty
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
