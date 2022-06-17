package servlet;



import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import bean.User;
import dao.OrderedItemDAO;

public class ShowHistoryOrderedItemServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String error = "";
		String cmd = "";
		HttpSession session = request.getSession();

		//		currentuser
		User user = (User) session.getAttribute("user");

		OrderedItemDAO orderedItemDao = new OrderedItemDAO();

		try {
			if(user == null) {
				error = "セッション切れの為、購入状況の確認は出来ません。";
				cmd = "logout";
				return;
			}



			//			ログイン者の購入履歴
			//			selectByUserメソッドを呼び出した戻り値が「OrderオブジェクトのQuantity」と
			//			「OrderedItemオブジェクトのtitle,date」となりオブジェクトが２つとなる。
			//			しかしreturn文でもどせるのは１つのリストしか返せない。
			//
			//			ですので１回で１文のreturnで２つのオブジェクトを返すには型をＯｂｊｅｃｔ型にしようと
			//			考えた。結果HashMapというものを利用して
			//			("item1",OrderedItemオブジェクト)
			//			("order1",Orderオブジェクト)
			//			("item2",OrderedItemオブジェクト)
			//			("order2",Orderオブジェクト)
			//
			//			上記のような(key,value)の形にして格納した。
			//
			//			それをservletからjspに送り、型をObject型から元の型に（キャスト）して
			//			for文で画面に出力している。
			//			＊＊「しかしlist.size()をすると２倍で格納（item1）(order1)で１データ文なため
			//			size()/2している。値は必ず偶数で入るため(int)型へのキャストはいらない」



			HashMap<String,Object> map  = orderedItemDao.selectByUser(user.getUserid());
			request.setAttribute("map", map);


		} catch (IllegalStateException e) {
			error= "DB接続エラーの為、購入状況確認は出来ません。";
			cmd="logout";



			// TODO: handle exception
		}finally {
			if(error.equals("")) {
				request.getRequestDispatcher("/view/showHistoryOrderedItem.jsp").forward(request, response);
			}else {
				request.setAttribute("error", error);
				request.setAttribute("cmd",cmd);
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);

			}
		}
	}
}
