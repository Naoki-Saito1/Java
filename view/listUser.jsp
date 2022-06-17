

<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="java.util.ArrayList,bean.User"%>


<%
ArrayList<User> user_list = (ArrayList<User>)request.getAttribute("user_list");


%>



<html>
<head>
<title>ユーザー一覧</title>
</head>
<body>
	<h1 align="center">書籍管理システムweb版ver.3.0</h1>
	<hr align="center" size="5" color="blue" width="950"></hr>
	<table align="center" width="850">
		<tr>
			<td width="80">[<a
				href="<%=request.getContextPath()%>/view/menu.jsp">メニュー</a>]
			</td>
			<td width="80">[<a
				href="<%=request.getContextPath()%>/view/insertUser.jsp">ユーザー登録</a>]
			</td>
			<td width="508" align="center"><font size="5">ユーザー一覧</font></td>
			<td width="80">&nbsp;</td>
			<td width="80">&nbsp;</td>
		</tr>
	</table>

	<hr align="center" size="2" color="black" width="950"></hr>

	<table align="center">
		<tr>
			<td>
				<form action="<%=request.getContextPath()%>/listUser">
					ユーザー<input type=text size="30" name="searchUserid">
					 <input type="submit" name="search" value="検索"></input>
				</form>
				<form action="<%=request.getContextPath()%>/listUser">
					<input type=hidden size="30" name="selectAll">
					 <input type="submit" name="selectAll" value="全件検索"></input>
				</form>
			</td>
		</tr>
	</table>


	<table align="center">
		<tr>
			<th bgcolor="#6666ff" width="200">ユーザー</th>
			<th bgcolor="#6666ff" width="450">Email</th>
			<th bgcolor="#6666ff" width="100">権限</th>
			<th bgcolor="#6666ff" width="200" colspan="2">変更/削除</th>

		</tr>
		<%
			if (user_list != null) {
				for (int i = 0; i < user_list.size(); i++) {
					User user = (User) user_list.get(i);

					if(user.getAuthority().equals("1")){
						user.setAuthority("一般ユーザー");
					}else{
						user.setAuthority("管理者");
					}
		%>
		<tr>
			<td align="center" width="200" ><a href="<%= request.getContextPath() %>/detailUser?userid=<%=user.getUserid() %>&param=detail">
			<%=user.getUserid() %></a></td>
			<td align="center" width="450" ><%=user.getEmail() %></td>
			<td align="center" width="100" ><%=user.getAuthority() %></td>
			<td align="center" width="200"  colspan="2" >
			<a href="<%= request.getContextPath() %>/detailUser?param=update&userid=<%=user.getUserid()%>">変更</a>
			<a id="btn" href="<%= request.getContextPath() %>/deleteUser?userid=<%=user.getUserid()%>">削除</a>
			</td>
		</tr>
		<%
			}
			} else {
		%>
		<tr>
			<td align="center" width="200">&nbsp;</td>
			<td align="center" width="200">&nbsp;</td>
			<td align="center" width="200">&nbsp;</td>
			<td align="center" width="250" colspan="2">&nbsp;</td>
		</tr>
		<%
			}
		%>
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

	<hr align="center" size="5" color="blue" width="950"></hr>

	<table border=0 align="center" width="950" summary="フッター表示">
		<tr>
			<td>copyright (c) 2010 all rights reserved.</td>
		</tr>
	</table>



</body>
</html>