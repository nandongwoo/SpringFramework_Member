package com.icia.member.controller;

import com.icia.member.dto.MemberDTO;
import com.icia.member.service.MemberService;
import com.mysql.cj.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.SessionScope;

import javax.servlet.http.HttpSession;
import java.lang.reflect.Member;
import java.util.List;

@Controller
public class MemberController {
    @Autowired
    private MemberService memberService;

    @GetMapping("/save")
    public String save() {
        return "memberSave";
    }

    // @ModelAttribute = MemberDTO를 받아올 때
    // Model model (model.addAttribute("member", memberDTO);) = MemberDTO를 보내줄 때
    @PostMapping("/save")
    public String save(@ModelAttribute MemberDTO memberDTO) {
        memberService.save(memberDTO);
        return "memberLogin";
    }


    @GetMapping("/login")
    public String login() {
        return "memberLogin";
    }


    @PostMapping("/login")

    public String login(@ModelAttribute MemberDTO memberDTO, HttpSession httpSession) {
        MemberDTO memberDTO1 = memberService.login(memberDTO);
        if (memberDTO1 != null) {
            httpSession.setAttribute("loginEmail", memberDTO.getMemberEmail());
            return "memberMain";
        } else {
            System.out.println("안됨 준상 바보");
            return "memberLogin";
        }
    }


    @GetMapping("logout")
    public String logout(HttpSession session) {
//        해당 파라미터만 없앨 경우
        session.removeAttribute("loginEmail");
//         세션 전체를 없앨 경우
//        session.invalidate();

        return "redirect:/";
//        기본 페이지로 넘어갈 때

    }


    @GetMapping("/list")
    public String list(Model model) {
        List<MemberDTO> memberDTOList = memberService.list();
        model.addAttribute("memberList", memberDTOList);
        return "memberList";
    }

    @GetMapping("/main")
    public String main(HttpSession httpSession) {
        if (httpSession.getAttribute("loginEmail") != null) {
            return "memberMain";
        }
        return "index";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") Long id) {
        memberService.delete(id);
        return "redirect:/list";

    }

    @PostMapping("/detail")
    public @ResponseBody MemberDTO detail(@RequestParam("memberId")Long id, Model model){
        MemberDTO memberDTO = memberService.detail(id);
        return memberDTO;
    }

    @GetMapping("/update")
    public String updateForm(HttpSession session, Model model) {
        // 세션에 저장된 이메일 꺼내기
        String memberEmail = (String) session.getAttribute("loginEmail");
        MemberDTO memberDTO = memberService.findByMemberEmail(memberEmail);
        model.addAttribute("member", memberDTO);
        return "memberUpdate";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute MemberDTO memberDTO) {
        memberService.update(memberDTO);
        return "memberMain";
    }

    @PostMapping("/email-check")
    public @ResponseBody String emailcheck (@RequestParam("memberEmail") String memberEmail){
        MemberDTO memberDTO = memberService.findByMemberEmail(memberEmail);
        if(memberDTO == null){
            return "yes";
        }
        return "no";
    }


}
