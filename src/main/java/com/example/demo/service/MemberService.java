package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.example.demo.dao.MemberDAO;
import com.example.demo.vo.MemberVO;

@Service
public class MemberService {
	
	@Autowired
	MemberDAO memberDAO;
	
	public void memberInsert(MemberVO memberVO) throws DataAccessException{
		memberDAO.insertMember(memberVO);
	}
	
	public String login(MemberVO memberVO)  throws DataAccessException{
		return memberDAO.login(memberVO);
	}
	public int idCheck(String id) {
		
		return memberDAO.idCheck(id);
	}

	public MemberVO selectAllInfo(String id) throws DataAccessException{
		
		return memberDAO.selectAllInfo(id);
	}

	public boolean selectPw(String id, String originalPw) throws DataAccessException{
		
		String pw = (String)memberDAO.selectPw(id);
		if(pw.equals(originalPw)) {
			return true;
		}else {
			return false;
		}
		 
	}

	public void changeInfo(String id, String cpw, String phone, String email) throws DataAccessException{
		memberDAO.changeInfo(id, cpw, phone, email);
		
	}
}
