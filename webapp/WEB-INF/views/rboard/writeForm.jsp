<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
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
						<div id="writeForm">
							<%-- 게시글 작성 폼 --%>
							<form action="${pageContext.request.contextPath}/rboard/write" method="get">
								<!-- 제목 -->
								<div class="form-group">
									<label class="form-text" for="txt-title">제목</label>
									<input type="text" id="txt-title" name="title" value="" placeholder="제목을 입력해 주세요">
									<input type="hidden" name="groupNo" value="${rVo.groupNo }">
									<input type="hidden" name="orderNo" value="${rVo.orderNo }">
									<input type="hidden" name="depth" value="${rVo.depth }">
								</div>
							
								<!-- 내용 -->
								<div class="form-group">
									<textarea id="txt-content" name="content"></textarea>
								</div>
								
								<a id="btn_cancel" href="${pageContext.request.contextPath}/rboard/list">취소</a>
								<button id="btn_add" type="submit" >등록</button>
								
							</form>
							<!-- //form -->
						</div>
						<!-- //writeForm -->
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
