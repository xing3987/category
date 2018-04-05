<%@ page contentType="text/html;charset=UTF-8" language="java" 
	 pageEncoding="utf-8"%>

<%@page import="java.util.*,javaBean.*" %>
<%//直接訪問該地址跳轉到登陸頁面
	HttpSession s=request.getSession();
	Object o=s.getAttribute("user");
	if(o==null){
		response.sendRedirect("login.jsp");
	}
%>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width,initial-scale=1">
	<title>index</title>
	<link rel="stylesheet" href="static/css/bootstrap.min.css">   
	<style type="text/css">
		body{ font-family: 'Microsoft YaHei';}
		/*.panel-body{ padding: 0; }*/
	</style>
</head>
<body>
<%@include file="header.jsp" %>
<div class="container">
	<div class="main">
		<div class="row">
			<!-- 左侧内容 -->
			<div class="col-md-3">
				<div class="list-group">
					<a href="${pageContext.request.contextPath}/CategoryList" class="list-group-item text-center active">生鲜列表</a>
					<a href="${pageContext.request.contextPath}/category-add.jsp" class="list-group-item text-center ">新增生鲜</a>
				</div>
			</div>
			<!-- 右侧内容 -->
			<div class="col-md-9">
				<!-- 成功提示框 -->
				<div class="alert alert-success alert-dismissible" role="alert">
					<button type="button" class="close" data-dismiss="alert"><span aria-hidden="false">&times;</span><span class="sr-only">Close</span></button>
					<strong>成功！</strong> 操作成功提示
				</div>
				<!-- 失败提示框 -->
				<div style="display: none" class="alert alert-danger alert-dismissible" role="alert">
					<button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
					<strong>失败！</strong> 操作失败提示
				</div>
				<!-- 自定义内容 -->
				<div class="panel panel-default">
					<div class="panel-heading">生鲜列表</div>
					<div class="panel-body">
						<table class="table table-striped table-responsive table-hover">
							<thead>
								<tr>
									<th>编号</th>
									<th>名称</th>
									<th>添加时间</th>
									<th>类别</th>
									<th>产地</th>
									<th width="120">操作</th>
								</tr>
							</thead>
							<tbody>
							<%
								List<Category> categorys=(List<Category>)request.getAttribute("categorys");
								for(Category category:categorys){
									%>
									<tr>
										<td><%=category.getId()%></td>
										<td><%=category.getName()%></td>
										<td><%=category.getAddTime()%></td>
										<td><%=category.getCategoryType()%></td>
										<td><%=category.getCategoryFrom()%></td>
										<td>
											<%--<a href="">详情</a>--%>
											<a href="${pageContext.request.contextPath}/Dele?id=<%=category.getId()%>">删除</a>
											<a href="${pageContext.request.contextPath}/category-update.jsp?id=<%=category.getId()%>&name=<%=category.getName()%>&categoryType=<%=category.getCategoryType()%>&categoryFrom=<%=category.getCategoryFrom()%>">修改</a>
										</td>
									</tr>
							<%  }
							%>
							
							
							
<!-- 							<c:forEach items="${page.list}" var="category">
								<tr>
									<th>${category.c_id}</th>
									<td>${category.c_name}</td>
									<td>${category.createtime}</td>
									<c:if test="${category.type==0}" >
										<td>未知</td>
									</c:if>
									<c:if test="${category.type==1}" >
										<td>猪牛羊肉</td>
									</c:if>
									<c:if test="${category.type==2}" >
										<td>海鲜水产</td>
									</c:if>
									<td>${category.place}</td>


									<td>
										<%--<a href="">详情</a>--%>
										<a href="${pageContext.request.contextPath}/category?method=deleteCategory&c_id=${category.c_id}">删除</a>
										<a href="${pageContext.request.contextPath}/category-update.jsp?c_id=${category.c_id}&c_name=${category.c_name}&type=${category.type}&place=${category.place}">修改</a>
									</td>
								</tr>
							</c:forEach>
 -->

							</tbody>
						</table>
					</div>
				</div>


				<!--分页 -->
				<!--得到list传来的page1对象用于分页查询，点击向上page1-1,向下page1+1 -->
				<%
					int page1=(Integer)request.getAttribute("page1");				
				%>
				<nav>
					<ul class="pagination pull-right">
						<li  class="previous"><a href="CategoryList?page1=
						<%=page1-1%>">&laquo;</a></li>	
						<li><a><%=page1%></a></li>					
 						<li><a href="CategoryList?page1=
						<%=page1+1%>">&raquo;</a></li>
					</ul>

				</nav>
			</div>
		</div>
  	</div>
</div>
<%@include file="footer.jsp" %>


	<script src="static/js/jquery-3.1.0.min.js"></script>
	<script src="static/js/bootstrap.min.js"></script>
</body>
</html>