package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import bean.Book;

public class BookDAO{

	private static String RDB_DRIVE="com.mysql.jdbc.Driver";
	private static String URL="jdbc:mysql://localhost/mybookdb";
	private static String USER="root";
	private static String PASSWD="root123";

	public static Connection getConnection()
	{
		try{
			Class.forName(RDB_DRIVE);
			Connection con = DriverManager.getConnection(URL, USER, PASSWD);
			return con;
		}catch(Exception e){
			throw new IllegalStateException(e);
		}
	}

	public ArrayList<Book> selectAll()	{

		ArrayList<Book> list = new ArrayList<Book>();
		String sql = "SELECT * FROM bookinfo ORDER BY ISBN";

		Connection con = null;
		Statement  smt = null;
		try{
			con = BookDAO.getConnection();
			smt = con.createStatement();

			ResultSet rs = smt.executeQuery(sql);

			while(rs.next()){
				Book books = new Book();
				books.setIsbn(rs.getString("isbn"));
				books.setTitle(rs.getString("title"));
				books.setPrice(rs.getInt("price"));

				list.add(books);
			}

		}catch(Exception e){
			throw new IllegalStateException(e);
		}finally{
			if(smt != null){
				try{smt.close();}catch(SQLException ignore){}
			}
			if(con != null){
				try{con.close();}catch(SQLException ignore){}
			}
		}
		return list;

	}


//	----------------------------------insert------------------------------------
	/**
	 * 引数で与えられた書籍情報を、書籍データを格納するbookinfoテーブルへ登録するメソッド
	 *
	 * @param _book
	 *            登録する書籍情報の{@link Book}オブジェクト
	 * @throws IllegalStateException
	 *             メソッド内部で例外が発生した場合
	 */
	public void insert(Book book) {
		Connection con = null;
		Statement smt = null;
		try {
			con = getConnection();
			smt = con.createStatement();
			String sql = "INSERT INTO bookinfo(isbn,title,price) VALUES('"
					+ book.getIsbn() + "','" + book.getTitle() + "',"
					+ book.getPrice() + ")";
			smt.executeUpdate(sql);

		} catch (Exception e) {
			throw new IllegalStateException(e);
		} finally {
			if (smt != null) {
				try {
					smt.close();
				} catch (SQLException ignore) {
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ignore) {
				}
			}
		}
	}

//	----------------------------------insert------------------------------------



//	----------------------------------detail------------------------------------
	/**
	 * 引数で与えられた書籍情報を、書籍データを格納するbookinfoテーブルへ登録するメソッド
	 *
	 * @param _book
	 *            登録する書籍情報の{@link Book}オブジェクト
	 * @throws IllegalStateException
	 *             メソッド内部で例外が発生した場合
	 */
	public Book selectByIsbn(String isbn) {
		Book book = new Book();
		Connection con = null;
		Statement smt = null;
		try {
			con = getConnection();
			smt = con.createStatement();
			String sql = "SELECT * FROM bookinfo WHERE isbn='" + isbn + "'";
			ResultSet rs = smt.executeQuery(sql);

			if (rs.next()) {
				book.setIsbn(rs.getString("isbn"));
				book.setTitle(rs.getString("title"));
				book.setPrice(rs.getInt("price"));
			}
		} catch (Exception e) {
			throw new IllegalStateException(e);
		} finally {
			if (smt != null) {
				try {
					smt.close();
				} catch (SQLException ignore) {
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ignore) {
				}
			}
		}
		return book;
	}

//	----------------------------------detail------------------------------------

//	----------------------------------update------------------------------------

	/**
	 * 書籍情報を格納するbookinfoテーブルに存在する、引数で与えられたISBNを持つ書籍情報を、 引数で与えられた書籍情報に変更をおこなうメソッド
	 *
	 * @param _book
	 *            更新する書籍情報の{@link Book}オブジェクト
	 * @throws IllegalStateException
	 *             メソッド内部で例外が発生した場合
	 */
	public void update(Book book) {
		Connection con = null;
		Statement smt = null;
		try {
			con = getConnection();
			smt = con.createStatement();
			String sql = "UPDATE bookinfo SET" + " title ='" + book.getTitle()
					+ "'," + " price =" + book.getPrice() + " WHERE isbn ='"
					+ book.getIsbn() + "'";
			smt.executeUpdate(sql);

		} catch (Exception e) {
			throw new IllegalStateException(e);
		} finally {
			if (smt != null) {
				try {
					smt.close();
				} catch (SQLException ignore) {
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ignore) {
				}
			}
		}
	}


//	----------------------------------update------------------------------------

//	----------------------------------delete------------------------------------

	public void delete(String isbn){

		  Connection con = null;
		  Statement smt = null;

		  try{
			  con = getConnection();
			  smt = con.createStatement();

			  String sql = "DELETE FROM bookinfo WHERE isbn = '" + isbn +"'";

			  smt.executeUpdate(sql);





		  }catch(Exception e){
		     throw new IllegalStateException(e);
		  }finally{
		     if( smt != null ){
		        try{smt.close();}catch(SQLException ignore){}
		    }
		     if( con != null ){
		        try{con.close();}catch(SQLException ignore){}
		    }
		  }
		}

//	----------------------------------delete------------------------------------
//	----------------------------------search------------------------------------

	public ArrayList <Book> search(String isbn,String title, String price){

		  Connection con = null;
		  Statement smt = null;

		  ArrayList<Book> bookList = new ArrayList<Book>();
		  try{

			  con = getConnection();
			  smt = con.createStatement();

			  String sql = "SELECT * FROM bookinfo WHERE isbn LIKE '%" + isbn
						+ "%' AND title LIKE '%" + title + "%' AND price LIKE '%"
						+ price + "%'";
			  System.out.println(sql);

			  ResultSet rs = smt.executeQuery(sql);

			  while(rs.next()) {
				  Book book = new Book();
				  book.setIsbn(rs.getString("isbn"));
				  book.setTitle(rs.getString("title"));
				  book.setPrice(rs.getInt("price"));
				  bookList.add(book);
			  }

		  }catch(Exception e){
		    throw new IllegalStateException(e);
		  }finally{
		    if( smt != null ){
		      try{smt.close();}catch(SQLException ignore){}
		    }
		    if( con != null ){
		      try{con.close();}catch(SQLException ignore){}
		    }
		  }
		  return bookList;
		}

//	----------------------------------search------------------------------------

}
