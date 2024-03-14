package com.back.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString (exclude = "memberRoleList")
public class Member {

    @Id
    private String email;

    private String pw;

    private String name;

    private boolean social;

    @ElementCollection(fetch = FetchType.LAZY)
    @Builder.Default
    private List<MemberRole> memberRoleList = new ArrayList<>();

    public void addRole(MemberRole memberRole){

        memberRoleList.add(memberRole);
    }

    public void clearRole(){
        memberRoleList.clear();
    }

    public void changeName(String name) {
        this.name = name;
    }

    public void changePw(String pw){
        this.pw = pw;
    }

    public void changeSocial(boolean social) {
        this.social = social;
    }

}