package com.example.demo.controller;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.service.MemberService;
import com.example.demo.vo.MemberVO;

@Controller
public class MemberController {
	
	@Autowired
	MemberService memberService;
	
	@PostMapping("idCheck")
	@ResponseBody
	public int idCheck(@RequestParam("id") String id) {

		return memberService.idCheck(id);
	}
	@RequestMapping("memberInsert")
	public String memberInsert(MemberVO memberVO) {

		
		try {
			if(memberVO.toString().contains("error")) {
				System.out.println("memberVO NullPointException");
				return "error";
			}else {
				memberService.memberInsert(memberVO);
				return "memberInsertOk";
			}
		}catch(DataAccessException e) {
			return "error";
		}
	}
	
	@RequestMapping("login")
	@ResponseBody
	public String login(HttpSession session,MemberVO memberVO) {
			
		JSONObject jo=new JSONObject();
		
		if(memberVO.getId() ==null || memberVO.getId().equals("") || 
				memberVO.getPw()==null || memberVO.getPw().equals("")) {
			jo.put("msg", "id와 pw는 필수입니다");
			return jo.toJSONString();
		}
		try {
			String id =memberService.login(memberVO);
			System.out.println(id);
			if(id==null || id.equals("")) {
				jo.put("msg", "id와 pw를 확인하세요");	
			}else {
				session.setAttribute("id", id);				
				jo.put("id", id);			
			}
		}catch(DataAccessException e) {
			jo.put("msg", e.getMessage());
		}		
		return jo.toJSONString();
	}
	
	@RequestMapping("logout")
	@ResponseBody
	public String logout(HttpSession session) {
		JSONObject jo=new JSONObject();
		
		try {
			session.invalidate();
			
			jo.put("msg", "ok");
		}catch(Exception e) {
			jo.put("msg", e.getMessage());
		}
		
		return jo.toJSONString();
	}
	
	@PostMapping("userInfoSelect")
	@ResponseBody
	public MemberVO userInfoSelect(String id) {
		//System.out.println(memberService.selectAllInfo(id));
		return memberService.selectAllInfo(id);
	}
	@PostMapping("checkPw")
	@ResponseBody
	public boolean checkPw(String id, String originalPw) {
		//System.out.println(memberService.selectAllInfo(id));
		return memberService.selectPw(id, originalPw);
	}
	
	@PostMapping("changeInfo")
	@ResponseBody
	public void changeInfo(String id, String cpw, String phone, String email) {
		memberService.changeInfo(id, cpw, phone, email);
	}


}
