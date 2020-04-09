<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>图书管理</title>
	<!--静态包含base标签，css样式，jquery文件-->
	<%@include file="/pages/common/head.jsp"%>
	<script type="text/javascript">
		$(function () {
			$("a.deleteClass").click(function () {
				//在事件的function函数中，有一个this对象，这个this对象，是指当前正在响应时间的dom对象
				//删除【】里的内容可以通过该dom对象找到父元素中的表格立得该书名即可
				return confirm("确定要删除【"+$(this).parent().parent().find("td:first").text()+"】?");

			})
		})
	</script>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="../../static/img/logo.jpg" >
			<span class="wel_word">图书管理系统</span>
		<%--静态包含manager管理模块的菜单--%>
		<%@include file="/pages/common/manager_menu.jsp"%>
	</div>
	
	<div id="main">
		<table>
			<tr>
				<td>名称</td>
				<td>价格</td>
				<td>作者</td>
				<td>销量</td>
				<td>库存</td>
				<td colspan="2">操作</td>
			</tr>		

			<c:forEach items="${requestScope.page.items}" var="book">
			<tr>
				<td>${book.name}</td>
				<td>${book.price}</td>
				<td>${book.author}</td>
				<td>${book.sales}</td>
				<td>${book.stock}</td>
				<td><a href="manage/BookServlet?action=getBook&id=${book.id}&method=update&pageNum=${requestScope.page.pageNum}">修改</a></td>
				<td><a class="deleteClass" href="manage/BookServlet?action=delete&id=${book.id}&pageNum=${requestScope.page.pageNum}">删除</a></td>
			</tr>
			</c:forEach>
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td><a href="pages/manager/book_edit.jsp?method=add&pageNum=${requestScope.page.pageTotal}">添加图书</a></td>
			</tr>	
		</table>
		<%--分页条的开始--%>
		<%--静态包含分页条--%>
		<%@include file="/pages/common/page_nav.jsp"%>

	</div>

	<%--静态包含页脚内容--%>
	<%@include file="/pages/common/footer.jsp"%>
</body>
</html>