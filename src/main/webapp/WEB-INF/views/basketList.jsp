<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>옛다 컬너 - My Color Box</title>
  <style>
    .navbar-custom {
      height: 50px;
    }
    
    header {
      height: 50px;
    }

    body {
      background-image: url('../img/gradient-bg.jpg');
      background-size: cover;
    }

    section {
      height: 300px;
      width: 100%;
      
      display: flex;
      justify-content: center;
      
    }

    .container {
      height: 300px;
      width: 100%;
    }

    .colorBox {
      width: 80%;
      height: 200px;
      background-color: rgba(255,255,255,0.8);
      box-shadow: 20px 20px 50px grey;
      margin-bottom: 20px;
      padding-top : 20px;
      padding-left : 50px;
      padding-right : 50px;
    }

    #sectionline {
      padding-left: 30px;
      padding-right: 30px;
      margin-top: 40px;
    }

    #colorBasket {
      padding: 30px;
      height: 400px;
      justify-content: center;
      display: flex;
    }

    #basketDiv {
      outline: 1px solid gray;
      padding: 20px;
      width: 80%;
      background-color: rgba(255, 255, 255, 0.8);

    }
    .box {
   width: 30px;
   height: 30px;
   outline: 1px;
   outline-style: solid;
   outline-color: gray;
   margin-left:90px;
}
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>   
 
 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  import="com.example.demo.vo.BoardVO, java.util.List" %>
    
  </style>
  <!--jquery lib-->
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-cookie/1.4.1/jquery.cookie.min.js" integrity="sha512-3j3VU6WC5rPQB4Ld1jnLV7Kd5xr+cq9avvhwqzbH/taCRNURoeEpoPBK9pDyeukwSxwRPJ8fDgvYXd6SkaZ2TA==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
  <!--bootstrap css-->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">

  <!--javascript 영역-->
  <script src="../js/my.js"></script>
  
  <script>
    
  $(document).ready(function(){
		
		const id = $.cookie("id");
		if(!id){
			 alert("로그인 후 이용 가능합니다.")
			    window.close();
		}

		  $("#id").val(id);
	         $(document).on("click","#deleteBtn",function(){
	            var deleteBtn = $(this);
	            var tr = deleteBtn.parent().parent();
	            var td = tr.children();
	            console.log("클릭한 Row의 모든 데이터 : "+tr.text());
	            var no =td.eq(0).text();
	            console.log(no);
	            
	            $.post('deleteRow',{no},function(){
	               
	               location.reload();
	               alert("정보 삭제 완료!!");
	            });
	         });
	       
	      	const pcolor = $.cookie("pColor";)
	         if(pcolor == '봄 웜톤') {
	        	 $("#bestColor").attr('src','../getImg/spring_palette.png')
	        	 $("#worstColor").attr('src','../getImg/winter_palette.png')
	         }else if(pcolor == '여름 쿨톤') {
	        	 $("#bestColor").attr('src','../getImg/summer_palette.png')
	        	 $("#worstColor").attr('src','../getImg/autumn_palette.png')
	         }else if(pcolor == '가을 웜톤') {
	        	 $("#bestColor").attr('src','../getImg/autumn_palette.png')
	        	 $("#worstColor").attr('src','../getImg/summer_palette.png')
	         }else if (pcolor == '겨울 쿨톤') {
	        	 $("#bestColor").attr('src','../getImg/winter_palette.png')
	        	 $("#worstColor").attr('src','../getImg/spring_palette.png')
	         }
	      });

	      


      
    

    
  </script>
</head>
<body>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <!--Navigation-->
  <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top navbar-custom">
    <div class="container-fluid align-items-center">
      <a class="navbar-brand" href="#">
        <img src="../img/logo.png" alt="" width="30" height="24">
      </a>
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarTogglerDemo02" aria-controls="navbarTogglerDemo02" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarTogglerDemo02">
        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
          <li class="nav-item">
            <a class="nav-link" aria-current="page" href="index.html">Home</a>
          </li>
          <li class="nav-item">
            <a class="nav-link active" aria-current="page" href="colorBox.html">MyColorBox</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#" onclick='window.open("boardList", "_blank", "toolbar=yes,scrollbars=yes,resizable=yes,top=100,left=300,width=800,height=500");'>Community</a>
          </li>
        </ul>
        <form>
        <span class="d-flex align-items-center" id="loginSpan">
          
            <input class="form-control me-2 form-control-sm " style="background-color: lightgrey;" placeholder="ID" aria-label="ID" id="id">
            <input class="form-control me-2 form-control-sm " style="background-color: lightgrey;" placeholder="PW" aria-label="PW" id="pw" type="password">
            <button class="btn btn-outline-light btn-sm" id="loginBtn">Login</button>        
        </span>
      </form>
      </div>
    </div>
  </nav> 
  <!--header 영역-->
  <header>

  </header>

  <!--My Color Box 영역-->
  <section>
    <div class="container">
      <center>
        <div class="mt-4">
          <h2 style="font-weight: bold; color: rgb(51, 51, 51);"><img src="../img/model.png" width="50px">My Color Box</h2>
        </div> 
        <div class="colorBox mt-4" id="colorBox">
          <div id="pcolor1" class="mt-3">
          
            <h4 style="font-weight: bold; color: rgb(51, 51, 51);">${id }님의 퍼스널컬러는 ${pColor }</h4>
          </div>
          <div id="bestColorDiv" class="mt-4 d-flex justify-contents-center align-items-center" style="color: gray;"> 
          	Best Color : <img id="bestColor" width="300px" src="" alt=""/> 
          </div>
          <div id="worstColorDiv" class="mt-2 d-flex justify-contents-center align-items-center" style="color: gray;">
          	Worst Color : <img id="worstColor" width="300px" src="" alt=""/>
          </div>
          
        </div>
        </center>
    </div>
  </section>
  <div id="sectionline">
    <hr>
  </div>

  <!--내조합 저장소-->
  <div id="colorBasket" class="mt-4">
     
    <div id="basketDiv" style="text-align: center;">
        <table class="table table-hover" id ="table">
        <thead>
          <tr>
             <th scope="col" class="hidden">no</th>
            <th scope="col">ID</th>
           
            <th scope="col">상의</th>
            <th scope="col">하의</th>
            <th scope="col">신발</th>
            <th scope="col"></th>
          </tr>
        </thead>
        <tbody>
          <tr>
             <c:forEach items="${comblist }" var="article">
                            
                        <tr>
                           <td class="hidden">${article.no }</td>
                           <td>${article.id }</td>
                           <td><div id="topDiv" class="box" style="background-color:${article.top }"> </div></td>
                           <td><div id="bottomDiv" class="box" style="background-color:${article.bottom }"></div></td>
                           <td><div id="shoesDiv" class="box" style="background-color:${article.shoes }"> </div></td>
                           <td><input type="submit" value="Delete" id="deleteBtn"/></td>
                        </tr>
                     </c:forEach>
          </tr>
          
        </tbody>
      </table>


      <div id="simulBtnDiv" class="mt- d-flex justify-content-center ">
        <button class="btn" onclick='window.open("simulator", "_blank", "toolbar=yes,scrollbars=yes,resizable=yes,top=100,left=300,width=600,height=700");' style="background-color: rgb(142, 68, 173); color: white; font-weight: bold;">색조합 시뮬레이터</button>
      </div>

    </div>


  </div>

  
  
</body>
</html>