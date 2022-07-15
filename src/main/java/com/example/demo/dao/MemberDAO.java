package com.example.demo.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.example.demo.vo.MemberVO;

@Mapper
@Repository
public interface MemberDAO {
	
	public void insertMember(MemberVO memberVO) throws DataAccessException;
	public String login(MemberVO memberVO) throws DataAccessException;
	public int idCheck(String id) throws DataAccessException;
	public MemberVO selectAllInfo(String id) throws DataAccessException;
	public String selectPw(String id)throws DataAccessException;
	public void changeInfo(String id, String cpw, String phone, String email);
}
