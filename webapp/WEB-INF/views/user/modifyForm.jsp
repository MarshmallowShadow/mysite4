<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>수정</title>
		<link href="/mysite4/assets/css/mysite.css" rel="stylesheet" type="text/css">
		<link href="/mysite4/assets/css/user.css" rel="stylesheet" type="text/css">
		
	</head>
	
	<body>
		<div id="wrap">
	
			<!-- //header -->
			<c:import url="/WEB-INF/views/includes/header.jsp"></c:import>
	
			<!-- //nav -->
			<c:import url="/WEB-INF/views/includes/nav.jsp"></c:import>
	
			<div id="container" class="clearfix">
				<div id="aside">
					<h2>회원</h2>
					<ul>
						<li>회원정보</li>
						<li>로그인</li>
						<li>회원가입</li>
					</ul>
				</div>
				<!-- //aside -->
	
				<div id="content">
				
					<div id="content-head">
						<h3>회원정보</h3>
						<div id="location">
							<ul>
								<li>홈</li>
								<li>회원</li>
								<li class="last">회원정보</li>
							</ul>
						</div>
						<div class="clear"></div>
					</div>
					 <!-- //content-head -->
		
					<div id="user">
						<div id="modifyForm">
							<%-- request에 보낸 정보들 꺼내기 --%>
							<form action="#" method="post">
								<!-- action이랑 id값들 보낼 수 있게 "hidden" input으로 "저장" -->
								<input type="hidden" name="id" value="${requestScope.uVo.id }">
								
								
								<%-- 아래부터 uVo에 저장된 정보들 꺼내서 해당 input value에 저장 --%>
								
								<!-- 아이디 -->
								<%-- id는 span에 저장되 있으므로 controller로 못보낸다는 점 주의--%>
								<div class="form-group">
									<label class="form-text" for="input-uid">아이디</label> 
									<span class="text-large bold">${requestScope.uVo.id }</span>
								</div>
		
								<!-- 비밀번호 -->
								<div class="form-group">
									<label class="form-text" for="input-pass">패스워드</label> 
									<input type="password" id="input-pass" name="password" value="${requestScope.uVo.password }" placeholder="비밀번호를 입력하세요">
								</div>
		
								<!-- 이메일 -->
								<div class="form-group">
									<label class="form-text" for="input-name">이름</label> 
									<input type="text" id="input-name" name="name" value="${requestScope.uVo.name }" placeholder="이름을 입력하세요">
								</div>
		
								<!-- //나이 -->
								
								<%-- 남성 여성에 따라서 해당 gender 라디오에 checked --%>
								<div class="form-group">
									<span class="form-text">성별</span> 
									
									<label for="rdo-male">남</label> 
									<input type="radio" id="rdo-male" name="gender" value="male" <c:if test="${requestScope.uVo.gender=='male'}">checked</c:if>> 
									
									<label for="rdo-female">여</label> 
									<input type="radio" id="rdo-female" name="gender" value="female" <c:if test="${requestScope.uVo.gender=='female'}">checked</c:if>> 
		
								</div>
		
								<!-- 버튼영역 -->
								<div class="button-area">
									<button type="submit" id="btn-submit">회원정보수정</button>
								</div>
								
							</form>
						
						
						</div>
						<!-- //modifyForm -->
					</div>
					<!-- //user -->
				</div>
				<!-- //content  -->
	
			</div>
			<!-- //container  -->
	
			<!-- //footer -->
			<c:import url="/WEB-INF/views/includes/footer.jsp"></c:import>
		</div>
		<!-- //wrap -->
	
	</body>

</html>