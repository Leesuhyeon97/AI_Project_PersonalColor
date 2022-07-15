package com.example.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import com.example.demo.vo.BoardVO;
import com.example.demo.vo.ScrapVO;

@Mapper
@Repository
public interface BoardDAO {
	
	public List<BoardVO> boardList() throws DataAccessException;

	public void boardWrite(BoardVO boardVO)throws DataAccessException;

	public BoardVO viewArticle(int no) throws DataAccessException;

	public void replyWrite(BoardVO boardVO) throws DataAccessException;

	public void delete(int no) throws DataAccessException;
	
	public void update(BoardVO boardVO) throws DataAccessException;
	
	public List<BoardVO> search(BoardVO boardVO) throws DataAccessException;
	
	public List<BoardVO> selectScrap(BoardVO boardVO) throws DataAccessException;
	
	

}

