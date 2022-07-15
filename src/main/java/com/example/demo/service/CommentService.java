package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.CommentDAO;
import com.example.demo.vo.CommentVO;

@Service
public class CommentService {

	@Autowired
	CommentDAO commentDAO;
	
	
	public  List<CommentVO> commentList(int bno) {
	 return commentDAO.commentList(bno);
	 }


	public void commentInsert(CommentVO comment) {
	
		commentDAO.commentInsert(comment);
	}


	public void commentDelete(int cno) {
		commentDAO.commentDelete(cno);
		
	}


	public void commentUpdate(CommentVO commentVO) {
		commentDAO.commentUpdate(commentVO);
		
	}
	
	public String selectWriter(int cno) {
		return commentDAO.selectWriter(cno);
	}

}
