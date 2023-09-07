package com.icia.member.service;

import com.icia.member.dto.MemberDTO;
import com.icia.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;

    public void save(MemberDTO memberDTO) {
        memberRepository.save(memberDTO);
    }

    public MemberDTO login(MemberDTO memberDTO){
        return memberRepository.login(memberDTO);
    }

    public List<MemberDTO> list(){
        return memberRepository.list();
    }

    public void delete(Long id){
        memberRepository.delete(id);
    }

    public MemberDTO detail(Long id){
        return memberRepository.detail(id);
    }

    public void update(MemberDTO memberDTO) {
        memberRepository.update(memberDTO);
    }

    public MemberDTO findByMemberEmail(String memberEmail) {
        return memberRepository.findByMemberEmail(memberEmail);
    }
}
