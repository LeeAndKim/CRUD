package com.yongin.whichSunday.board.vo;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
public class BoardVO {

    private Long id;
    private String title;
    private String content;
    private String date;
    private UploadFile attachFile;

    public BoardVO() {
    }

    public BoardVO(String title, String content, String date) {
        this.title = title;
        this.content = content;
        this.date = date;
    }
}
