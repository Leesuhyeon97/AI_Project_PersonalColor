<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!--jquery lib-->
<script
   src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
   src="https://cdnjs.cloudflare.com/ajax/libs/jquery-cookie/1.4.1/jquery.cookie.min.js"
   integrity="sha512-3j3VU6WC5rPQB4Ld1jnLV7Kd5xr+cq9avvhwqzbH/taCRNURoeEpoPBK9pDyeukwSxwRPJ8fDgvYXd6SkaZ2TA=="
   crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<!--bootstrap css-->
<link
   href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
   rel="stylesheet"
   integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
   crossorigin="anonymous">
<script
   src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
   integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
   crossorigin="anonymous"></script>
<link rel="stylesheet"
   href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">

<!--javascript 영역-->
<script src="../js/my.js"></script>
<script>
   $(document).ready(function() {
                  const id = $.cookie("id");
                  $("#id").val(id);
                  console.log(id);
                  $(document).on('click', '#insertColorComb', function() {
                     const id = $('#id').val();
                     console.log(id);
                     var top = document.getElementById("top");
                     top = top.options[top.selectedIndex].text;
                     var bottom = document.getElementById("bottom");
                     bottom = bottom.options[bottom.selectedIndex].text;
                     var shoes = document.getElementById("shoes");
                     shoes = shoes.options[shoes.selectedIndex].text;
                     console.log(top);
                     console.log(bottom);
                     console.log(shoes);

                     var data = {
                        "id" : id,
                        "top" : top,
                        "bottom" : bottom,
                        "shoes" : shoes
                     };
                     console.log(data);

                     $.ajax({
                        type: "post",
                          url : "insertColorComb", 
                          data : data,

                        success : function() {
                           alert("저장완료");
                           opener.location.reload();
                           window.close();
                        },
                        error : function(e) {
                           console.log("ERROR : ", e);
                        }
                     });
                  });

                  $("#topsaveBtn").on('click',function(){
                      var tcode = $("#top> option:selected").text();
                      
                      document.querySelector("#topcolorDiv").style.backgroundColor = tcode;
                    });

                    $("#btmsaveBtn").on('click',function(){
                      var bcode = $("#bottom> option:selected").text();
                      	
                      document.querySelector("#btmcolorDiv").style.backgroundColor = bcode;
                    });

                    $("#shsaveBtn").on('click',function(){
                      var scode = $("#shoes> option:selected").text();
                      
                      document.querySelector("#shcolorDiv").style.backgroundColor = scode;
                    });
               });
</script>
<style>
body {
   background-image: url('../img/gradient-bg.jpg');
   background-size: cover;
   display: flex;
   align-items: center;
   justify-content: center;
}

#simulatorDiv {
   background-color: white;
   width: 90%;
   height: 95%;
   text-align: center;
}

.simul-container {
   align-self: center;
   padding-left: 50px;
   padding-right: 50px;
   height: 550px;
}

.box {
   width: 50px;
   height: 50px;
   outline: 1px;
   outline-style: solid;
   outline-color: gray;
}
</style>

<title>컬러 시뮬레이터</title>
</head>
<body>
   <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

   <!--색조합 시뮬레이터-->
   <%@ page language="java" contentType="text/html; charset=UTF-8"
      pageEncoding="UTF-8"
      import="com.example.demo.vo.BoardVO, java.util.List"%>
   <div id="simulatorDiv" class="mt-5">
      <div class="d-flex justify-content-center mt-4">
         <h4 style="font-weight: bold; color: rgb(51, 51, 51);">색 조합
            시뮬레이터</h4>
      </div>

      <div id="simulContainer" class="simul-container mt-4">
         <!--얼굴 색 선택-->
         <div class="d-flex align-items-center justify-content-evenly">
            <div id="faceLabel" style="text-align: center;">
               <h5>얼굴</h5>

              
            </div>
            <div id="fcolorDiv" class="box"
               style="background-color: rgb(251, 211, 168);"></div>
         </div>

         <div class="mt-3 mb-3">
            <hr>
         </div>

         <!--상의 색상 선택-->


         <input name="id" id="id" type="hidden"><br>
         <div class="d-flex align-items-center justify-content-evenly mb-4">
            <div id="topLabel" style="text-align: center;">
               <h5>상의</h5>

               <select id="top" name="top" class="form-select form-select-sm"
                  aria-label="Default select example">
                  <option selected>상의 색상 선택</option>
                  <c:forEach items='${color }' var="name">
                     <option style="background-color:${name}">${name }</option>

                  </c:forEach>
               </select>
               <button id="topsaveBtn" class="btn btn-sm btn-dark mt-2">색 표현</button>
            </div>

            <div id="topcolorDiv" class="box"></div>
         </div>

         <!--하의 색상 선택-->
         <div class="d-flex align-items-center justify-content-evenly mb-4">
            <div id="btmLabel" style="text-align: center;">
               <h5>하의</h5>
               <select id="bottom" name="bottom"
                  class="form-select form-select-sm"
                  aria-label="Default select example">
                  <option selected>하의 색상 선택</option>
                  <c:forEach items='${color }' var="name">
                     <option style="background-color:${name}">${name }</option>

                  </c:forEach>
               </select>
               <button id="btmsaveBtn" class="btn btn-sm btn-dark mt-2" >색 표현</button>
            </div>
            <div id="btmcolorDiv" class="box"></div>
         </div>

         <!--신발 색상 선택-->
         <div class="d-flex align-items-center justify-content-evenly mb-4">
            <div id="shoeLabel" style="text-align: center;">
               <h5>신발</h5>
               <select id="shoes" name="shoes" class="form-select form-select-sm"
                  aria-label="Default select example">
                  <option selected>신발 색상 선택</option>
                  <c:forEach items='${color }' var="name">
                     <option style="background-color:${name}">${name }</option>

                  </c:forEach>
               </select>

               <button id="shsaveBtn" class="btn btn-sm btn-dark mt-2" >색 표현</button>
            </div>
            <div id="shcolorDiv" class="box"></div>
         </div>
         <button id="insertColorComb" class="btn btn-sm btn-dark mt-2" style="background-color: rgb(142, 68, 173); color: white; font-weight: bold;"	>색조합저장</button>


      </div>
   </div>

</body>
</html>