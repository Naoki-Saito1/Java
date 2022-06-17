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
import dao.BookDAO;

public class SearchServlet extends HttpServlet {
	public void doGet(HttpServletRequest request ,HttpServletResponse response) throws ServletException ,IOException{



		String error = "";
		String cmd = "";

		try {
			request.setCharacterEncoding("UTF-8");

			String isbn = request.getParameter("isbn");
			String title = request.getParameter("title");
			String price = request.getParameter("price");

			BookDAO bookDao = new BookDAO();

			ArrayList<Book> bookList = bookDao.search(isbn, title, price);
			System.out.println(bookList);

			request.setAttribute("book_list", bookList);


		} catch (IllegalStateException e) {
			error= "DB接続エラーの為、一覧表示は行なえませんでした。";
			cmd ="logout";
			// TODO: handle exception
		}finally{
			if(error.equals("")) {
				request.getRequestDispatcher("/view/list.jsp").forward(request, response);
			}else {
				request.setAttribute("error", error);
				request.setAttribute("cmd", cmd);
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);
			}
		}

	}
}
