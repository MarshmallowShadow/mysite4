<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>방명록</title>
		<!-- js -->
		<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.12.4.js"></script>
		
		<!-- css -->
		<link href="${pageContext.request.contextPath}/assets/css/mysite.css" rel="stylesheet" type="text/css">
		<link href="${pageContext.request.contextPath}/assets/css/guestbook.css" rel="stylesheet" type="text/css">
	</head>
	
	<body>
		<div id="wrap">
	
			<!-- //header -->
			<c:import url="/WEB-INF/views/includes/header.jsp"></c:import>
	
			<div id="container" class="clearfix">
				<div id="aside">
					<h2>방명록</h2>
					<ul>
						<li>일반방명록</li>
						<li>ajax방명록</li>
					</ul>
				</div>
				<!-- //aside -->
	
				<div id="content">
					
					<div id="content-head" class="clearfix">
						<h3>일반방명록</h3>
						<div id="location">
							<ul>
								<li>홈</li>
								<li>방명록</li>
								<li class="last">일반방명록</li>
							</ul>
						</div>
					</div>
					<!-- //content-head -->
	
					<div id="guestbook">
						<%-- 방병록 작성 폼 --%>
						<form action="${pageContext.request.contextPath}/guestbook/add" method="post">
							<table id="guestAdd">
								<colgroup>
									<col style="width: 70px;">
									<col>
									<col style="width: 70px;">
									<col>
								</colgroup>
								<tbody>
									<tr>
										<th><label class="form-text" for="input-uname">이름</label></th>
										<td><input id="input-uname" type="text" name="name"></td>
										<th><label class="form-text" for="input-pass">패스워드</label></th>
										<td><input id="input-pass"type="password" name="password"></td>
									</tr>
									<tr>
										<td colspan="4"><textarea name="content" cols="72" rows="5"></textarea></td>
									</tr>
									<tr class="button-area">
										<td colspan="4" class="text-center"><button type="submit">등록</button></td>
									</tr>
								</tbody>
								
							</table>
							<!-- //guestWrite -->							
						</form>	
						
						<%-- 글 목록 반복문으로 나열 --%>
						<div id="guestRead"></div>
						<!-- //guestRead -->
						
					</div>
					<!-- //guestbook -->
				
				</div>
				<!-- //content  -->
			</div>
			<!-- //container  -->
	
			<!-- //footer -->
			<c:import url="/WEB-INF/views/includes/footer.jsp"></c:import>
		</div>
		<!-- //wrap -->
	
	</body>
	<script type="text/javascript">
		$(document).ready(function(){ //triggers when all elements in the html file finishes loading
			$.ajax({
				url: "${pageContext.request.contextPath}/api/guestbook/list",
				type : "post",
				
				dataType: "json",
				success : function(gList){
					//console.log(gList);
					/*성공시 처리해야될 코드 작성*/
					
					for(var i=0; i<gList.length; i++){
						render(gList[i]);
					}
				},
				error : function(XHR, status, error) {
					console.error(status + " : " + error);
				}
			});
		});
		
		function render(gVo){	
			$("#guestRead").append(
					'<table class="guestRead">' +
					'	<colgroup>' +
					'		<col style="width: 10%;">' +
					'		<col style="width: 40%;">' +
					'		<col style="width: 40%;">' +
					'		<col style="width: 10%;">' +
					'	</colgroup>' +
					'	<tr>' +
					'		<td>' + gVo.no + '</td>' +
					'		<td>' + gVo.name + '</td>' +
					'		<td>' + gVo.regDate + '</td>' +
					'		<td><a href="${pageContext.request.contextPath}/guestbook/deleteForm/' + gVo.no + '">[삭제]</a></td>' +
					'	</tr>' +
					'	<tr>' +
					'		<td colspan=4 class="text-left">' + gVo.content + '</td>' +
					'	</tr>' +
					'</table>'
			);
		};
	</script>
</html>