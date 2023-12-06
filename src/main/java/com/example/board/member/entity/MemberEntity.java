package com.example.board.member.entity;


import com.example.board.member.dto.MemberDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

//Table 역할
@Entity
@Setter
@Getter
@Table(name = "member_table")
public class MemberEntity {
    @Id // 기본키지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto_increment
    private Long id;

    @Column(unique = true)
    private String memberEmail;

    @Column
    private String memberPassword;

    @Column
    private String memberName;

    public static MemberEntity toMemberEntity(MemberDTO MemberDTO){
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setMemberEmail(MemberDTO.getMemberEmail());
        memberEntity.setMemberPassword(MemberDTO.getMemberPassword());
        memberEntity.setMemberName(MemberDTO.getMemberName());
        return memberEntity;
    }

    public static MemberEntity toUpdateMemberEntity(MemberDTO MemberDTO){
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setId(MemberDTO.getId());
        memberEntity.setMemberEmail(MemberDTO.getMemberEmail());
        memberEntity.setMemberPassword(MemberDTO.getMemberPassword());
        memberEntity.setMemberName(MemberDTO.getMemberName());
        return memberEntity;
    }
}
