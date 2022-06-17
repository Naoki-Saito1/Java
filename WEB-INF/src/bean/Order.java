package bean;

public class Order {
	private int orderno; //注文No
	private String userid; //ユーザID
	private String isbn; //ISBN
	private int quantity; //数量
	private String date; //購入日付


	//	コンストラクタ
	public Order() {
		this.orderno = 0;
		this.userid = null;
		this.isbn = null;
		this.quantity = 0;
		this.date = null;

	}

	public void setOrderno(int orderno) {
		this.orderno = orderno;
	}
	public int getOrderno() {
		return orderno;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUserid() {
		return userid;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getDate() {
		return date;
	}

}
