package com.back.validator;

import com.back.dto.MemberJoinDTO;
import com.back.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@RequiredArgsConstructor
@Component
public class CheckNicknameValidator extends AbstractValidator<MemberJoinDTO> {
    private final MemberRepository memberRepository;
    @Override
    protected void doValidate( MemberJoinDTO dto, Errors errors) {
        if (memberRepository.existsByNickname(dto.getEmail())) {
            errors.rejectValue(
                    "email", "닉네임 중복 오류",
                    "이미 사용중인 닉네임 입니다.");}}}