<!--
プログラム名	書籍管理システムWeb版 Ver2.0
プログラムの説明	書籍を管理するシステムCRUD機能、検索機能、書籍購入機能、メール送信機能
作成者	齋藤直希
作成日	2022年5月30日 -->


<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@page import="java.util.ArrayList,bean.Book,util.MyFormat"%>

<%
	ArrayList<Book> book_list = (ArrayList<Book>) request.getAttribute("book_list");
	MyFormat format = new MyFormat();
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>初期データ登録</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/shared.css">
</head>
<body>
	<!-- ヘッダー部分 -->
	<%@ include file="/common/header.jsp"%>
	<div id="nav">
		<a href="<%=request.getContextPath()%>/view/menu.jsp">[メニュー]</a>
	</div>
	<h1 class="page-title">初期データ登録</h1>
	<h3 align="center">初期データとして以下のデータを登録しました。</h3>
	<table>

		<tr>
			<th>ISBN</th>
			<th>TITLE</th>
			<th>価格</th>
		</tr>

		<%
			for (int i = 0; i < book_list.size(); i++) {
		%>

		<tr>
			<td><%=book_list.get(i).getIsbn()%></td>
			<td><%=book_list.get(i).getTitle()%></td>
			<td><%=format.moneyFormat(book_list.get(i).getPrice())%></td>

		</tr>
		<%
			}
		%>

	</table>
	<%@ include file="/common/footer.jsp"%>
</body>
</html>