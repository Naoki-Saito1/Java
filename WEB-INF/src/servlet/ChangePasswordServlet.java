package servlet;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

import bean.User;
import dao.UserDAO;

public class ChangePasswordServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		String error= "";
		String cmd="";



		//		旧パスワードがあってるか検証用currentuser;
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");

		System.out.println(user);
//		変更ボタン押下時に、セッションが切れている
		if(user == null) {
			error= "セッション切れの為、パスワード変更は行えません。 ";
			cmd ="logout";
			return;
		}


//		jspから送られてきたパラメタや必要な変数
		String userid = user.getUserid();
		String oldPass = request.getParameter("oldPass");
		String newPass1 = request.getParameter("newPass1");
		String newPass2 = request.getParameter("newPass2");

		int count = 0;
		UserDAO userDao = new UserDAO();



		try {

//			変更ボタン押下時に、旧パスワードが未入力
			if(oldPass.equals("")) {
				error="旧パスワードを入力して下さい！ ";
				cmd ="menu";
				return;
			}
//			変更ボタン押下時に、新パスワードが未入力
			if(newPass1.equals("")) {
				error="新パスワードを入力して下さい！";
				cmd ="menu";
				return;
			}
//			変更ボタン押下時に、新パスワード(確認用)が未入力
			if(newPass2.equals("")) {
				error="新パスワード(確認用)を入力して下さい！";
				cmd ="menu";
				return;
			}
//			変更ボタン押下時に、旧パスワードが合っていない
			if(!(user.getPassword().equals(oldPass))) {
				error= "旧パスワードが間違っています！";
				cmd ="menu";
				return;
			}
			//			変更ボタン押下時に、新と確認のパスワードとが一致しない
			if(!(newPass1.equals(newPass2))) {
				error="新パスワードと確認パスワードが合っていません！";
				cmd ="menu";
				return;
			}

			count = userDao.updateForPassword(userid, newPass2);

			if(count == 0) {
				error="クエリ発行に失敗しました。";
				cmd ="logout";
			}
			System.out.println(count);

			//			これでセッションに再格納できるらしい
			//			ログインしているためdb変更してsetPasswordにセットして値を持ち運べる？
			user.setPassword(newPass1);
			session.setAttribute("user", user);




			//			変更ボタン押下時に、DBに接続できない
		} catch (IllegalStateException e) {
			error="DB接続エラーの為、パスワード変更は行えません。";
			cmd ="logout";
			// TODO: handle exception

		} catch (Exception e) {
			error="原因不明のエラーです。申し訳ありません";
			cmd ="logout";
			System.out.println(e);
		}finally {
			if(error.equals("")) {
				//				正常
				request.setAttribute("message", "パスワード" + count +"件更新しました。");
				request.setAttribute("cmd", cmd);
				request.getRequestDispatcher("/view/menu.jsp").forward(request, response);

			}else {
				//				エラーあれば
				request.setAttribute("error", error);
				request.setAttribute("cmd", cmd);
				request.getRequestDispatcher("/view/error.jsp").forward(request,response);

			}

		}

	}

}
