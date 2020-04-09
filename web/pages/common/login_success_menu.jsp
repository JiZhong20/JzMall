<%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 2020/3/24
  Time: 18:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div>
    <span>欢迎<span class="um_span">${sessionScope.user.username}</span>光临JzMall</span>
    <a href="OrderServlet?action=myOrders">我的订单</a>
    <a href="UserServlet?action=logout">注销</a>&nbsp;
    <a href="index.jsp">返回</a>
</div>