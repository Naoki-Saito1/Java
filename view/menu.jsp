<!--
プログラム名	書籍管理システムWeb版 Ver2.0
プログラムの説明	書籍を管理するシステムCRUD機能、検索機能、書籍購入機能、メール送信機能
作成者	齋藤直希
作成日	2022年5月30日 -->


<%@page contentType="text/html;charset=UTF-8"%>
<%@page import="bean.User"%>

<!-- flashMessage用トライ-->
<%
	String message = (String) request.getAttribute("message");
	User user = (User) session.getAttribute("user");
%>


<html>
<head>
<title>書籍管理メニュー画面</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/menu.css">
</head>
<body>
	<h1 align="center">書籍管理システムweb版ver.3.0</h1>
	<hr align="center" size="5" color="blue" width="950">


	<div align="center">
		<font size="6">menu</font>
	</div>


	<hr align="center" size="2" color="black" width="950">
	<div style="position: relative; top: -80; left: 950;"></div>
	<%
		if (message != null) {
	%>
	<p style="color: red;" align="center"><%=message%></p>
	<%
		}
	%>



	<table border=0 align="center" summary="メニュー表示">


		<tr>
			<td><a href="<%=request.getContextPath()%>/list">【書籍一覧▼】</a></td>
		</tr>


		<%
			if (user.getAuthority().equals("1")) {
		%>

		<tr>
			<td><a href="<%=request.getContextPath()%>/showCart">【カート状況確認▼】</a></td>
		</tr>
		<tr>
			<td><a
				href="<%=request.getContextPath()%>/showHistoryOrderedItem">【購入履歴▼】</a></td>
		</tr>



		<%
			} else {
		%>
		<tr>
			<td><a href="<%=request.getContextPath()%>/view/insert.jsp">【書籍登録▼】</a></td>
		</tr>


		<tr>
			<td><a href="<%=request.getContextPath()%>/insertIniData">【初期データ登録▼(データがない場合のみ)】</a></td>
		</tr>


		<tr>
			<td><a href="<%=request.getContextPath()%>/showOrderedItem">
					【購入状況確認▼】</a></td>
		</tr>

		<tr>
			<td><a
				href="<%=request.getContextPath()%>/view/showSalesByMonth.jsp">【売上状況▼】</a></td>
		</tr>

		<tr>
			<td><a href="<%=request.getContextPath()%>/view/insertUser.jsp">【ユーザー登録▼】</a></td>
		</tr>

		<tr>
			<td><a href="<%=request.getContextPath()%>/listUser">【ユーザー一覧▼】</a></td>
		</tr>


		<%
			}
		%>







		<tr>
			<td><a
				href="<%=request.getContextPath()%>/view/changePassword.jsp">【パスワード変更▼】</a></td>
		</tr>


		<tr>
			<td><a href="<%=request.getContextPath()%>/logout">【ログアウト▼】</a></td>
		</tr>



	</table>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>

	<hr align="center" size="5" color="blue" width="950">
	<table border=0 align="center" width="950" summary="フッター表示">
		<tr>
			<td>copyright (c) 2010 all rights reserved.</td>
		</tr>
	</table>
</body>
</html>