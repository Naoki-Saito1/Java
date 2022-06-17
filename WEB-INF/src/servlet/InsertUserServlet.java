package servlet;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.*;

import bean.User;
import dao.UserDAO;

public class InsertUserServlet extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//		エラー画面遷移用変数
		String error = "";
		String cmd = "";

		request.setCharacterEncoding("UTF-8");
		//		登録用パラメタ
		String userid = request.getParameter("user");
		String password = request.getParameter("password");
		String password_confirm = request.getParameter("password_confirm");
		String email = request.getParameter("email");
		String authority = request.getParameter("authority");
		//		戻り値用
		int count = 0;

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


			User userTest = userDao.selectByUser(userid, password);
System.out.println(userTest);
			if(userTest != null) {
				error="入力ユーザー名は既に使用済み為、登録できません。";
				cmd = "menu";
				return ;
			}


			if( ! (authority.equals("1") || authority.equals("2") ) ) {
				error = "権限は「１」または「２」を入力して下さい。";
				cmd = "menu";
				return ;
			}





			user.setUserid(userid);
			user.setPassword(password);
			user.setEmail(email);
			user.setAuthority(authority);

			count = userDao.insert(user);

			System.out.println(count);

			if(count == 0) {
				error= "クエリ発行に失敗しました。 ";
				cmd = "logout";
			}

		} catch (Exception e) {
			error ="DBに接続出来ずユーザー登録は出来ませんでした。";
			cmd = "logout";


			// TODO: handle exception
		}finally {
			if(error.equals("")) {
				request.getRequestDispatcher("/view/menu.jsp").forward(request, response);
			}else {
				request.setAttribute("error", error);
				request.setAttribute("cmd", cmd);
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);

			}

		}

	}
}
