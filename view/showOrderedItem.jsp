<!--
プログラム名	書籍管理システムWeb版 Ver2.0
プログラムの説明	書籍を管理するシステムCRUD機能、検索機能、書籍購入機能、メール送信機能
作成者	齋藤直希
作成日	2022年5月30日 -->


<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@page import="java.util.ArrayList,bean.OrderedItem,util.MyFormat"%>

<%
	ArrayList<OrderedItem> ordered_list = (ArrayList<OrderedItem>) request.getAttribute("ordered_list");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>購入状況</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/shared.css">
</head>
<body>
	<!-- ヘッダー部分 -->
	<%@ include file="/common/header.jsp"%>
	<div id="nav">
		<a href="<%=request.getContextPath()%>/view/menu.jsp">[メニュー]</a>
	</div>
	<h1 class="page-title">購入状況</h1>

	<table>

		<tr>
			<th>ユーザー</th>
			<th>TITLE</th>
			<th>数量</th>
			<th>注文日</th>
		</tr>


		<%
			for (int i = 0; i < ordered_list.size(); i++) {
		%>

		<tr>
			<td><%=ordered_list.get(i).getUserid()%></td>
			<td><%=ordered_list.get(i).getTitle()%></td>
			<td><%=ordered_list.get(i).getQuantity()%></td>
			<td><%=ordered_list.get(i).getDate()%></td>
		</tr>

		<%
			}
		%>


	</table>

	<%@ include file="/common/footer.jsp"%>
</body>
</html>