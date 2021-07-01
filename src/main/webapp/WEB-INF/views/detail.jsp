<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="moviedto" value="${movie}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/detail.css"/>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
	$(function(){
		 $('input[type="checkbox"][name="category"]').click(function(){
			 
			  if($(this).prop('checked')){
			 
			     $('input[type="checkbox"][name="category"]').prop('checked',false);
			 
			     $(this).prop('checked',true);
			 
			    }
			  
			});
		 
		 $('.category2').click(function(){
			 if($(this).is(":checked")==true){
				 $('.commentcontainer').each(function(){
						if($(this).data("value")==="spoiler"){
							$(this).css("display","block");
						} else{
							$(this).css("display","none");
						}
					 });
						 
			 }else{
				 $('.commentcontainer').each(function(){
						if($(this).data("value")==="spoiler"){
							$(this).css("display","none");
						} else{
							$(this).css("display","block");
						}
					 });
			 }

			 
			 
			});
	});
	
</script>
</head>
<body>
<%String userid = (String)session.getAttribute("userid"); %>
<div class="container">
	<div class="innercontainer">
		<div class="leftcontainer">
			<div class="movieposter">
				<img class="poster" src="${pageContext.request.contextPath}/img/${moviedto.imagepath}">
			</div>
			<div class="movietitle">
				<%String title = (String)session.getAttribute("title");
				
				%>
				${title}
			</div>
		</div>
		<div class="rightcontainer">
			<div class="formcontainer">
				<form action="CommentInsert" method="POST" >
					<div class="mycommentdisc">
						나의 리뷰 작성
					</div>
				
					<div class="spoilercheck">
						<div class="check1">
							<input type="checkbox" name="category" value="spoiler">spoiler
						</div>
						<div class="check2">
							<input type="checkbox" name="category" value="review">review
						</div>
					</div>
			

					<div class="mycomment">
						<div class="inputcommentcontainer">
							<textarea placeholder="리뷰를 입력하세요" name="content" class="inputcomment"></textarea>
						</div>
					</div>
					<div class="confirmbutton">
						<input type="submit" value="등록" class="buttonconfirm">
					</div>
				</form>
			</div>
			

			<div class="commentlistcontainer">
				<div class="commentlistdesc">
					리뷰보기
				</div>
				<div class="otherspoilercheck">
					<div class="check1">
						<input type="checkbox" class="category2" name="category2" value="spoiler">spoiler
					</div>

				</div>
				<div class="commentcontenlistcontainer">
					<c:forEach var="commentdto" items="${comment}">
					<c:choose>
					<c:when  test="${commentdto.category eq 'spoiler'}">
					<div class="commentcontainer" data-value="${commentdto.category}" style="display:none;">
						<div class="commentuser">
							${commentdto.user_id}
						</div>
						<div class="commentcontentcontainer">
							<div class="commentcontent">
								${commentdto.content}
							</div>
							
						</div>
						<div class="commentedit">
							
						</div>
						<div class="commenteditconfirm">
							
						</div>
					</div>
					</c:when>
					
					<c:otherwise>
					<div class="commentcontainer" data-value="${commentdto.category}" >
						<div class="commentuser">
							${commentdto.user_id}
						</div>
						<div class="commentcontentcontainer">
							<div class="commentcontent">
								${commentdto.content}
							</div>
							
						</div>
						<div class="commentedit">
							
						</div>
						<div class="commenteditconfirm">
							
						</div>
					</div>
					</c:otherwise>

					</c:choose>

					</c:forEach>
				</div>

			</div>
		</div>
	</div>

</div>
</body>
</html>