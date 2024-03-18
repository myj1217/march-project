package com.back.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import com.back.domain.Member;

public interface MemberRepository extends JpaRepository<Member, String> {

    @EntityGraph(attributePaths = {"memberRoleList"})
    @Query("select m from Member m where m.email = :email")
    Member getWithRoles(@Param("email") String email);

    boolean existsByEmail(@Param("email")String email);
    boolean existsByNumber(@Param("number")String number);
    boolean existsByNickname(@Param("nickname")String nickname);


}

    //중복처리를 위한 코드
