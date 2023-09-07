package com.icia.member.repository;

import com.icia.member.dto.MemberDTO;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MemberRepository {
    @Autowired
    private SqlSessionTemplate sql;

    public void save(MemberDTO memberDTO) {
        sql.insert("Member.save", memberDTO);
    }

    public MemberDTO login(MemberDTO memberDTO) {
        return sql.selectOne("Member.login", memberDTO);
    }

    public List<MemberDTO> list(){
        return sql.selectList("Member.findList");
    }

    public void delete(Long id){
        sql.delete("Member.delete", id);
    }

    public MemberDTO detail(Long id){
        return sql.selectOne("Member.findById", id);
    }

    public MemberDTO findByMemberEmail(String memberEmail) {
        return sql.selectOne("Member.findByEmail", memberEmail);
    }
    public void update(MemberDTO memberDTO) {
        sql.update("Member.update", memberDTO);
    }
}
