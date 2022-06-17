//
//プログラム名	書籍管理システムWeb版 Ver2.0
//プログラムの説明	書籍を管理するシステムCRUD機能、検索機能、書籍購入機能、メール送信機能
//作成者	齋藤直希
//作成日	2022年5月30日

package servlet;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.http.HttpSession;

public class LogoutServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {

		//セッションオブジェクトの取得
		HttpSession session = request.getSession();
		//セッションがある場合、セッションを破棄
		if(session != null){
			session.invalidate();
		}
		request.setAttribute("message", "ログアウトしました。");
		request.getRequestDispatcher("/view/login.jsp").forward(request, response);
	}
}