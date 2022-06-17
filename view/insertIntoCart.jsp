<!--
プログラム名	書籍管理システムWeb版 Ver2.0
プログラムの説明	書籍を管理するシステムCRUD機能、検索機能、書籍購入機能、メール送信機能
作成者	齋藤直希
作成日	2022年5月30日 -->


<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@page import="bean.Book, bean.Order,util.MyFormat"%>

<!--3.0でオーダー情報必要に。。。。-->
<%
	Book book = (Book) request.getAttribute("book");
	Order order = (Order) request.getAttribute("order");
	MyFormat format = new MyFormat();
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>カート確認</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/insertIntoCart.css">
</head>
<body>
	<!-- ヘッダー部分 -->
	<%@ include file="/common/header.jsp"%>
	<div id="nav">
		<a href="<%=request.getContextPath()%>/view/menu.jsp">[メニュー]</a> <a
			href="<%=request.getContextPath()%>/list">[書籍一覧]</a>
	</div>
	<div class="page-title">
		<h1>カート追加</h1>
	</div>
	<p>下記の書籍をカートに追加しました。</p>
	<p>↓</p>
	<table>
		<tr>
			<th>ISBN</th>
			<td><%=book.getIsbn()%></td>
		</tr>

		<tr>
			<th>TITLE</th>
			<td><%=book.getTitle()%></td>
		</tr>

		<tr>
			<th>価格</th>
			<td><%=format.moneyFormat(book.getPrice())%></td>
		</tr>

		<tr>
			<th>購入数</th>
			<td><%=order.getQuantity()%></td>
		</tr>


	</table>
	<div class="submit">
		<form action="<%=request.getContextPath()%>/showCart">
			<input class="button-color" type="submit" value="カート確認">
		</form>
	</div>
	<!-- フッター部分 -->
	<%@ include file="/common/footer.jsp"%>

</body>
</html>