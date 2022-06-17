<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="bean.User"%>

<%
User user = (User)request.getAttribute("user");
if(user.getAuthority().equals("1")){
	user.setAuthority("一般ユーザー");
}else{
	user.setAuthority("管理者");
}
%>

<html>
<head>
<title>ユーザー変更</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/update.css">
</head>
<body>
	<!-- ブラウザ全体 -->
	<div id="wrap">
		<!-- ヘッダー部分 -->
		<%@ include file="/common/header.jsp"%>

		<!-- メニュー部分 -->
		<!-- ナビゲーション  -->
		<div id="nav">

			<a href="<%=request.getContextPath()%>/view/menu.jsp">[メニュー]</a> <a
				href="<%=request.getContextPath()%>/view/insertUser.jsp">[ユーザー登録]</a>
			<a href="<%=request.getContextPath()%>/listUser">[ユーザー一覧]</a>

		</div>

		<!-- ページタイトル -->
		<div class="page-title">
			<h1>ユーザー変更</h1>
		</div>


		<!-- 書籍変更コンテンツ部分 -->
		<div class="container">
			<!-- 変更画面 -->
			<form action="<%=request.getContextPath()%>/updateUser">
				<table class="input-table">
					<thead>
						<tr>
							<td></td>
							<th>&lt;&lt;変更前情報&gt;&gt;</th>
							<th>&lt;&lt;変更後情報&gt;&gt;</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th class="th-color">ユーザー</th>
							<td class="td-color"><%=user.getUserid()%></td>
							<td><%=user.getUserid()%></td>
						</tr>
						<tr>
							<th class="th-color">パスワード</th>
							<td class="td-color"><%=user.getPassword()%></td>
							<td><input type="password" name="password"
								placeholder="パスワード"></td>
						</tr>

						<tr>
							<th class="th-color">パスワード(確認用)</th>
							<td class="td-color"></td>
							<td><input type="password" name="password_confirm"
								placeholder="パスワード(確認用)"></td>
						</tr>
						<tr>
							<th class="th-color">Email</th>
							<td class="td-color"><%=user.getEmail()%></td>
							<td><input type="text" name="email" placeholder="Email"></td>
						</tr>
						<tr>
							<th class="th-color">権限</th>
							<td class="td-color"><%=user.getAuthority()%></td>
							<td><select name="authority">
									<option value="1">一般</option>
									<option value="2">管理者</option>
							</select></td>
						</tr>

					</tbody>
				</table>
				<input type="hidden" name="userid" value="<%=user.getUserid()%>">

				<input class="submit-button" type="submit" value="変更完了">
			</form>
		</div>

		<!-- フッター部分 -->
		<%@ include file="/common/footer.jsp"%>

	</div>

</body>
</html>