package com.example.demo.controller;

import java.io.File;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.service.CelebrityDetectionService;
import com.example.demo.service.ColorBoxService;
import com.example.demo.service.ColorTestService;
import com.example.demo.service.ObjectDetectionService;
import com.example.demo.service.PColorDetectionService;
import com.example.demo.service.PathVar;
import com.example.demo.service.PersonDetectionService;
import com.example.demo.vo.ColorBoxVO;
import com.example.demo.vo.ColorTestVO;
import com.example.demo.vo.MemberVO;

import java.awt.image.BufferedImage;


@Controller
public class ColorTestController {

	
	@Autowired
	PColorDetectionService getColorService;

	@Autowired
	ColorTestService colorTestService;

	List<ColorTestVO> testList;

	@Autowired
	PersonDetectionService personDetectionService;

	@Autowired
	ObjectDetectionService objectDetectionService;

	@Autowired
	ColorBoxService colorBoxService;


	String pColor;



	
	@PostMapping("getPcolor")
	@ResponseBody
	public String getPcolor(MultipartFile image, HttpSession session) {
		
	
	
		System.out.println(image.getOriginalFilename());

		int no = 0, sum = 0, temp = 1000;
		int[] a = new int[3];
		JSONArray ja = new JSONArray();
		JSONObject jo1 = new JSONObject();// 사람탐지
		JSONObject jo2 = new JSONObject();// 얼굴탐지
		JSONObject jo3 = new JSONObject();// pcolor
		/// home/ubuntu/0csh/git_registry/test-1/media

		try {
			File uploadFile = new File(PathVar.LOCAL_DRIVE + image.getOriginalFilename());

			testList = colorTestService.selectAllType();
			image.transferTo(uploadFile);
			String result1 = personDetectionService.detectPerson(uploadFile);
			jo1.put("result1", result1);
			ja.put(jo1);
			if (result1 == "사람탐지") {
				JSONObject result2 = objectDetectionService.objectDetect(uploadFile);
				jo2.put("result2", result2);
				ja.put(jo2);
				JSONObject test = (JSONObject) jo2.get("result2");
				test = (JSONObject) test.get("info");
				int faceCount = test.getInt("faceCount");
				if (faceCount == 1) {
					a = getColorService.getColor(uploadFile);
					if (testList != null) {
						for (int i = 0; i < 8; i++) {
							sum += Math.abs(testList.get(i).getRed() - a[0]);
							sum += Math.abs(testList.get(i).getBlue() - a[2]);
							sum += Math.abs(testList.get(i).getGreen() - a[1]);

							if (sum < temp) {
								temp = sum;
								no = i + 1;
							}
							sum = 0;
						}
					} else {
						System.out.println("colorTestVO is null");
						jo3.put("msg", "퍼스널컬러 기준 테이블 없음");
					}

					pColor = colorTestService.selectPeronalType(no);
					jo3.put("pColor", pColor);
					if (session.getAttribute("memberVO") != null) {
						session.setAttribute("pColor", pColor);

					}
					ja.put(jo3);
					return ja.toString();
				}else {
					return "manyFaces";
				}

			}else {
				return "nullPerson";
			}

		} catch (IllegalStateException e1) {
		
			e1.printStackTrace();
			jo3.put("msg", "ISEerror");
			ja.put(jo3);
		} catch (IOException e1) {

			e1.printStackTrace();
			jo3.put("msg", "IOEerror");
			ja.put(jo3);
		} catch (Exception e) {

			e.printStackTrace();
			jo3.put("msg", "error");
		}
		
		 System.out.println(ja.toString());
		return ja.toString();
	}

	

}