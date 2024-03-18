package com.back.domain;

import com.back.service.ComBoardService;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "comboard")
@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class ComBoard {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long comBoardBno;

    @Column(length = 30, nullable = false)
    private String comBoardTitle;

    @Column(length = 500, nullable = false)
    private String comBoardContent;

    @Column(length = 10, nullable = false)
    private String comBoardWriter;

    @Column
    private String imagePath; // 이미지 경로를 저장할 필드

    public void cbChange(String comBoardTitle, String comBoardContent) {
        this.comBoardTitle = comBoardTitle;
        this.comBoardContent = comBoardContent;
    }

    public void setComBoardBno(Long comBoardBno) {
        this.comBoardBno = comBoardBno;
    }

    @Builder
    public ComBoard(String comBoardTitle, String comBoardContent, String comBoardWriter) {
        this.comBoardTitle = comBoardTitle;
        this.comBoardContent = comBoardContent;
        this.comBoardWriter = comBoardWriter;
    }

    @Column(nullable = false)
    private LocalDateTime cbRegDate;

    public void setCbRegDate(LocalDateTime cbRegDate) {
        this.cbRegDate = cbRegDate;
    }

    @PrePersist
    public void prePersist() {
        this.cbRegDate = LocalDateTime.now();
    }

    public String getFormattedCbRegDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return cbRegDate.format(formatter);
    }

}
