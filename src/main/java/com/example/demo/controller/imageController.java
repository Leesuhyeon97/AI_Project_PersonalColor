package com.example.demo.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class imageController {

	@RequestMapping("getImg/{imageName}")
	@ResponseBody
	public byte[] getImage(@PathVariable(name = "imageName") String imageName,
			HttpServletRequest request)
			throws IOException {
		
		File imageFile = new File(PathVar.FIND_DRIVE + imageName);
		InputStream imageStream = new FileInputStream(imageFile);
		byte[] imageByteArray = IOUtils.toByteArray(imageStream);
		imageStream.close();

		return imageByteArray;
	}
}
