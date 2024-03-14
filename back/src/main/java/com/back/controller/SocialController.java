//SocialController.java
package com.back.controller;


import com.back.dto.MemberModifyDTO;
import jakarta.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.back.dto.MemberSecurityDTO;
import com.back.service.MemberService;
import com.back.util.JWTUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;


@RestController
@Log4j2
@RequiredArgsConstructor
public class SocialController {

    private final MemberService memberService;

    @GetMapping("/api/member/kakao")
    public Map<String, Object> getMemberFromKakao(String accessToken) {

        log.info("accessToken ");
        log.info(accessToken);

        MemberSecurityDTO memberSecurityDTO = memberService.getKakaoMember(accessToken);

        Map<String, Object> claims = memberSecurityDTO.getClaims();

        String jwtAccessToken = JWTUtil.generateToken(claims, 10);
        String jwtRefreshToken = JWTUtil.generateToken(claims, 60*1);

        claims.put("accessToken", jwtAccessToken);
        claims.put("refreshToken", jwtRefreshToken);

        return claims;
    }
    @PutMapping("/member/modify")
    public String modify(@RequestBody @Valid MemberModifyDTO memberModifyDTO, Errors errors, Model model) {

        if (errors.hasErrors()) {
            /* 정보수정 실패시 입력 데이터 값을 유지 */
            model.addAttribute("memberModifyDTO", memberModifyDTO);
            /* 유효성 통과 못한 필드와 메시지를 핸들링 */
            Map<String, String> validatorResult = memberService.validateHandling(errors);

            for (String key : validatorResult.keySet()) {
                model.addAttribute(key, validatorResult.get(key));
            }

            /* 정보수정 페이지로 다시 리턴 */

            return "/member/modify";

        }

        //수정처리 완료
        memberService.modifyMember(memberModifyDTO);

        return "redirect:/member/modify";

    }

}