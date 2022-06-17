
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
<title>ユーザー詳細情報</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/detail.css">
</head>
<body>
	<!-- ブラウザ全体 -->
	<!-- ヘッダー部分 -->
	<%@ include file="/common/header.jsp"%>
	<div id="nav">
		<a href="<%=request.getContextPath()%>/view/menu.jsp">[メニュー]</a> <a
			href="<%=request.getContextPath()%>/view/insertUser.jsp">[ユーザー登録]</a>
		<a href="<%=request.getContextPath()%>/listUser">[ユーザー一覧]</a>

	</div>
	<h1 class="detail-title">ユーザー詳細情報</h1>


	<div class="wrap">
		<div class="ud-button">
			<form action="<%=request.getContextPath()%>/detailUser"
				class="inline-block">
				<input type="hidden" name="userid" value="<%=user.getUserid()%>">
				<input type="hidden" name="param" value="update"></input> <input
					type="submit" value="変更">
			</form>

			<form action="<%=request.getContextPath()%>/deleteUser"
				class="inline-block">
				<input type="hidden" name="userid" value="<%=user.getUserid()%>">
				<input type="submit" value="削除">
			</form>

		</div>


		<table>
			<tr>
				<th>ユーザー</th>
				<td><%=user.getUserid()%></td>
			</tr>
			<tr>
				<th>パスワード</th>
				<td><%=user.getPassword()%></td>
			</tr>
			<tr>
				<th>Email</th>
				<td><%=user.getEmail()%></td>
			</tr>

			<tr>
				<th>権限</th>
				<td><%=user.getAuthority()%></td>
			</tr>

		</table>

	</div>



	<!-- フッター部分 -->
	<%@ include file="/common/footer.jsp"%>

</body>
</html>