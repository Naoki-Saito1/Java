package servlet;

import javax.servlet.http.*;

import dao.UserDAO;

import java.io.*;

import javax.servlet.*;

public class DeleteUserServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		String error = "";
		int count = 0;
		String userid = request.getParameter("userid");

		UserDAO userDao = new UserDAO();

		try {

			count = userDao.delete(userid);

		} catch (IllegalStateException e) {
			error= "DB接続エラーの為、ユーザー削除は行えません。";
//			cmd = "";
			// TODO: handle exception
		} finally {
			if (error.equals("")) {
				request.getRequestDispatcher("/listUser").forward(request, response);

			} else {
				//				エラーがある場合
				request.setAttribute("error", error);
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);
			}

		}
	}
}
