//
//プログラム名	書籍管理システムWeb版 Ver2.0
//プログラムの説明	書籍を管理するシステムCRUD機能、検索機能、書籍購入機能、メール送信機能
//作成者	齋藤直希
//作成日	2022年5月30日

package servlet;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

import bean.User;
import dao.UserDAO;

public class LoginServlet extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		String userid = request.getParameter("userid");
		String password = request.getParameter("password");
		String error = "";

		//		User（DTO）定義
		User user = null;
		//		セッションオブジェクト生成
		HttpSession session = request.getSession();

		try {

			if(userid.equals("")|| password.equals("")) {
				error = "データを入力してください。";
				request.setAttribute("error", error);
				request.setAttribute("cmd", "logout");
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);
				return;
			}



			UserDAO userDao = new UserDAO();
			//			db内に登録されているidとpassが一致したオブジェクトを取得
			user = userDao.selectByUser(userid, password);



			System.out.print(user.getAuthority());
		} catch (IllegalStateException e) {

			error = "DB接続エラーの為、ログインは出来ません。";
			request.setAttribute("error", error);
			request.setAttribute("cmd", "logout");
			request.getRequestDispatcher("/view/error.jsp").forward(request, response);

			// TODO: handle exception
		} finally {
			if (user == null) {
				request.setAttribute("message", "入力データが間違っています！！！");
				request.getRequestDispatcher("/view/login.jsp").forward(request, response);

			}

			//				これでユーザー情報を他のページでも取得できる。
			//				ログイン中使いたい値や共通で使いたい値などはセッションにセットしておく。？
			session.setAttribute("user", user);
			Cookie userCookie = new Cookie("userid", userid);
			//				有効期限・60秒*60分*24時間*1日
			userCookie.setMaxAge(60 * 60 * 24 * 5);
			//	 			レスポンスでクッキー返してクライアントpcに保持させる
			response.addCookie(userCookie);

			//パスワード用クッキーの生成
			Cookie passwordCookie = new Cookie("password", password);
			passwordCookie.setMaxAge(60 * 60 * 24 * 5);
			response.addCookie(passwordCookie);

			request.getRequestDispatcher("/view/menu.jsp").forward(request, response);



		}

	}
}
