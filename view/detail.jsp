<!--
プログラム名	書籍管理システムWeb版 Ver2.0
プログラムの説明	書籍を管理するシステムCRUD機能、検索機能、書籍購入機能、メール送信機能
作成者	齋藤直希
作成日	2022年5月30日 -->


<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="bean.Book,util.MyFormat"%>

<%
	Book book = (Book) request.getAttribute("book");
	MyFormat format = new MyFormat();
%>

<html>
<head>
<title>書籍詳細情報</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/detail.css">
</head>
<body>
	<!-- ブラウザ全体 -->
	<!-- ヘッダー部分 -->
	<%@ include file="/common/header.jsp"%>
	<div id="nav">
		<a href="<%=request.getContextPath()%>/view/menu.jsp">[メニュー]</a> <a
			href="<%=request.getContextPath()%>/view/insert.jsp">[書籍登録]</a> <a
			href="<%=request.getContextPath()%>/list">[書籍一覧]</a>
	</div>
	<h1 class="detail-title">書籍詳細情報</h1>


	<div class="wrap">
		<div class="ud-button">
			<form action="<%=request.getContextPath()%>/detail"
				class="inline-block">
				<input type="hidden" name="isbn" value="<%=book.getIsbn()%>">
				<input type="hidden" name="cmd" value="update"></input> <input
					type="submit" value="変更">
			</form>

			<form action="<%=request.getContextPath()%>/delete"
				class="inline-block">
				<input type="hidden" name="isbn" value="<%=book.getIsbn()%>">
				<input type="submit" value="削除">
			</form>
		</div>
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
		</table>
	</div>



	<!-- フッター部分 -->
	<%@ include file="/common/footer.jsp"%>

</body>
</html>