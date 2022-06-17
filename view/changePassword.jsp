<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@page import="bean.User"%>

<%
	//セッションからユーザー情報を取得
	User user = (User) session.getAttribute("user");
	//セッション切れか確認
	if (user == null) {
		//セッション切れならerror.jspへフォワード
		request.setAttribute("error", "セッション切れの為、メニュー画面が表示できませんでした。");
		request.setAttribute("cmd", "logout");
		request.getRequestDispatcher("/view/error.jsp").forward(request, response);
		return;
	}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>パスワード変更</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/shared.css">
</head>
<body>
	<%@ include file="/common/header.jsp"%>
	<div id="nav">
		<a href="<%=request.getContextPath()%>/view/menu.jsp">[メニュー]</a>

	</div>
	<h1 class="page-title">パスワード変更</h1>

	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<form style="text-align: center;"
		action="<%=request.getContextPath()%>/changePassword">
		<table align="center">

			<tr>
				<th>ユーザー</th>
				<td><%=user.getUserid()%></td>
			</tr>

			<tr>
				<th>旧パスワード</th>
				<td><input type="password" name="oldPass"></td>
			</tr>


			<tr>
				<th>新パスワード</th>
				<td><input type="password" name="newPass1"></td>
			</tr>


			<tr>
				<th>新パスワード(確認用)</th>
				<td><input type="password" name="newPass2"></td>
			</tr>

		</table>
		<input type="submit" value="変更" align="center">
	</form>

	<%@ include file="/common/footer.jsp"%>
</body>
</html>