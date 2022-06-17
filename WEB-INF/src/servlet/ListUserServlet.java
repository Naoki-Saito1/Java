package servlet;
import java.io.*;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import bean.User;
import dao.UserDAO;

public class ListUserServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		String error = "";
		String cmd = "";

		HttpSession session = request.getSession();
		//		currentUser
		User user = (User)session.getAttribute("user");


		ArrayList<User> userList;
		UserDAO userDao = new UserDAO();

		String searchUserid = request.getParameter("searchUserid");
		String searchParam = request.getParameter("search");

		try {

			if(user == null ) {
				error = "セッション切れの為、ユーザ一覧表示は行えません。";
				cmd = "logout";
				return;

			}
				//				全件検索
				userList = userDao.selectAll();

				request.setAttribute("user_list", userList);

			if(searchParam != null){
				//キーワード検索メソッド呼び出し

				userList = userDao.serch(searchUserid);

				request.setAttribute("user_list", userList);
			}

		} catch (IllegalStateException e) {
			error = "DB接続エラーの為、ユーザ一覧は表示出来ません。";
			cmd ="logout";
		}finally {

			if(error.equals("")) {
				request.getRequestDispatcher("/view/listUser.jsp").forward(request, response);
			}else {
				request.setAttribute("error", error);
				request.setAttribute("cmd", cmd);
				request.getRequestDispatcher("/view/error.jsp").forward(request,response);

			}

		}


	}
}
