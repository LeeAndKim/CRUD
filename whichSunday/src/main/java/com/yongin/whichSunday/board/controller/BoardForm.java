package com.yongin.whichSunday.board.controller;

import com.yongin.whichSunday.board.vo.UploadFile;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;

@Data
public class BoardForm {
    @NotEmpty
    private String title;
    @NotEmpty
    private String content;
    private MultipartFile attachFile;
}
