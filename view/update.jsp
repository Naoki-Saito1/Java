<!--
プログラム名	書籍管理システムWeb版 Ver2.0
プログラムの説明	書籍を管理するシステムCRUD機能、検索機能、書籍購入機能、メール送信機能
作成者	齋藤直希
作成日	2022年5月30日 -->


<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="bean.Book,util.MyFormat"%>

<%
	Book book = (Book) request.getAttribute("book");
	MyFormat format = new MyFormat();
%>

<html>
<head>
<title>書籍変更</title>
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

						<a href="<%=request.getContextPath()%>/view/menu.jsp">[メニュー]</a>
						<a href="<%=request.getContextPath()%>/view/insert.jsp">[書籍登録]</a>
						<a href="<%=request.getContextPath()%>/list">[書籍一覧]</a>

				</div>

				<!-- ページタイトル -->
				<div class="page-title">
					<h1>書籍変更</h1>
				</div>


		<!-- 書籍変更コンテンツ部分 -->
		<div class="container">
			<!-- 変更画面 -->
			<form action="<%=request.getContextPath()%>/update">
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
							<th class="th-color">ISBN</th>
							<td class="td-color"><%=book.getIsbn()%></td>
							<td><%=book.getIsbn()%></td>
						</tr>
						<tr>
							<th class="th-color">TITLE</th>
							<td class="td-color"><%=book.getTitle()%></td>
							<td><input  type="text" name="title" placeholder="TITLE"></td>
						</tr>
						<tr>
							<th class="th-color">価格</th>
							<td class="td-color"><%=format.moneyFormat(book.getPrice())%></td>
							<td><input type="text" name="price" placeholder="価格を入力"></td>
						</tr>
					</tbody>
				</table>
				<input type="hidden" name="isbn" value="<%=book.getIsbn()%>">

				<input class="submit-button" type="submit" value="変更完了">
			</form>
		</div>

		<!-- フッター部分 -->
		<%@ include file="/common/footer.jsp"%>

	</div>

</body>
</html>
