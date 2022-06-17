//
//プログラム名	書籍管理システムWeb版 Ver2.0
//プログラムの説明	書籍を管理するシステムCRUD機能、検索機能、書籍購入機能、メール送信機能
//作成者	齋藤直希
//作成日	2022年5月30日

package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Book;
import dao.BookDAO;

/**
 * 書籍管理システムにおける書籍更新機能に関する処理をおこなうサーブレットクラス
 *
 * @author KandaITSchool
 *
 */
public class UpdateServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String error = "";
		String cmd = "";

		try {
			// 入力データの文字コードの指定
			request.setCharacterEncoding("UTF-8");

			// � isbn,title,price等の入力パラメータを取得する
			String isbn = request.getParameter("isbn");
			String title = request.getParameter("title");
			String strPrice = request.getParameter("price");

			// � 取得したパラメータの各エラーチェックをおこなう
			// 全データの空白チェック（データが入力されているかどうか）
			if (title.equals("")) {
				error = "タイトルが未入力の為、書籍更新処理は行えませんでした。";
				cmd = "list";
				return;
			}
			if (strPrice.equals("")) {
				error = "価格が未入力の為、書籍更新処理は行えませんでした。";
				cmd = "list";
				return;
			}

			int price;
			try {
				// 価格値チェック（整数かどうか）
				price = Integer.parseInt(strPrice);
			} catch (NumberFormatException e) {
				error = "価格の値が不正の為、書籍更新処理は行えませんでした。";
				cmd = "list";
				return;
			}

			// BookDAOオブジェクト生成
			BookDAO objDao = new BookDAO();

			// 入力されたISBNの存在チェック
			if (objDao.selectByIsbn(isbn).getIsbn() == null) {
				error = "更新対象が存在しない為、書籍更新処理は行えませんでした。";
				cmd = "list";
				return;
			}

			// � Bookのオブジェクトを生成し、各setterメソッドを利用し、isbn,title,priceを設定する。
			Book book = new Book();
			book.setIsbn(isbn);
			book.setTitle(title);
			book.setPrice(price);

			// � �で設定したBookのオブジェクトを引数として、更新メソッドを呼び出す。
			objDao.update(book);

		} catch (IllegalStateException e) {
			error = "DB接続エラーの為、書籍更新処理は行えませんでした。";
			cmd = "logout";
		} finally {
			// �エラーの有無でフォワード先を呼び分ける
			if (error.equals("")) {
				// エラーが無い場合はListServletにフォワード
				request.getRequestDispatcher("/list")
						.forward(request, response);
			} else {
				// エラーが有る場合はerror.jspにフォワードする
				request.setAttribute("error", error);
				request.setAttribute("cmd", cmd);
				request.getRequestDispatcher("/view/error.jsp").forward(
						request, response);
			}
		}
	}
}
