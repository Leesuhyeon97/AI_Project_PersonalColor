package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.ColorBoxDAO;
import com.example.demo.dao.ColorCombDAO;
import com.example.demo.vo.ColorBoxVO;
import com.example.demo.vo.ColorCombVO;

@Service
public class ColorCombService {

	@Autowired
	ColorCombDAO colorCombDAO;
	
	public void insertColorComb(ColorCombVO colorCombVO) throws Exception {
		colorCombDAO.insertColorComb(colorCombVO);
	}
	public List<ColorCombVO> selectAllColorCombList(String id){
		
		return colorCombDAO.selectAllColorCombList(id);
		
	}
	public void deleteRow(int no) {
	      colorCombDAO.deleteRow(no);
	      
	   }




}
