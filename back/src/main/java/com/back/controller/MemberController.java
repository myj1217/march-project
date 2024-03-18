package com.back.controller;


import com.back.dto.MemberJoinDTO;
import com.back.dto.MemberModifyDTO;
import com.back.service.MemberService;
import com.back.validator.CheckEmailValidator;
import com.back.validator.CheckNicknameValidator;
import com.back.validator.CheckNumberValidator;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/member")
@Log4j2
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;


    private final CheckNumberValidator checkNumberValidator;
    private final CheckNicknameValidator checkNicknameValidator;
    private final CheckEmailValidator checkEmailValidator;

    /* 커스텀 유효성 검증을 위해 추가 */
    @InitBinder
    public void validatorBinder(WebDataBinder binder) {
        binder.addValidators(checkNumberValidator);
        binder.addValidators(checkNicknameValidator);
        binder.addValidators(checkEmailValidator);
    }


    //회원가입 부분
    @GetMapping("/join")
    public void joinGET() {
        log.info("join get...");

    }

    @PostMapping("/auth/join")
    public ResponseEntity<Map<String, String>> join(@Valid MemberJoinDTO memberJoinDTO, Errors errors) {
        if (errors.hasErrors()) {
            /* 유효성 통과 못한 필드와 메시지를 핸들링 */
            Map<String, String> validatorResult = memberService.validateHandling(errors);

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validatorResult);
        }
        memberService.join(memberJoinDTO);

        return ResponseEntity.ok().build();
    }


    @PutMapping("/modify")
    public ResponseEntity<Map<String, String>> modify(@RequestBody @Valid MemberModifyDTO memberModifyDTO, Errors errors, Model model) {

        if (errors.hasErrors()) {
            /* 유효성 통과 못한 필드와 메시지를 핸들링 */
            Map<String, String> validatorResult = memberService.validateHandling(errors);

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validatorResult);

        }

        //수정처리 완료
        memberService.modifyMember(memberModifyDTO);

        return ResponseEntity.ok().build();

    }
}




