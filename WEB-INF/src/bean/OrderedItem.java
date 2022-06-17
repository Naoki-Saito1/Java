package bean;

public class OrderedItem {
	private String userid; //ユーザID
	private String title; //書籍のタイトル
	private String date;//購入日付
	private int quantity;

	public OrderedItem() {
		this.userid = null;
		this.title = null;
		this.quantity = 0;
		this.date = null;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getQuantity() {
		return quantity;
	}


}
