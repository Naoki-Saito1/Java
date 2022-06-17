//
//プログラム名	書籍管理システムWeb版 Ver2.0
//プログラムの説明	書籍を管理するシステムCRUD機能、検索機能、書籍購入機能、メール送信機能
//作成者	齋藤直希
//作成日	2022年5月30日

package servlet;



import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

import dao.BookDAO;

public class DeleteServlet extends HttpServlet {
	public void doGet(HttpServletRequest request ,HttpServletResponse response) throws ServletException ,IOException{
		String error = "";
		String cmd = "";

		try {
			request.setCharacterEncoding("UTF-8");

			BookDAO  bookDao = new BookDAO();

			String isbn = request.getParameter("isbn");

			if(bookDao.selectByIsbn(isbn).getIsbn() == null) {
				error="削除対象の書籍が存在しない為、書籍削除処理は行えませんでした。";
				cmd = "list";

				return;
			}

			bookDao.delete(isbn);


		} catch (IllegalStateException e) {
//
//			ここではフォワード処理はいらないなぜなら最終的にfinally でフォワードしてくれるため
//			ここではcmd と　errorにDBエラーのさいの文字列を入れておけばよい
			error="DB接続エラーの為、書籍削除処理は行えませんでした。";
			cmd = "logout";
			// TODO: handle exception
		}finally {

			if(error.equals("")) {
				request.getRequestDispatcher("/list").forward(request,response);
			}else {

				request.setAttribute("error", error);
				request.setAttribute("cmd", cmd);
				request.getRequestDispatcher("/view/error.jsp").forward(request,response);
			}


		}

	}
}
