package com.back.domain;

import com.back.dto.MemberSecurityDTO;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString (exclude = "memberRoleList")
public class Member {

    @Id
    private String email;  // 이메일

    private String pw;     // 비밀번호

    private String name;    //이름

    private String nickname; // 닉네임

    private String number;   //휴대폰 번호

    private boolean social;  // 소셜

    private String zipCode;				// 우편 번호

    private String streetAddress;		// 지번 주소

    private String detailAddress;		// 상세 주소(직접 입력하는값 ex. 3층)

    @ElementCollection(fetch = FetchType.LAZY)
    @Builder.Default
    private List<MemberRole> memberRoleList = new ArrayList<>();

        public void addRole(MemberRole memberRole){

        memberRoleList.add(memberRole);
    }


    public void clearRole(){
        memberRoleList.clear();
    }

    public void changeNickname(String nickname) {this.nickname = nickname; }

    public void changeName(String uname) {
        this.name = name;
    }

    public void changeNumber(String number) {
        this.number = number;
    }

    public void changePw(String pw){
        this.pw = pw;
    }

    public void changeZipCode(String zipCode){
        this.zipCode = zipCode;
    }


    public void changeStreetAddress(String streetAddress){
        this.streetAddress = streetAddress;
    }

    public void changeDetailAddress(String detailAddress) {this.detailAddress = detailAddress;}

    public void changeSocial(boolean social) {
        this.social = social;
    }

}