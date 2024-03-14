package com.back.service;

import com.back.domain.Member;
import com.back.dto.MemberJoinDTO;
import com.back.dto.MemberSecurityDTO;
import com.back.dto.MemberModifyDTO;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;

import java.util.Map;
import java.util.stream.Collectors;

@Transactional//access Token을 파라미터로 받아서 로그인처리에 사용하는 MemverDTO를
//반환하는 getKakaoMember();
public interface MemberService {



    void join(MemberJoinDTO memberJoinDTO) ;

    MemberSecurityDTO getKakaoMember(String accessToken);

    void modifyMember(MemberModifyDTO memberModifyDTO); // 추가되었음

    default MemberSecurityDTO entityToDTO(Member member){

        MemberSecurityDTO dto = new MemberSecurityDTO(
        member.getEmail(),
                member.getPw(),
                member.getName(),
                member.isSocial(),
                member.getNumber(),
                member.getZipCode(),
                member.getNickname(),
                member.getStreetAddress(),
                member.getDetailAddress(),
                member.getMemberRoleList()
                        .stream()
                        .map(memberRole -> memberRole.name()).collect(Collectors.toList()));
                return dto;
    }

    public Map<String, String> validateHandling(Errors errors);
}