//
//プログラム名	書籍管理システムWeb版 Ver2.0
//プログラムの説明	書籍を管理するシステムCRUD機能、検索機能、書籍購入機能、メール送信機能
//作成者	齋藤直希
//作成日	2022年5月30日

package servlet;


import java.io.*;
import java.util.ArrayList;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Book;
import dao.BookDAO;

public class ListServlet extends HttpServlet{


	public void doGet(HttpServletRequest request ,HttpServletResponse response) throws ServletException ,IOException{

		String error= "";
		BookDAO objDao = new BookDAO();

		try {
			ArrayList<Book> list = objDao.selectAll();
			request.setAttribute("book_list", list);
		} catch (IllegalStateException e) {
			error= "DB接続エラーの為、一覧表示は行なえませんでした。";
			request.setAttribute("error", error);
			request.setAttribute("cmd","logout");
			request.getRequestDispatcher("/view/error.jsp").forward(request, response);
			// TODO: handle exception
		} finally {
			request.getRequestDispatcher("/view/list.jsp").forward(request, response);
		}
	}
}
