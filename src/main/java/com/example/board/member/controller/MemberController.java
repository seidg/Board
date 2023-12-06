package com.example.board.member.controller;

import com.example.board.member.dto.MemberDTO;
import com.example.board.member.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {
    // 생성자 주입
    private final MemberService memberService;

    //회원가입 페이지
    @GetMapping("/member/save")
    public String saveForm(){
        return "/member/save";
    }

    @PostMapping("/member/save")
    public String save(@ModelAttribute MemberDTO memberDTO){
        memberService.save(memberDTO);
        return "/member/index";
    }

    @GetMapping("/member/login")
    public String logingForm() {return "/member/login";}

    @PostMapping("/member/login")
    public String login(@ModelAttribute MemberDTO memberDTO, HttpSession session) {
        MemberDTO loginResult = memberService.login(memberDTO);
        if(loginResult != null){
            //login성공
            session.setAttribute("loginEmail", loginResult.getMemberEmail());
            return "/member/main";
        } else{
            //login실패
            return "/member/login";
        }
    }

    @GetMapping("/member/")
    public String findAll(Model model){
        List<MemberDTO> memberDTOList = memberService.findAll();
        // html로 가져갈 데이터가 있다면 model사용
        model.addAttribute("memberList",memberDTOList);
        return "/member/list";
    }

    @GetMapping("/member/{id}")
    public String findeById(@PathVariable Long id , Model model){
        MemberDTO memberDTO = memberService.findById(id);
        model.addAttribute("member",memberDTO);
        return "/member/detail";
    }

    @GetMapping("/member/update")
    public String updateForm(HttpSession session, Model model){
        String myEmail = (String) session.getAttribute("loginEmail");
        MemberDTO memberDTO = memberService.updateForm(myEmail);
        model.addAttribute("updateMember",memberDTO);
        return "/member/update";
    }

    @PostMapping("/member/update")
    public String update(@ModelAttribute MemberDTO memberDTO){
        memberService.update(memberDTO);
        return "redirect:/member/" + memberDTO.getId();
    }

    @GetMapping("/member/delete/{id}")
    public String deleteById(@PathVariable Long id){
        memberService.delete(id);
        return "redirect:/member/";
    }

    @GetMapping("/member/logout")
    public String logout(HttpSession session){
        session.invalidate();            //세션 무효화
        return "/member/index";
    }


}
