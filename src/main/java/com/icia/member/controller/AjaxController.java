package com.icia.member.controller;

import com.icia.member.dto.MemberDTO;
import com.icia.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Member;
import java.util.List;

@Controller
public class AjaxController {
    @GetMapping("/ajax01")
    public String ajax01(){

        return "index";

    }
    @GetMapping("/ajax02")
    public String ajax02(){

        return "index";
        // ajax에서 index로 리턴했을 시에 index.jsp로 돌아가지 않는다
        // 그러한 방법으로 하고싶을 때는 ajax success메뉴에서 페이지를 돌려주어야함
    }

    @GetMapping(value = "/ajax03", produces = "application/text; charset=utf-8")
    public @ResponseBody String ajax03(){
        String returnValue = "리턴 값입니다.";
        return returnValue + "리턴값입니다";
    }

    @GetMapping("/ajax04")
    public @ResponseBody String ajax04(@RequestParam("p1") String p1, @RequestParam("p2") String p2){
        System.out.println("p1 = " + p1 + ", p2 = " + p2);
        return "ok";
    }

    @PostMapping("/ajax05")
    public @ResponseBody String ajax05(@RequestParam("p1") String p1, @RequestParam("p2") String p2){
        System.out.println("p1 = " + p1 + ", p2 = " + p2);
        return "good";
    }

    @PostMapping("/ajax06")
    public @ResponseBody String ajax06(@ModelAttribute MemberDTO memberDTO){
        System.out.println("memberDTO = " + memberDTO);
        return "great";
    }
    @Autowired
    private MemberService memberService;

    @GetMapping("/ajax07")
    public @ResponseBody MemberDTO ajax07() {
        MemberDTO memberDTO = memberService.detail(1L);
        return memberDTO;
    }

    @GetMapping("/ajax08")
    public @ResponseBody List<MemberDTO> ajax08(){
        List<MemberDTO> memberDTOList = memberService.list();
                return memberDTOList;
    }

    @PostMapping("/ajax09") //JSON형태로 받는 데이터 또한 @RequsetBody로 받는다.
    public @ResponseBody MemberDTO ajax09(@RequestBody MemberDTO memberDTO){
        System.out.println("memberDTO = " + memberDTO);
        return memberDTO;
    }

    @PostMapping("/ajax10")
    public @ResponseBody List<MemberDTO> ajax10(@ModelAttribute MemberDTO memberDTO){
        memberService.save(memberDTO);
        List<MemberDTO> memberDTOList = memberService.list();
        return memberDTOList;
    }
    @PostMapping(value = "/ajax11")
    // ResponseEntity = 데이터와, 코드를 함께 둘 수있게하는 것.(오류거를 때 - ex : try/catch문을 쓸 때)
    // ResponseBody = 데이터만 전송

    // ResponseBody를 쓰는 이유 : @ResponseBody에 데이터를 담아 전송하게 되면 Spring에 있는 기능들이
    // 필요한 형변환 등을 자동으로 해줌, 따라서 데이터를 받을 때에도 다른 형태라면 ResponseBody를 써줘야하고
    // ResponseBody를 쓰지 않았을 경우 데이터를 주고 받을 때 형변환을 해주는 등의 추가 작업이 이루어져야함.

    public ResponseEntity ajax11(@ModelAttribute MemberDTO memberDTO){
        try{
            memberService.save(memberDTO);
        } catch(Exception e){
            // 이메일이 중복되는 상황에서 Conflict라는 응답코드를 준다.
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        List<MemberDTO> memberDTOList = memberService.list();
        // 문제가 없다면 회원리스트 데이터와 200코드를 응답으로 준다.
        return new ResponseEntity<>(memberDTOList, HttpStatus.OK);
    }
}
