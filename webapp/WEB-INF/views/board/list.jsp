<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>게시판</title>
		<!-- js -->
		<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.12.4.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/assets/bootstrap/js/bootstrap.js"></script>

		<!-- css -->
		<link href="${pageContext.request.contextPath }/assets/css/mysite.css" rel="stylesheet" type="text/css">
		<link href="${pageContext.request.contextPath }/assets/css/board.css" rel="stylesheet" type="text/css">
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
						<div id="list">
							<form action="${pageContext.request.contextPath}/board/list"
								method="get">
								<div class="form-group text-right">
									<input type="text" name="keyword" value="">
									<button type="submit" id=btn_search>검색</button>
								</div>
							</form>
							<table>
								<thead>
									<tr>
										<th>번호</th>
										<th>제목</th>
										<th>글쓴이</th>
										<th>조회수</th>
										<th>작성일</th>
										<th>관리</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${pMap.bList}" var="bMap">
										<tr>
											<td>${bMap.NO }</td>
											<td class="text-left"><a
												href="${pageContext.request.contextPath}/board/read/${bMap.NO }">${bMap.TITLE }</a></td>
											<td>${bMap.NAME }</td>
											<td>${bMap.HIT }</td>
											<td>${bMap.REGDATE }</td>
											<td><c:if test="${authUser.no == bMap.USERNO }">
													<a
														href="${pageContext.request.contextPath}/board/delete/${bMap.NO }">[삭제]</a>
												</c:if></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
	
							<div id="paging">
								<ul id="pageList">
									<c:if test="${pMap.prev }">
										<li><a href="${pageContext.request.contextPath}/board/list?page=${pMap.startPageBtnNo-1}&keyword=${keyword}">◀</a></li>
									</c:if>
									
									<c:forEach begin="${pMap.startPageBtnNo}" end="${pMap.endPageBtnNo}" step="1" var="pageNum">
									<c:choose>
										<c:when test="${pageNum == page}"><li class="active"><a href="${pageContext.request.contextPath}/board/pageList?page=${pageNum }&keyword=${keyword}">${pageNum }</a></li></c:when>
										<c:otherwise><li><a href="${pageContext.request.contextPath}/board/list?page=${pageNum }&keyword=${keyword}">${pageNum }</a></li></c:otherwise>
									</c:choose>
									</c:forEach>
									
									<c:if test="${pMap.next }">
										<li><a href="${pageContext.request.contextPath}/board/list?page=${pMap.endPageBtnNo+1}&keyword=${keyword}">▶</a></li>
									</c:if>
								</ul>
								<div class="clear"></div>
							</div>
							<c:choose>
								<c:when test="${authUser == null }"></c:when>
								<c:otherwise>
									<a id="btn_write"
										href="${pageContext.request.contextPath}/board/writeForm">글쓰기</a>
								</c:otherwise>
							</c:choose>
	
						</div>
						<!-- //list -->
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
