//MemberRepositoryTests .java

package com.back.repository;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.back.domain.Member;
import com.back.domain.MemberRole;

@SpringBootTest
@Log4j2
public class MemberRepositoryTests {
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void testInsertMember(){
        for (int i = 0; i < 10; i++) {
            Member member = Member.builder()
                    .email("user"+(i+20)+"@aaa.com")
                    .pw(passwordEncoder.encode("1111"))
                    .name("USER"+(i+20))
                    .build();
            member.addRole(MemberRole.USER);
            if(i >= 5){
                member.addRole(MemberRole.MANAGER);
            }
            if(i >= 8){
                member.addRole(MemberRole.ADMIN);
            }
            memberRepository.save(member);
        }
    }

    @Test
    public void testInsertMember2(){
            Member member = Member.builder()
                    .email("222@aaa.com")
                    .pw(passwordEncoder.encode("1111"))
                    .name("김OO")
                    .nickname("테스트")
                    .number("01012345678")
                    .zipCode("1234")
                    .streetAddress("12-34번지")
                    .detailAddress("1층")
                    .build();
            member.addRole(MemberRole.USER);
        memberRepository.save(member);
        }
    @Test
    public void testRead() {
        String email = "user9@aaa.com";

        Member member = memberRepository.getWithRoles(email);

        log.info("-----------------");
        log.info(member);
    }
}