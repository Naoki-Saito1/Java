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
import dao.OrderDAO;
import util.MailFormat;
import util.SendMail;

//商品購入
public class BuyConfirmServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

//		mail用インスタンス生成
		SendMail sendMail = new SendMail();
//		mailフォーマットインスタンス生成
		MailFormat mailFormat = new MailFormat();

		String error= "";
		//		これは合計金額が⓪円ならカートは空と判断するための変数です。
		int money = (Integer.parseInt(request.getParameter("total")));
		HttpSession session = request.getSession();
		//currentUser
		User user = (User)session.getAttribute("user");
		//		insertIntoCartからのsessionを取得
		ArrayList<Order> order_list =(ArrayList<Order>)session.getAttribute("order_list");

		//		insert(Order)必要な値
		//		・購入者のuserid
		//		・購入者がカートに追加したisbn
		//		・個数

		ArrayList<Book> book_list = new ArrayList<Book>();
		BookDAO bookDao = new BookDAO();
		OrderDAO orderDao = new OrderDAO();



		try {
			//			order_listの中にuserid/isbn/quantityが入ってル
			//			ちなみにorderno/dateはここでは無くても行ける【詳しくはorderDaoクラス確認】

			//			orderインスタンスにデータをセットしてinsertメソッドを呼び出す【数ぶん】

			for(int i =0; i < order_list.size(); i++) {
				Order order = new Order();
				order.setUserid(order_list.get(i).getUserid());
				order.setIsbn(order_list.get(i).getIsbn());
				order.setQuantity(order_list.get(i).getQuantity());
				orderDao.insert(order);

				//				本来いらない気がするが・・一応登録できたisbnカラムのbookinfo 情報をArraylistに格納
				book_list.add(bookDao.selectByIsbn(order_list.get(i).getIsbn()));
			}

		} catch (IllegalStateException e) {
			error = "DB接続エラーの為、購入は出来ません。";
			request.setAttribute("error", error);
			request.setAttribute("cmd", "logout");
			request.getRequestDispatcher("/view/error.jsp").forward(request,response);

			// TODO: handle exception
		}finally {
			if(user == null) {
				//Session expired.
				error = "セッション切れの為、購入は出来ません。";
				request.setAttribute("error", error);
				request.setAttribute("cmd", "logout");
				request.getRequestDispatcher("/view/error.jsp").forward(request,response);

			}else if(money == 0){
				error = "カートの中に何も無かったので購入は出来ません。";
				request.setAttribute("error", error);
				request.setAttribute("cmd", "menu");
				request.getRequestDispatcher("/view/error.jsp").forward(request,response);
			}else {
				//				正常
				//				orderlist空にする・・・・・リロードするとnullpointer e出るよ・・・！！

				request.setAttribute("book_list",book_list);
				request.setAttribute("order_list", order_list);
//				メールフォーマット用クラスの呼び出し（このクラスではメールの本文の生成を行っている）
				String mailText = mailFormat.mailFormat(book_list,money,user);
//				メール送信用メソッドを呼び出している。
				String message = sendMail.SendStart(user, mailText);
//				System.out.println(message);


				session.setAttribute("order_list",null);

				request.getRequestDispatcher("/view/buyConfirm.jsp").forward(request, response);
			}

		}

	}
}
