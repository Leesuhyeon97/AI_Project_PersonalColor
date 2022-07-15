package com.example.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.example.demo.vo.CommentVO;

@Mapper
@Repository
public interface CommentDAO {

	  // 댓글 개수
    public int commentCount() throws DataAccessException;
 
    // 댓글 목록
    public List<CommentVO> commentList(int bno) throws DataAccessException;
 
    // 댓글 작성
    public void commentInsert(CommentVO comment) throws DataAccessException;
    
    // 댓글 수정
    public int commentUpdate(CommentVO comment) throws DataAccessException;
 
    // 댓글 삭제
    public int commentDelete(int cno) throws DataAccessException;
    
    public String selectWriter(int cno) throws DataAccessException;

	
}
