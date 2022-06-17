<!--
プログラム名	書籍管理システムWeb版 Ver2.0
プログラムの説明	書籍を管理するシステムCRUD機能、検索機能、書籍購入機能、メール送信機能
作成者	齋藤直希
作成日	2022年5月30日 -->


<%@ page language="java" contentType="text/html; charset=UTF-8"%>


<%
	String error = (String) request.getAttribute("error");
	if (error == null) {
		error = "";
	}
	String cmd = (String) request.getAttribute("cmd");
	if (cmd == null) {
		cmd = "menu";
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Error画面</title>
</head>
<body>
	<!-- ブラウザ全体 -->
	<div id="wrap">
		<!-- ヘッダー部分 -->
		<%@ include file="/common/header.jsp"%>

		<!-- メニュー部分 -->
		<div id="menu">
			<div class="container">
				<!-- ページタイトル -->
				<div id="page_title">
					<h2>■■エラー■</h2>
				</div>
			</div>
		</div>

		<!-- コンテンツ部分 -->
		<div id="main" class="container">
			<!-- エラーメッセージ  -->
			<p class="error-msg"><%=error%></p>

			<!-- リンク先  -->
			<p class="back-link">
				<%
					if (cmd.equals("menu")) {
				%>
				<a href="<%=request.getContextPath()%>/view/menu.jsp">[メニューに戻る]</a>
				<%
					} else if(cmd.equals("logout")) {
				%>
				<a href="<%=request.getContextPath()%>/logout">[ログイン画面へ]</a>
				<%
					}else{
				%>
				<a href="<%=request.getContextPath()%>/list">[一覧画面へ]</a>

				<%} %>
			</p>
		</div>

		<!-- フッター部分 -->
		<%@ include file="/common/footer.jsp"%>

	</div>
</body>
</html>