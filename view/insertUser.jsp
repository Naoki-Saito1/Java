<%@ page language="java" contentType="text/html; charset=UTF-8"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザー登録</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/shared.css">
</head>
</head>
<body>
	<!-- ヘッダー部分 -->
	<%@ include file="/common/header.jsp"%>
<div id="nav">
		<a href="<%=request.getContextPath()%>/view/menu.jsp">[メニュー]</a>
	<a href="<%=request.getContextPath()%>/listUser">[ユーザー一覧]</a>
	</div>
	<h1 class="page-title">ユーザー登録</h1>

	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<form action="<%=request.getContextPath()%>/insertUser" method="post">

		<table>
			<tr>
				<th>ユーザー</th>
				<td><input type=text name="user"></td>
			</tr>
			<tr>
				<th>パスワード</th>
				<td><input type=password name="password"></td>
			</tr>
			<tr>
				<th>パスワード（確認用）</th>
				<td><input type=password name="password_confirm"></td>
			</tr>
			<tr>
				<th>Email</th>
				<td><input type="text" name="email"></td>
			</tr>
			<tr>
				<th>権限</th>
				<td><select name="authority"">
						<option value="1">一般</option>
						<option value="2">管理者</option>
				</select></td>
			</tr>

			<tr>
				<th></th>
				<td><input type="submit" value="登録"></td>
			</tr>


		</table>

	</form>

	<!-- フッター部分 -->
	<%@ include file="/common/footer.jsp"%>
</body>
</html>