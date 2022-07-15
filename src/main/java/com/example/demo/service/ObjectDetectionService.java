package com.example.demo.service;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.*;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import javax.imageio.ImageIO;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

// Object Detection API 예제
@Service
public class ObjectDetectionService {
	
	
     String imgFormat = "png";                             
	 int newWidth = 200;                                  // 변경 할 넓이
     int newHeight = 260;                                 // 변경 할 높이
     String mainPosition = "X";                             // W:넓이중심, H:높이중심, X:설정한 수치로(비율무시)

     Image image;
     int imageWidth;
     int imageHeight;
     double ratio;
     int w;
     int h;
    public  JSONObject objectDetect(File uploadFile) {
    	try {
        Files.copy(uploadFile.toPath(), new File(PathVar.imgOriginalPath).toPath(),StandardCopyOption.REPLACE_EXISTING );

    	JSONObject jo1 = new JSONObject();
    	int[] a = new int[3];
    	
    	image = ImageIO.read(new File(PathVar.imgOriginalPath));
    	 
        // 원본 이미지 사이즈 가져오기
        imageWidth = image.getWidth(null);
        imageHeight = image.getHeight(null);

        if(mainPosition.equals("W")){    // 넓이기준

            ratio = (double)newWidth/(double)imageWidth;
            w = (int)(imageWidth * ratio);
            h = (int)(imageHeight * ratio);

        }else if(mainPosition.equals("H")){ // 높이기준

            ratio = (double)newHeight/(double)imageHeight;
            w = (int)(imageWidth * ratio);
            h = (int)(imageHeight * ratio);

        }else{ //설정값 (비율무시)

            w = newWidth;
            h = newHeight;
        }

        // 이미지 리사이즈
        // Image.SCALE_DEFAULT : 기본 이미지 스케일링 알고리즘 사용
        // Image.SCALE_FAST    : 이미지 부드러움보다 속도 우선
        // Image.SCALE_REPLICATE : ReplicateScaleFilter 클래스로 구체화 된 이미지 크기 조절 알고리즘
        // Image.SCALE_SMOOTH  : 속도보다 이미지 부드러움을 우선
        // Image.SCALE_AREA_AVERAGING  : 평균 알고리즘 사용
        Image resizeImage = image.getScaledInstance(w, h, Image.SCALE_SMOOTH);
        

        // 새 이미지  저장하기
        BufferedImage newImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        Graphics g = newImage.getGraphics();
        g.drawImage(resizeImage, 0, 0, null);
        g.dispose();
        ImageIO.write(newImage, imgFormat, new File(PathVar.imgResizePath));
        File newFile = new File(PathVar.imgResizePath);
        
        StringBuffer reqStr = new StringBuffer();
        String clientId = "s9vnr4drhx";//애플리케이션 클라이언트 아이디값";
        String clientSecret = "KqROzDH0ggHpaiZvRq5byUECOjLCA8VSDvgBoh8a";//애플리케이션 클라이언트 시크릿값";

      
        	
            String paramName = "image"; // 파라미터명은 image로 지정
           //String imgFile = "D:\\temp\\smile.jpg";
           // File uploadFile = new File(imgFile);
            String apiURL = "https://naveropenapi.apigw.ntruss.com/vision/v1/face"; // 얼굴 감지
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setUseCaches(false);
            con.setDoOutput(true);
            con.setDoInput(true);
            // multipart request
            String boundary = "---" + System.currentTimeMillis() + "---";
            con.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
            con.setRequestProperty("X-NCP-APIGW-API-KEY-ID", clientId);
            con.setRequestProperty("X-NCP-APIGW-API-KEY", clientSecret);
            OutputStream outputStream = con.getOutputStream();
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(outputStream, "UTF-8"), true);
            String LINE_FEED = "\r\n";
            // file 추가
            String fileName = newFile.getName();
            writer.append("--" + boundary).append(LINE_FEED);
            writer.append("Content-Disposition: form-data; name=\"" + paramName + "\"; filename=\"" + fileName + "\"").append(LINE_FEED);
            writer.append("Content-Type: "  + URLConnection.guessContentTypeFromName(fileName)).append(LINE_FEED);
            writer.append(LINE_FEED);
            writer.flush();
            FileInputStream inputStream = new FileInputStream(newFile);
            byte[] buffer = new byte[4096];
            int bytesRead = -1;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            outputStream.flush();
            inputStream.close();
            writer.append(LINE_FEED).flush();
            writer.append("--" + boundary + "--").append(LINE_FEED);
            writer.close();
            BufferedReader br = null;
            int responseCode = con.getResponseCode();
            if(responseCode==200) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {  // 오류 발생
//                System.out.println("error!!!!!!! responseCode= " + responseCode);
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            }
            String inputLine;
            if(br != null) {
                StringBuffer response = new StringBuffer();
                while ((inputLine = br.readLine()) != null) {
                    response.append(inputLine);
                }
                br.close();
//                System.out.println(response.toString());
                
				BufferedImage bimg = ImageIO.read(uploadFile); 
				int width = bimg.getWidth();
				int height = bimg.getHeight();
//				System.out.println(response.toString());
                JSONObject jo=new JSONObject(response.toString());    
                jo.put("width", width);
                jo.put("height", height);
                System.out.println("newFile:"+newFile.getAbsolutePath());
                return jo;
            }
        } catch (Exception e) {
//            e.printStackTrace();
            
        }
    	return null;
    }
}


