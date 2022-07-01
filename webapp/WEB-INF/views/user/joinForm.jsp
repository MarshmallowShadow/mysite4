<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
		<title>회원가입</title>
		
		<link href="${pageContext.request.contextPath}/assets/css/mysite.css" rel="stylesheet" type="text/css">
		<link href="${pageContext.request.contextPath}/assets/css/user.css" rel="stylesheet" type="text/css">
		<link href="${pageContext.request.contextPath }/assets/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css">
		
		<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.12.4.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath }/assets/bootstrap/js/bootstrap.js"></script>
	</head>
	
	<body>
		<div id="wrap">
	
			<!-- //header -->
			<c:import url="/WEB-INF/views/includes/header.jsp"></c:import>
	
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
						<h3>회원가입</h3>
						<div id="location">
							<ul>
								<li>홈</li>
								<li>회원</li>
								<li class="last">회원가입</li>
							</ul>
						</div>
						<div class="clear"></div>
					</div>
					<!-- //content-head -->
					
					<%-- 가입 폼 --%>
					<div id="user">
						<div id="joinForm">
							<%-- user controller로 정보 보내기 --%>
							<form id="join-form" action="${pageContext.request.contextPath}/user/join" method="post">
								<!-- 아이디 -->
								<div class="form-group">
									<label class="form-text" for="input-uid">아이디</label>
									<input type="text" id="input-uid" name="id" value="" placeholder="아이디를 입력하세요">
									<button type="button" id="btnCheck">중복체크</button>
								</div>
								
								<div id="checkRepeat" class="form-group" style="padding: 0px 160px"></div>
		
								<!-- 비밀번호 -->
								<div class="form-group">
									<label class="form-text" for="input-pass">패스워드</label> 
									<input type="text" id="input-pass" name="password" value="" placeholder="비밀번호를 입력하세요">
								</div>
		
								<!-- 이메일 -->
								<div class="form-group">
									<label class="form-text" for="input-name">이름</label> 
									<input type="text" id="input-name" name="name" value="" placeholder="이름을 입력하세요">
								</div>
								
								<!-- 나이 -->
								<div class="form-group">
									<span class="form-text">성별</span> 
									
									<label for="rdo-male">남</label> 
									<input type="radio" id="rdo-male" name="gender" value="male" > 
									
									<label for="rdo-female">여</label> 
									<input type="radio" id="rdo-female" name="gender" value="female" > 
								
								</div>
								
								<!-- 약관동의 -->
								<div class="form-group">
									<span class="form-text">약관동의</span> 
									<input type="radio" id="rdo-agree" name="agree" value="true">
									<label for="rdo-agree">서비스 약관에 동의합니다.</label> 
								</div>
								
								<!-- 버튼영역 -->
								<div class="button-area">
									<button type="submit" id="btn-submit">회원가입</button>
								</div>
								
							</form>
						</div>
						<!-- //joinForm -->
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
	
	<script type="text/javascript">
		$("#join-form").on("submit", function(){
			var id = $("#input-uid").val();
			var password = $("#input-pass").val();
			
			if(id == "" || id == null) {
				alert("아이디를 입력하세요.");
				return false;
			}
			if(password.length < 8) {
				alert("비밀번호 8 자리 입력하세요.");
				return false;
			}
			if($("#chk-agree").is(":checked") == false) {
				alert("약관동의 해주세요.");
				return false;
			}
			
			return true;
		});
		
		$("#btnCheck").on("click", function(){
			var id = $("#input-uid").val();
			
			if(id != null){
				$.ajax({
					url: "${pageContext.request.contextPath}/api/user/checkId",
					type : "post",
					contentType : "application/json",
					data: JSON.stringify(id),
					dataType: "json",
					success : function(result){
						//console.log(gList);
						/*성공시 처리해야될 코드 작성*/
						if(result == true) {
							$("#checkRepeat").html('<font color="red">사용할 수 없는 아이디입니다.</font>');
						} else {
							$("#checkRepeat").html("사용 가능한 아이디입니다.");
						}
					},
					error : function(XHR, status, error) {
						console.error(status + " : " + error);
					}
				});
			}
		});
	</script>
</html>