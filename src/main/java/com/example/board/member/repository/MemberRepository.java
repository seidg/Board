package com.example.board.member.repository;

import com.example.board.member.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
    //이메일로 회원정보 조회
    Optional<MemberEntity> findByMemberEmail(String memberEmail);
}
