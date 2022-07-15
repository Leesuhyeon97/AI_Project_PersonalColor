package com.example.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.example.demo.vo.ScrapVO;

@Mapper
@Repository
public interface ScrapBoxDAO {

	public void insertScrap(ScrapVO scrapVO) throws DataAccessException;

	public List<ScrapVO> scrapList(ScrapVO scrapVO) throws DataAccessException;

	public ScrapVO viewScrapArticle(int no) throws DataAccessException;

	public void deleteScrap(int no) throws DataAccessException;
}
