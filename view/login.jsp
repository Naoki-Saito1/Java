<!--
プログラム名	書籍管理システムWeb版 Ver2.0
プログラムの説明	書籍を管理するシステムCRUD機能、検索機能、書籍購入機能、メール送信機能
作成者	齋藤直希
作成日	2022年5月30日 -->


<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<%
	String user = ""; //ユーザーを管理する変数
	String pass = "";
	//パスワードを管理する変数
	String message = (String) request.getAttribute("message");
	if(message == null){
		message = "";
	}
	Cookie[] userCookie = request.getCookies();

	if (userCookie != null) {
		for (int i = 0; i < userCookie.length; i++) {
			//クッキーからユーザー情報の取得
			if (userCookie[i].getName().equals("userid")) {
				user = userCookie[i].getValue();
			}
			//クッキーからパスワード情報の取得
			if (userCookie[i].getName().equals("password")) {
				pass = userCookie[i].getValue();
			}
		}
	}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン画面</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/login.css">
</head>
<body>
	<!-- ヘッダー部分 -->
	<%@ include file="/common/header.jsp"%>
	<div class="page-title">
		<h1>Login</h1>
	</div>
	<p><%=message%></p>
	<form class="login-form" action="<%=request.getContextPath()%>/login"
		method="post">


		<table>
			<tr>
				<th>ユーザー</th>
				<td><input type="text" name="userid" value=<%=user%>></td>
			</tr>

			<tr>
				<th>パスワード</th>
				<td><input type="password" name="password" value=<%=pass%>></td>
			</tr>
		</table>
		<br> <br> <br>
		<div class="submit">
			<input type="submit" value="ログイン">
		</div>
	</form>

	<!-- フッター部分 -->
	<%@ include file="/common/footer.jsp"%>
</body>
</html>