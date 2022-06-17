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

import bean.OrderedItem;
import dao.OrderedItemDAO;

public class ShowOrderedItemServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//		ここで出力させたいのはuserid,title,dateだが
		//		orderinfoにはuserid,dateしかない
		//		↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
		//		bookinfo と結合してデータを取ってくる。

		String error = "";
		OrderedItemDAO ordereditemDao = new OrderedItemDAO();

		try {
			//			購入履歴を取得する
			ArrayList<OrderedItem> orderedItem = ordereditemDao.selectAll();
			request.setAttribute("ordered_list",orderedItem);
		} catch (IllegalStateException e) {
			error = "DB接続エラーの為、購入状況確認は出来ません。";
			request.setAttribute("error", error);
			request.setAttribute("cmd", "logout");
			request.getRequestDispatcher("/view/error.jsp").forward(request,response);
			// TODO: handle exception
		}finally{
			request.getRequestDispatcher("/view/showOrderedItem.jsp").forward(request, response);
		}
	}
}
