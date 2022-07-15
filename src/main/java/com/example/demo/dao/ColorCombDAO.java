package com.example.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.example.demo.vo.ColorBoxVO;
import com.example.demo.vo.ColorCombVO;


	@Mapper
	@Repository
	public interface ColorCombDAO {

		public void insertColorComb(ColorCombVO colorCombVO) throws Exception;
		public List<ColorCombVO> selectAllColorCombList(String id)throws DataAccessException;
		public void deleteRow(int no) throws DataAccessException;
	}

