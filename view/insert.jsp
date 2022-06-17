<!--
プログラム名	書籍管理システムWeb版 Ver2.0
プログラムの説明	書籍を管理するシステムCRUD機能、検索機能、書籍購入機能、メール送信機能
作成者	齋藤直希
作成日	2022年5月30日 -->


<%@page contentType="text/html; charset=UTF-8"%>
<html>
<head>
<title>書籍登録</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/in_sert.css">
</head>
<body>
	<%@ include file="/common/header.jsp"%>

	<div id="nav">
		<a href="<%=request.getContextPath()%>/view/menu.jsp">[メニュー]</a> <a
			href="<%=request.getContextPath()%>/list">[書籍一覧]</a>
	</div>
	<h1 class="insert-title">書籍登録</h1>
	<div class="wrap">
		<form action="<%=request.getContextPath()%>/Insert">
			<table>
				<tr>
					<th>ISBN</th>
					<td><input type="text" name="isbn"></td>
				</tr>

				<tr>
					<th>TITLE</th>
					<td><input type="text" name="title"></td>
				</tr>

				<tr>
					<th>価格</th>
					<td><input type="text" name="price"></td>
				</tr>


			</table>
			<input class="submit-button" type="submit" value="登録">
		</form>
	</div>
	<%@ include file="/common/footer.jsp"%>
</body>
</html>