package com.back.validator;

import com.back.dto.MemberJoinDTO;
import com.back.dto.MemberSecurityDTO;
import com.back.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@RequiredArgsConstructor
@Component
public class CheckEmailValidator extends AbstractValidator<MemberJoinDTO> {
    private final MemberRepository memberRepository;
    @Override
    protected void doValidate( MemberJoinDTO dto, Errors errors) {
        if (memberRepository.existsByEmail(dto.getEmail())) {
            errors.rejectValue(
                    "email", "이메일 중복 오류",
                    "이미 사용중인 아이디 입니다.");}}}