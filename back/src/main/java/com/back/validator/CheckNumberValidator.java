package com.back.validator;

import com.back.dto.MemberJoinDTO;
import com.back.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@RequiredArgsConstructor
@Component
public class CheckNumberValidator extends AbstractValidator<MemberJoinDTO> {
    private final MemberRepository memberRepository;
    @Override
    protected void doValidate(MemberJoinDTO dto, Errors errors) {
        if (memberRepository.existsByNumber(dto.getEmail())) {
            errors.rejectValue(
                    "email", "전화번호 중복 오류",
                    "이미 사용중인 전화번호 입니다.");}}}