package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.example.demo.dao.BoardDAO;
import com.example.demo.dao.ScrapBoxDAO;
import com.example.demo.vo.BoardVO;
import com.example.demo.vo.ScrapVO;

@Service
public class BoardService {
	
	@Autowired
	BoardDAO boardDAO;
	
	@Autowired
	ScrapBoxDAO scrapBoxDAO;
	
	public List<BoardVO> boardList(){
		return boardDAO.boardList();
	}

	public void boardWrite(BoardVO boardVO) {
			boardDAO.boardWrite(boardVO);
	}

	public BoardVO viewArticle(int no) {
		return boardDAO.viewArticle(no);
		
	}

	public void replyWrite(BoardVO replyVO) {
		boardDAO.replyWrite(replyVO);
		
	}
	
	public void delete(int no) {
		boardDAO.delete(no);
	}
	
	public void update(BoardVO boardVO) {
		boardDAO.update(boardVO);
	}
	
	public List<BoardVO> search(BoardVO boardVO) {
		return boardDAO.search(boardVO);
		
	}
	
	public List<BoardVO> selectScrap(BoardVO boardVO){
		return boardDAO.selectScrap(boardVO);
	}

	public void insertScrap(ScrapVO scrapVO) {		
		scrapBoxDAO.insertScrap(scrapVO);
	}

	public List<ScrapVO> scrapList(ScrapVO scrapVO) {
		return scrapBoxDAO.scrapList(scrapVO);
		
	}

	public ScrapVO viewScrapArticle(int no) {		
		return scrapBoxDAO.viewScrapArticle(no);
	}
	
	public void deleteScrap(int no) {
		scrapBoxDAO.deleteScrap(no);
	}
}
