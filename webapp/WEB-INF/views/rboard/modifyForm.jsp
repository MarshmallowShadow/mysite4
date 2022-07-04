<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>수정</title>
		<link href="${pageContext.request.contextPath}/assets/css/mysite.css" rel="stylesheet" type="text/css">
		<link href="${pageContext.request.contextPath}/assets/css/board.css" rel="stylesheet" type="text/css">
		<link href="${pageContext.request.contextPath }/assets/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css">
	</head>
	
	
	<body>
		<div id="wrap">
	
			<!-- //header -->
			<c:import url="/WEB-INF/views/includes/header.jsp"></c:import>
			
			<div id="container" class="clearfix">
				<div id="aside">
					<h2>게시판</h2>
					<ul>
						<li><a href="${pageContext.request.contextPath}/board/list">일반게시판</a></li>
						<li><a href="${pageContext.request.contextPath}/rboard/list">댓글게시판</a></li>
					</ul>
				</div>
				<!-- //aside -->
	
				<div id="content">
	
					<div id="content-head">
						<h3>게시판</h3>
						<div id="location">
							<ul>
								<li>홈</li>
								<li>게시판</li>
								<li class="last">일반게시판</li>
							</ul>
						</div>
						<div class="clear"></div>
					</div>
					<!-- //content-head -->
		
					<div id="board">
						<div id="modifyForm">
							<%-- 수정폼 (컨트롤러가 보낸 정보들 해당 의치에 넣어주기) --%>
							<form action="${pageContext.request.contextPath}/board/modify/${bMap.NO }" method="get">
								<%-- action이랑 no hidden으로 저장 --%>
								<!-- 작성자 -->
								<div class="form-group">
									<span class="form-text">작성자</span>
									<span class="form-value">${bMap.NAME }</span>
								</div>
								
								<!-- 조회수 -->
								<div class="form-group">
									<span class="form-text">조회수</span>
									<span class="form-value">${bMap.HIT }</span>
								</div>
								
								<!-- 작성일 -->
								<div class="form-group">
									<span class="form-text">작성일</span>
									<span class="form-value">${bMap.REGDATE }</span>
								</div>
								
								<!-- 제목 -->
								<div class="form-group">
									<label class="form-text" for="txt-title">제목</label>
									<input type="text" id="txt-title" name="title" value="${bMap.TITLE }">
								</div>
							
								
							
								<!-- 내용 -->
								<div class="form-group">
									<textarea id="txt-content" name="content">${bMap.CONTENT }</textarea>
								</div>
								
								<a id="btn_cancel" href="${pageContext.request.contextPath}/board/read/${bMap.NO }">취소</a>
								<button id="btn_modify" type="submit" >수정</button>
							</form>
							<!-- //form -->
						</div>
						<!-- //modifyForm -->
					</div>
					<!-- //board -->
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