<%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 2020/3/29
  Time: 17:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="page_nav">
    <c:if test="${requestScope.page.pageNum>1}">
        <a href="${requestScope.page.url}&pageNum=1">首页</a>
        <a href="${requestScope.page.url}&pageNum=${requestScope.page.pageNum-1}">上一页</a>
    </c:if>
    <%--开始输出页码--%>

    <c:choose>
        <%--情况1：如果总页码小于等于5的情况，页码的范围是：1-总页码--%>
        <c:when test="${requestScope.page.pageTotal <= 5 }">
            <c:set var="begin" value="1"/>
            <c:set var="end" value="${requestScope.page.pageTotal}"/>
        </c:when>
        <%--情况2：总页码大于5的情况--%>
        <c:when test="${requestScope.page.pageTotal > 5}">
            <c:choose>
                <%--小情况1：当前页码为前面3个：1，2，3的情况，页码范围是：1-5.--%>
                <c:when test="${requestScope.page.pageNum <= 3}">
                    <c:set var="begin" value="1"/>
                    <c:set var="end" value="5"/>
                </c:when>
                <%--小情况2：当前页码为最后3个，8，9，10，页码范围是：总页码减4 - 总页码--%>
                <c:when test="${requestScope.page.pageNum > requestScope.page.pageTotal-3}">
                    <c:set var="begin" value="${requestScope.page.pageTotal-4}"/>
                    <c:set var="end" value="${requestScope.page.pageTotal}"/>
                </c:when>
                <%--小情况3：4，5，6，7，页码范围是：当前页码减2 - 当前页码加2--%>
                <c:otherwise>
                    <c:set var="begin" value="${requestScope.page.pageNum-2}"/>
                    <c:set var="end" value="${requestScope.page.pageNum+2}"/>
                </c:otherwise>
            </c:choose>
        </c:when>
    </c:choose>

    <c:forEach begin="${begin}" end="${end}" var="i">
        <c:if test="${i == requestScope.page.pageNum}">
            【${i}】
        </c:if>
        <c:if test="${i != requestScope.page.pageNum}">
            <a href="${requestScope.page.url}&pageNum=${i}">${i}</a>
        </c:if>
    </c:forEach>

    <%--结束输出页码--%>
    <c:if test="${requestScope.page.pageNum <requestScope.page.pageTotal }">
        <a href="${requestScope.page.url}&pageNum=${requestScope.page.pageNum+1}">下一页</a>
        <a href="${requestScope.page.url}&pageNum=${requestScope.page.pageTotal}">末页</a>
    </c:if>

    共${requestScope.page.pageTotal}页，${requestScope.page.pageTotalCount}条记录
    到第<input value="${param.pageNum}" name="pn" id="pn_input"/>页
    <input id="searchPageBtn" type="button" value="确定">

    <script type="text/javascript">
        $(function () {
            $("#searchPageBtn").click(function () {

                var pageNum = $("#pn_input").val();

                location.href="http://localhost:8088/mall_02/${requestScope.page.url}&pageNum="+pageNum;
            });
        });
    </script>
</div>