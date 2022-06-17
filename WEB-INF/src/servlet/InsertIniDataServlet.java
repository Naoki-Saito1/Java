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

public class InsertIniDataServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String error = "";
		BookDAO bookDao = new BookDAO();
		// これでファイルパスが取得できる
		String path = getServletContext().getRealPath("file\\initial_data.csv");

		try {
			// これはdbのbookinfoの情報
			ArrayList<Book> list = bookDao.selectAll();

			if (path.equals("")) {
				// ファイルの中身が空
				error = "初期データファイルが無い為、登録は行えません。";
			} else if (list.size() >= 1) {
				// ファイルあるけどdbにデータがある場合
				error = "DBにはデータが存在するので、初期データは登録できません。";
				request.setAttribute("error", error);
				request.setAttribute("cmd", "menu");
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);
				return;

			}

		} catch (IllegalStateException e) {
			error = "DB接続エラーの為、初期データ登録は行えません。";
			request.setAttribute("error", error);
			request.setAttribute("cmd", "logout");
			request.getRequestDispatcher("/view/error.jsp").forward(request, response);
			// TODO: handle exception
		}

		// ファイルの中身格納用ArrayList
		ArrayList<Book> book_list = new ArrayList<>();


		try {

			File file = new File(path);
			FileInputStream input = new FileInputStream(file);
			InputStreamReader stream = new InputStreamReader(input, "SJIS");
			BufferedReader buffer = new BufferedReader(stream);

			String line;

			while ((line = buffer.readLine()) != null) {

				byte[] b = line.getBytes();
				line = new String(b, "UTF-8");
				String[] books = line.split(",", -1);

				// ファイルの中身をBookオブジェクトに登録
				Book book = new Book();
				book.setIsbn(books[0]);
				book.setTitle(books[1]);
				book.setPrice(Integer.parseInt(books[2]));
				book_list.add(book);

			}

			input.close();
			stream.close();
			buffer.close();



			// 格納したファイルの中身をBookオブジェぶん繰り返しinsertする。
			for (int i = 0; i < book_list.size(); i++) {
				bookDao.insert(book_list.get(i));

				System.out.println(book_list.get(i).getIsbn());
			}

		} catch (UnsupportedEncodingException | FileNotFoundException e) {
			error = "初期データファイルが不備がある為、登録は行えません。";
			request.setAttribute("error", error);
			request.setAttribute("cmd", "menu");
			request.getRequestDispatcher("/view/error.jsp").forward(request, response);

		} catch (IOException e) {
			error = "初期データファイルが不備がある為、登録は行えません。";
			request.setAttribute("error", error);
			request.setAttribute("cmd", "menu");
			request.getRequestDispatcher("/view/error.jsp").forward(request, response);
		} finally {
			request.setAttribute("book_list", book_list);
			request.getRequestDispatcher("/view/insertIniData.jsp").forward(request, response);

		}
	}
}
