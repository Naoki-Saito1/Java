package bean;

public class Sale {

	private String isbn;
	private String title;
	private int price;
	private int quantity;

	public Sale() {
		this.isbn = null;
		this.title = null;
		this.price = 0;
		this.quantity = 0;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getPrice() {
		return price;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getQuantity() {
		return quantity;
	}

}
