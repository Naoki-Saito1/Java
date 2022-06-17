<%@page import="bean.User"%>
<%@page contentType="text/html;charset=UTF-8"%>


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

<p>
	名前:<%=user.getUserid()%></p>

<%
	if (user.getAuthority().equals("1")) {
%>
<p>権限:一般ユーザー</p>
<%
	} else {
%>
<p>権限:管理者</p>
<%
	}
%>

