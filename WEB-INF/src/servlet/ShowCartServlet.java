//
//プログラム名	書籍管理システムWeb版 Ver2.0
//プログラムの説明	書籍を管理するシステムCRUD機能、検索機能、書籍購入機能、メール送信機能
//作成者	齋藤直希
//作成日	2022年5月30日

package servlet;

import java.io.*;
import java.util.ArrayList;

import javax.servlet.*;
import javax.servlet.http.*;

import bean.Book;
import bean.Order;
import bean.User;
import dao.BookDAO;


//カート状況
public class ShowCartServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String error= "";
		//		削除用パラメタ・中身（index）番号
		String delno = request.getParameter("delno");
		HttpSession session = request.getSession();
		//		currentUser
		User user = (User)session.getAttribute("user");

		//		insetIntoでorder_listにorder情報をsession.setAttributeしてるそれを取得
		ArrayList<Order> order_list = (ArrayList<Order>)session.getAttribute("order_list");

		//		delnoに値がある場合それを削除
		if(delno != null) {
			order_list.remove(Integer.parseInt(delno));
		}

		BookDAO bookDao = new BookDAO();
		ArrayList<Book> Book_list = new ArrayList<Book>();

		try {

			//			viewにbookinfoテーブルで表示するために
			//			・orderのisbnをキーにしてBookオブジェクトを取得する。
			for(int i=0; i < order_list.size(); i++) {
				Book_list.add(bookDao.selectByIsbn(order_list.get(i).getIsbn()));
			}

		} catch (IllegalStateException e) {
			error = "DB接続エラーの為、カート状況は確認は出来ません。";
			request.setAttribute("error", error);
			request.setAttribute("cmd", "logout");
			request.getRequestDispatcher("/view/error.jsp").forward(request,response);
			// TODO: handle exception

		}finally {
			if(user == null) {
				//Session expired.
				error = "セッション切れの為、カート状況は出来ません。";
				request.setAttribute("error", error);
				request.setAttribute("cmd", "logout");
				request.getRequestDispatcher("/view/error.jsp").forward(request,response);

			}else {
				//				正常
				request.setAttribute("book_list",Book_list);
				request.getRequestDispatcher("/view/showCart.jsp").forward(request, response);
			}

		}

	}

}
