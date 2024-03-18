package com.back.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class ComBoardDTO {
    private Long comBoardBno;
    private String comBoardTitle;
    private String comBoardContent;
    private String comBoardWriter;
    private LocalDateTime cbRegDate;
    private String imagePath; // 이미지 경로를 저장할 필드
}
