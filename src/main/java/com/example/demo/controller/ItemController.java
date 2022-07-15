package com.example.demo.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.service.ColorTestService;
import com.example.demo.service.ItemService;
import com.example.demo.vo.ItemVO;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class ItemController {
	@Autowired
	ColorTestService colorTestService;
	@Autowired
	ItemService itemService;
	
	List<ItemVO> itemList;
	//String pColor;
	
	@RequestMapping("selectItem")
	@ResponseBody
	public String selectItem(MultipartFile image, HttpSession session, ItemVO itemVO, String pColor) {
		//경로설정하고, db 이미지경로 받아오고, 세션에서 pColor값 받아오고
		//설정한 경로와 db경로가 같은지 확인하고 맞으면 js로 넘기기 
		
		
		
		System.out.println(pColor);
		
				try {
					if(pColor!=null) {//pColor가 있다면 db에서 데이터 가져오기
						System.out.println("dsdsd");
						itemList=itemService.selectItem(pColor);
						System.out.println(itemList);
						JSONArray ja =new JSONArray(itemList);
						System.out.println(ja);
						
						return ja.toString();
					}else {
						System.out.println("pColor==null");
					}
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					
				}
				return "error!"; 
	}
	
	@RequestMapping("item")
	@ResponseBody
	public ModelAndView showItem(HttpSession session,String pColor) {
		ModelAndView mav = new ModelAndView();
		pColor = (String)session.getAttribute("pColor");
		
		return mav;
	}
			
}
