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


//カート追加機能
public class InsertIntoCartServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String error = "";
		//		sessionオブジェクト
		HttpSession session = request.getSession();
		//		currentUser
		User user = (User)session.getAttribute("user");

//		list.jspからパラメタで送られてくる。
		String isbn = request.getParameter("isbn");
		String quantity = request.getParameter("quantity");




		try {
//			カートに追加する書籍情報
			BookDAO bookDao = new BookDAO();
			Book book = bookDao.selectByIsbn(isbn);
			request.setAttribute("book",book);

			//			オーダー１件追加するためにDTOクラスをオブジェクト化
			//			追加したいisbn
			//			追加するuserid
			//			追加する数量
			Order order = new Order();
			order.setIsbn(isbn);
			order.setUserid(user.getUserid());
			order.setQuantity(Integer.parseInt(quantity));

//			web3.0で必要１件のオーダー情報(数量)
			request.setAttribute("order",order );

			ArrayList<Order> list = (ArrayList<Order>)session.getAttribute("order_list");

			//			もしオーダーリストが空ならArrayListインスタンス生成
			if(list == null) {
				list = new ArrayList<Order>();
			}
			//			オーダーリストに一件文追加する
			//			セッションで持ち運ぶ
			list.add(order);
			session.setAttribute("order_list",list);


		} catch (IllegalStateException e) {
			error = "DB接続エラーの為、カート追加は出来ません。";
			request.setAttribute("error", error);
			request.setAttribute("cmd", "logout");
			request.getRequestDispatcher("/view/error.jsp").forward(request,response);
			// TODO: handle exception
		}finally {
			if(user == null) {
				//Session expired.
				error = "セッション切れの為、カートに追加出来ません。";
				request.setAttribute("error", error);
				request.setAttribute("cmd", "logout");
				request.getRequestDispatcher("/view/error.jsp").forward(request,response);

			}else {
				//				正常

				if(quantity.equals("")) {
					error= "数量を入力してください";
					request.setAttribute("error", error);
					request.setAttribute("cmd", "menu");
					request.getRequestDispatcher("/view/error.jsp").forward(request,response);

//				フォワードとリダイレクトは競合する??
//					response.sendRedirect(request.getContextPath() + "/view/list.jsp");
				}
				request.getRequestDispatcher("/view/insertIntoCart.jsp").forward(request, response);
			}
		}

	}
}
