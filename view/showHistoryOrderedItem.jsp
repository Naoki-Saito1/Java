<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@page import="java.util.*,bean.Order,bean.OrderedItem,bean.User"%>

<%
	HashMap<String, Object> map = (HashMap<String, Object>) request.getAttribute("map");

	User user = (User) session.getAttribute("user");
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>購入履歴</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/shared.css">
</head>
<body>

	<!-- ヘッダー部分 -->
	<%@ include file="/common/header.jsp"%>
	<div id="nav">
		<a href="<%=request.getContextPath()%>/view/menu.jsp">[メニュー]</a>
	</div>
	<h1 class="page-title">購入履歴</h1>
	<h3 align="center"><%=user.getUserid()%>
		様が購入したものは以下になります。
	</h3>
	<table>

		<tr>
			<th>TITLE</th>
			<th>数量</th>
			<th>購入日</th>
		</tr>

		<%
			for (int i = 0; i < map.size() / 2; i++) {
				System.out.print(map.size());
		%>

		<tr>
			<td><%=((OrderedItem) map.get("item" + i)).getTitle()%></td>
			<td><%=((Order) map.get("order" + i)).getQuantity()%></td>
			<td><%=((OrderedItem) map.get("item" + i)).getDate()%></td>

		</tr>
		<%
			}
		%>

	</table>
	<%@ include file="/common/footer.jsp"%>

</body>
</html>