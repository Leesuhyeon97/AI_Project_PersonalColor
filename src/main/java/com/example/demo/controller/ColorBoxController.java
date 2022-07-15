package com.example.demo.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.example.demo.service.ColorBoxService;
import com.example.demo.service.ColorCombService;
import com.example.demo.service.PersonDetectionService;
import com.example.demo.service.ObjectDetectionService;
import com.example.demo.vo.ColorBoxVO;
import com.example.demo.vo.ColorCombVO;
import com.example.demo.vo.MemberVO;

@Controller
public class ColorBoxController {
	
	@Autowired
	ColorBoxService colorBoxService;
	
	
	
	@Autowired
	ColorCombService colorCombService;
	
	
	

	
	@PostMapping("insertPcolorInColorBox")
	@ResponseBody
	public String insertColorBox(ColorBoxVO colorBoxVO, HttpSession session, String pColor) {
		JSONObject jo = new JSONObject();

		try {
			String id = (String) session.getAttribute("id");
			
			
			if (id != null && pColor != null) {

				colorBoxVO.setId(id);
				colorBoxVO.setPColor(pColor);
				System.out.println(colorBoxService.selectAllBasketList(colorBoxVO));
				if(colorBoxService.selectAllBasketList(colorBoxVO)!=null) {
					colorBoxService.deleteAllColorBoxList(colorBoxVO);
					colorBoxService.insertColorBox(colorBoxVO);
					jo.put("success","기존 정보 삭제 후 저장되었습니다");
					
				}else {
					colorBoxService.insertColorBox(colorBoxVO);
					jo.put("success","저장되었습니다");
				}
				
				

			} else {
				jo.put("msg", "로그인 하세요");
			}
		} catch (Exception e) {
			jo.put("msg", "error");
		}
		return jo.toString();
	}
	
	@RequestMapping("basketList")
      public ModelAndView showBasket(HttpSession session, ColorBoxVO colorBoxVO) {
         System.out.println(session.getId());
         ModelAndView mav = new ModelAndView();
         String id = (String)session.getAttribute("id");
         System.out.println(id);
         if(id==null) {
            //mav.addObject("id",null);
            System.out.println("id is null");
            
            
         }else {
            colorBoxVO.setId(id);
            try {
               List<ColorBoxVO> list = colorBoxService.selectAllBasketList(colorBoxVO);
               mav.addObject("pColor", list.get(0).getPColor());
               session.setAttribute("pColor", list.get(0).getPColor());
               System.out.println(list);
               List<ColorCombVO> blist = colorCombService.selectAllColorCombList(id); 
		session.setAttribute("id",id);
               mav.addObject("blist",blist);
               mav.addObject("id",id);
               System.out.println(mav);
               
            }catch (Exception e) {
               e.printStackTrace();
            }
            
         }
         
         mav.setViewName("basketList");
         return mav;
         
   }

	@RequestMapping("simulator")	
	public ModelAndView goSimul(HttpSession session) {
		ModelAndView mav = new ModelAndView();
//		String pColor = (String)session.getAttribute("pColor");
		String id = (String)session.getAttribute("id");
		String pColor = (String)session.getAttribute("pColor");
		System.out.println(id);
		mav.addObject("id",id);
		List<String> spring = new ArrayList<String>(Arrays.asList("#D82B20","#FC5A15","#EB8157","#F8D7C4","#F0F3AE","#A6D114","#A5D311","#96DC97","#5EB372","#2C8B2C","#BDECD6","#64BCCD","#2B6AA0"));
		List<String> summer = new ArrayList<String>(Arrays.asList("#DD729E","#E79CBB","#EEC7DF","#A37BD2","#C6ACDB","#DDD6F6","#CFDFF6","#A5BEF6","#6A94E6","#4667B4","#C5E7F1","#B6ECD3","#EEF5B1"));
		List<String> autumn = new ArrayList<String>(Arrays.asList("#922D23","#922C20","#E68452","#F8C5AA","#E2B502","#9F7020","#52731B","#97DA98","#867352","#342812","#688B8D","#7AC4CD","#145569"));
		List<String> winter = new ArrayList<String>(Arrays.asList("#DB2F42","#D486A7","#812C53","#2D0E4E","#582D98","#28130E","#897A75","#0E3AB3","#0A172B","#316C9F","#B7BABF","#194F6E","#0E2933"));

		System.out.println(pColor);
		if(pColor.equals("봄 웜톤")) {
			mav.addObject("color",spring);
		}else if(pColor.equals("여름 쿨톤")) {
			mav.addObject("color",summer);
		}else if(pColor.equals("가을 웜톤")) {
			mav.addObject("color",autumn);
		}else if(pColor.equals("겨울 쿨톤")) {
			mav.addObject("color",winter);
		}else {
			System.out.println("pColor값 오류");
		}
		return mav;
	}
	
	@PostMapping("insertColorComb")
	
	public RedirectView insertColorComb(ColorCombVO colorCombVO) {
		System.out.println(colorCombVO);
		try {
			colorCombService.insertColorComb(colorCombVO);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new RedirectView("simulator");
	}
	@PostMapping("deleteRow")
	   @ResponseBody
	   public RedirectView deleteRow(int no) {
	      System.out.println(no);
	      try {
	         colorCombService.deleteRow(no);
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
	      return new RedirectView("basketList");
	   }

	
}


