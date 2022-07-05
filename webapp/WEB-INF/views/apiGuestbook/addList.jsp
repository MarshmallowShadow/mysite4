<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>방명록</title>
		<!-- js -->
		<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.12.4.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/assets/bootstrap/js/bootstrap.js"></script>
		
		<!-- css -->
		<link href="${pageContext.request.contextPath}/assets/css/mysite.css" rel="stylesheet" type="text/css">
		<link href="${pageContext.request.contextPath}/assets/css/guestbook.css" rel="stylesheet" type="text/css">
		<link href="${pageContext.request.contextPath}/assets/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css">
	</head>
	
	<body>
		<div id="wrap">
	
			<!-- //header -->
			<c:import url="/WEB-INF/views/includes/header.jsp"></c:import>
	
			<div id="container" class="clearfix">
				<div id="aside">
					<h2>방명록</h2>
					<ul>
						<li><a href="${pageContext.request.contextPath}/guestbook/addList">일반방명록</a></li>
						<li><a href="${pageContext.request.contextPath}/api/guestbook/addList">ajax방명록</a></li>
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
								<li class="last">ajax방명록</li>
							</ul>
						</div>
					</div>
					<!-- //content-head -->
	
					<div id="guestbook">
						<%-- 방병록 작성 폼 --%>
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
									<td colspan="4" class="text-center"><button id="btnSubmit" type="submit">등록</button></td>
								</tr>
							</tbody>
						</table>
						
						<!-- <button id="btnTest" class="btn btn-primary">모달창</button><br><br>  -->
						
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
		
		
<!-- ************************************************************************************************************* -->
<!-- 삭제 모달창 -->
		<div id="delModal" class="modal fade">
			<div class="modal-dialog">
		    	<div class="modal-content">
		    		<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						<h4 class="modal-title">비밀번호 입력하세요</h4>
					</div>
					<div class="modal-body">
						<label for="pass">비밀번호</label>
			        	<input type="password" id="pass" name="password">
			        	<input type="hidden" name="no">
			        	<div id="deleteFail"></div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">취소</button>
						<button id="btnModalDel" type="button" class="btn btn-danger">삭제</button>
					</div>
				</div><!-- /.modal-content -->
			</div><!-- /.modal-dialog -->
		</div><!-- /.modal -->
<!-- ************************************************************************************************************* -->
	
	</body>
	
	
	<script type="text/javascript">
		
	//addList
		$(document).ready(function(){ //triggers when all elements in the html file finishes loading
			$.ajax({
				url: "${pageContext.request.contextPath}/api/guestbook/list",
				type : "post",
				
				dataType: "json",
				success : function(gList){
					//console.log(gList);
					/*성공시 처리해야될 코드 작성*/
					
					for(var i=0; i<gList.length; i++){
						render(gList[i], true);
					}
				},
				error : function(XHR, status, error) {
					console.error(status + " : " + error);
				}
			});
		});
	
	//add
		$("#btnSubmit").on("click", function(){
			var name = $("[name=name]").val();
			var password = $("[name=password]").val();
			var content = $("[name=content]").val();
			
			var guestVo = {
				name: name,
				password: password,
				content: content
			};
			//console.log(guestVo);
			
			$.ajax({
				url : "${pageContext.request.contextPath}/api/guestbook/add2",
				type : "post",
				contentType : "application/json",
				data : JSON.stringify(guestVo),
				dataType : "json",
				success : function(gVo){
					render(gVo, false);
				},
				error : function(XHR, status, error) {
				
				console.error(status + " : " + error);
				
				}
			});
		});
		
	//Modal-Related
		$("#btnTest").on("click", function() {
			console.log("btnTest 클릭");
			//모달창 띄우기 -> show or hide 면 숨겨지기도 함!
			$("#delModal").modal("show");
		});
		
		$("#guestRead").on("click", ".btnDel", function(){
			var $this = $(this);
			var no = $this.data("no");
			
			$("[name=password]").val("");
			$("[name=no]").val(no);
			
			$("#delModal").modal("show");
		});
		
		$("#btnModalDel").on("click", function(){
			var no = $("#delModal [name=no]").val();
			var password = $("#delModal [name=password]").val();
			gVo = {
				no: no,
				password: password
			};
			//console.log(gVo);
			
			$.ajax({
				url: "${pageContext.request.contextPath}/api/guestbook/delete",
				type: "post",
				//contentType : "application/json",
				data: gVo,
				dataType: "json",
				success: function(success){
					if(success > 0){
						$("#t" + no).remove();
						$("#delModal").modal("hide");
					}
					else {
						$("#deleteFail").html("<br><font color='red'>비밀번호가 다릅니다. 다시 시도하세요</font>");
					}
				},
				error : function(XHR, status, error) {

					console.error(status + " : " + error);

				}
			});
		});
		
	//functions (render, remove)
		function render(gVo, isAppend){	
			var str =
			'<table id="t' + gVo.no + '" class="guestRead">' +
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
			'		<td><button class="btnDel btn btn-primary" type="button" data-no="' + gVo.no + '">삭제</button></td>' +
			'	</tr>' +
			'	<tr>' +
			'		<td colspan=4 class="text-left">' + gVo.content + '</td>' +
			'	</tr>' +
			'</table>';
			
			if(isAppend == true){
				$("#guestRead").append(str);
			} else {
				$("#guestRead").prepend(str);
			}
			
		};
	</script>
</html>