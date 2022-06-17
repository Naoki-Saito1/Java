package servlet;

import javax.servlet.http.*;

import bean.User;
import dao.UserDAO;

import java.io.*;

import javax.servlet.*;

public class DetailUserServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		String error = "";
		String cmd = "";
		String userid = request.getParameter("userid");
		String param = request.getParameter("param");

		UserDAO userDao = new UserDAO();
		User user = null;
		try {


			user = userDao.selectByUser(userid);



		} catch (IllegalStateException e) {
			error = "DB接続エラーの為、ユーザーの詳細情報は表示出来ません。";
			cmd ="logout";


			// TODO: handle exception
		}finally {
			if(error.equals("")) {
				request.setAttribute("user",user);
				if(param.equals("detail")) {
//					詳細へ
					request.getRequestDispatcher("/view/detailUser.jsp").forward(request,response);
				}else {
//					更新へ
					request.getRequestDispatcher("/view/updateUser.jsp").forward(request,response);
				}

			}else {
//				エラーがある場合
				request.setAttribute("error", error);
				request.setAttribute("cmd", cmd);
				request.getRequestDispatcher("/view/error.jsp").forward(request,response);
			}

		}

	}
}
