package com.example.demo.service;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.ItemDAO;
import com.example.demo.vo.ItemVO;

@Service
public class ItemService {
	@Autowired
	ItemDAO itemDAO;

	public List<ItemVO> selectItem(String pColor) throws Exception{
		return itemDAO.selectItem(pColor);
	}
}
