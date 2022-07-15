package com.example.demo.dao;

import java.io.File;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.example.demo.vo.ItemVO;

@Mapper
@Repository
public interface ItemDAO {
	
	public List<ItemVO> selectItem(String pColor) throws Exception;
}
