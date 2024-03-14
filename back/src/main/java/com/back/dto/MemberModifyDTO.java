package com.back.dto;

import lombok.Data;

@Data
public class MemberModifyDTO {

    private String email;

    private String pw;

    private String name;

    private String number;

    private String nickname;

    private String zipCode;				// 우편 번호

    private String streetAddress;		// 지번 주소

    private String detailAddress;		// 상세 주소(직접 입력하는값 ex. 3층)
}