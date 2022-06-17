<!--
プログラム名	書籍管理システムWeb版 Ver2.0
プログラムの説明	書籍を管理するシステムCRUD機能、検索機能、書籍購入機能、メール送信機能
作成者	齋藤直希
作成日	2022年5月30日 -->



<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="java.util.ArrayList,bean.Book,util.MyFormat"%>


<!--String message = (String) request.getAttribute("message");-->
<%
	MyFormat format = new MyFormat();
%>


<html>
<head>
<title>書籍一覧</title>
</head>
<body>
	<h1 align="center">書籍管理システムweb版ver.1.0</h1>
	<hr align="center" size="5" color="blue" width="950"></hr>
	<table align="center" width="850">
		<tr>
			<td width="80">[<a
				href="<%=request.getContextPath()%>/view/menu.jsp">メニュー</a>]
			</td>
			<td width="80">[<a
				href="<%=request.getContextPath()%>/view/insert.jsp">書籍登録</a>]
			</td>
			<td width="508" align="center"><font size="5">書籍一覧</font></td>
			<td width="80">&nbsp;</td>
			<td width="80">&nbsp;</td>
		</tr>
	</table>

	<hr align="center" size="2" color="black" width="950"></hr>

	<table align="center">
		<tr>
			<td>
				<form action="<%=request.getContextPath()%>/search">
					isbn：<input type=text size="30" name="isbn"></input> title：<input
						type=text size="30" name="title"></input> 価格：<input type=text
						size="30" name="price"></input> <input type="submit" name="search"
						value="検索"></input>
				</form>
			</td>
			<td>
				<form action="<%=request.getContextPath()%>/list">
					<input type="submit" name="searchall" value="全件表示"></input>
				</form>
			</td>
		</tr>
	</table>


	<table align="center">
		<tr>
			<th bgcolor="#6666ff" width="200">isbn</th>
			<th bgcolor="#6666ff" width="200">title</th>
			<th bgcolor="#6666ff" width="200">価格</th>
			<th bgcolor="#6666ff" width="250" colspan="2">変更/削除</th>
			<th bgcolor="#6666ff">購入数/カート</th>

		</tr>
		<%
			ArrayList<Book> list = (ArrayList<Book>) request.getAttribute("book_list");
			if (list != null) {
				for (int i = 0; i < list.size(); i++) {
					Book books = (Book) list.get(i);
		%>
		<tr>
			<td align="center" width="100"><a
				href="<%=request.getContextPath()%>/detail?isbn=<%=books.getIsbn()%>&cmd=detail"><%=books.getIsbn()%></a></td>
			<td align="center" width="100"><%=books.getTitle()%></td>
			<td align="center" width="100"><%=format.moneyFormat(books.getPrice())%></td>
			<td align="center" width="122"><a
				href="<%=request.getContextPath()%>/detail?isbn=<%=books.getIsbn()%>&cmd=update">変更</a>
			</td>
			<td align="left" width="122"><a
				href="<%=request.getContextPath()%>/delete?isbn=<%=books.getIsbn()%>">削除</a>
			</td>
			<td width="122">
				<form action="<%=request.getContextPath()%>/insertIntoCart">
					<input type="hidden" name="isbn" value="<%=books.getIsbn()%>">
					<input type="text" name="quantity" size=20> <input
						type="submit" value="カートに入れる">
				</form>
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