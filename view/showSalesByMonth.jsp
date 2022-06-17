<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.*,bean.Sale,util.MyFormat"%>

<%
	ArrayList<Sale> sales = (ArrayList<Sale>) request.getAttribute("sale_list");
	MyFormat format = new MyFormat();
	int total_money = 0;
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>売り上げ状況</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/shared.css">
</head>
<body>
	<!-- ヘッダー部分 -->
	<%@ include file="/common/header.jsp"%>
	<div id="nav">
		<a href="<%=request.getContextPath()%>/view/menu.jsp">[メニュー]</a>
	</div>
	<h1 class="page-title">売り上げ状況</h1>

	<table align="center">
		<tr>
			<td>
				<form action="<%=request.getContextPath()%>/showSalesByMonth">
					年<input type=text size="30" name="year"> 月<input type=text
						size="30" name="month"> <input type="submit" name="search"
						value="検索">
				</form>
			</td>
		</tr>
	</table>



	<table align="center">
		<tr>
			<th bgcolor="#6666ff" width="200">isbn</th>
			<th bgcolor="#6666ff" width="200">title</th>
			<th bgcolor="#6666ff" width="200">価格</th>
			<th bgcolor="#6666ff" width="200">数量</th>
			<th bgcolor="#6666ff">売上小計</th>

		</tr>
		<%
			if (sales != null) {

				for (int i = 0; i < sales.size(); i++) {
		%>
		<tr>

			<td align="center" width="100"><%=sales.get(i).getIsbn()%></td>
			<td align="center" width="100"><%=sales.get(i).getTitle()%></td>
			<td align="center" width="100"><%=format.moneyFormat(sales.get(i).getPrice())%></td>
			<td align="center" width="100"><%=sales.get(i).getQuantity()%></td>
			<%
				total_money += (sales.get(i).getPrice() * sales.get(i).getQuantity());
			%>
			<td align="center" width="100"><%=total_money%></td>

		</tr>

		<%
			}
			}
		%>

	</table>
	<hr align="center" size="5" color="blue" width="950">


	<h3 align="center"><%=format.moneyFormat(total_money)%>円
	</h3>


	<%@ include file="/common/footer.jsp"%>
</body>
</html>