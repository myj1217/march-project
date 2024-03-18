package com.back.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString

public class CbpResponseDTO<E> {

    private int page;
    private int size;
    private Long total;

    private int start;
    private int end;

    private boolean prev;
    private boolean next;

    private List<E> dtoList;

    @Builder(builderMethodName = "pageBuilder")
    public CbpResponseDTO(List<E> dtoList, Long total, int page, int size) {
        if (total <= 0) {
            return;
        }


        this.page = page;
        this.size = size;
        this.total = total;
        this.dtoList = dtoList;

        this.end = (int) (Math.ceil(this.page / 10.0)) * 10;     // 화면에서의 마지막 번호
        this.start = this.end - 9; // 화면에서의 시작 번호

        int last = (int) (Math.ceil((total / (double) size)));      // 데이터의 개수를 계산한 마지막 페이지 번호
        this.end = end > last ? last : end;

        this.prev = this.start > 1;
        this.next = total > this.end * this.size;
    }
}
