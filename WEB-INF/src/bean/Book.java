package bean;

public class Book {

	private String isbn;
	private String title;
	private int	price;

	public Book(){
		isbn  = null;
		title = null;
		price = 0;
	}

	public void setIsbn(String isbn){
		this.isbn = isbn;
	}
	public void setTitle(String title){
		this.title = title;
	}
	public void setPrice(int price){
		this.price = price;
	}

	public String getIsbn(){
		return this.isbn;
	}
	public String getTitle(){
		return this.title;
	}
	public int getPrice(){
		return this.price;
	}

}
