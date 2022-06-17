package util;

import java.util.ArrayList;

import bean.Book;
import bean.User;

//メールの本文作成用クラス
//引数
//購入商品bookList
//合計金額
//ログインユーザー
public class MailFormat {
	public  String  mailFormat( ArrayList<Book> books, int price, User user) {

		//		合計金額をStringに変換してる
		String money = String.valueOf(price);
		//		本文いれる変数
		String mozi = "";

		//		購入商品をループして取得し、つなげて変数に代入してる
		for(int i = 0; i < books.size() ; i ++) {
			mozi += books.get(i).getIsbn()
					+ "" +books.get(i).getTitle()
					+ "" +String.valueOf(books.get(i).getPrice())+ "円" + "\r\n";

		}

		//本文生成ほかのやり方はないのでしょうか？（長文になるといこのやり方だと大変）
		mozi = user.getUserid() + "様" + "\r\n\r\n" +"本のご購入ありがとうざいます。\r\n" +
				"以下内容でご注文を受け付けましたので、ご連絡致しま\r\n" +
				"す。" + "\r\n"+ mozi + "\r\n"+ "合計" +money + "円";
		//本文返却
		return mozi;

	}

}

