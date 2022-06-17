package servlet;

import javax.servlet.http.*;

import bean.User;
import dao.UserDAO;

import java.io.*;

import javax.servlet.*;

public class UpdateUserServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String error = "";
		String cmd = "";

		String userid = request.getParameter("userid");
		String password = request.getParameter("password");
		String password_confirm = request.getParameter("password_confirm");
		String email = request.getParameter("email");
		String authority = request.getParameter("authority");

		int count=0;
		UserDAO userDao = new UserDAO();
		User user = new User();
		try {
			if(userid.equals("") || password.equals("") || password_confirm.equals("") ||
					email.equals("") || authority.equals("")) {
				error = "未入力項目があります。やりなおし！";
				cmd = "logout";
				return ;
			}

			if(!(password.equals(password_confirm))) {
				error = "パスワードが一致しません";
				cmd = "menu";
				return ;
			}


			user.setUserid(userid);
			user.setPassword(password);
			user.setEmail(email);
			user.setAuthority(authority);

			count = userDao.update(user);
			System.out.println(count);

			if(count == 0) {
				error= "クエリ発行に失敗しました。 ";
				cmd = "logout";
				return;
			}

		} catch (IllegalStateException e) {
			error = "DB接続エラーの為、ユーザーの詳細情報は表示出来ません。";
			cmd ="logout";


			// TODO: handle exception
		}finally {
			if(error.equals("")) {
				request.getRequestDispatcher("/listUser").forward(request,response);

			}else {
				//				エラーがある場合
				request.setAttribute("error", error);
				request.setAttribute("cmd", cmd);
				request.getRequestDispatcher("/view/error.jsp").forward(request,response);
			}

		}

	}
}
