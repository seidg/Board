package com.example.board.member.service;


import com.example.board.member.dto.MemberDTO;
import com.example.board.member.entity.MemberEntity;
import com.example.board.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public void save(MemberDTO memberDTO) {
        //1. dto -> entity
        //2. repository의 save 메서드 호출
        MemberEntity memberEntity = MemberEntity.toMemberEntity(memberDTO);
        memberRepository.save(memberEntity); //jparepository가 제공해주는 save메서드
    }

    public MemberDTO login(MemberDTO memberDTO) {
        //1,회원이 입력한 이메일로 DB에서 조회함
        //2. DB에서 조회한 비밀번호와 사용자가 입력한 비밀번호가 일치하는지 판단
        Optional<MemberEntity> byMemberEmail = memberRepository.findByMemberEmail(memberDTO.getMemberEmail());
        if(byMemberEmail.isPresent()){
            //조회 결과가 있다
            MemberEntity memberEntity = byMemberEmail.get();
            if (memberEntity.getMemberPassword().equals(memberDTO.getMemberPassword())){
                //비밀번호가 일치
                // entity -> dto 변환 후 리턴
                MemberDTO DTO =MemberDTO.toMemberDTO(memberEntity);
                return DTO;
            }else{
                //비밀번호 불일치
                return null;
            }
        }else{
            //조회 결과가 없다
            return null;
        }
    }

    public List<MemberDTO> findAll() {
        //Repository에 있는 메서드
        List<MemberEntity> memberEntityList = memberRepository.findAll();
        List<MemberDTO> memberDTOList = new ArrayList<>();
        for(MemberEntity memberEntity: memberEntityList){
            memberDTOList.add(MemberDTO.toMemberDTO(memberEntity));
        }
        return memberDTOList;
    }
}
