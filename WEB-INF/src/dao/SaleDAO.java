package dao;

import java.sql.*;
import java.util.ArrayList;

import bean.Sale;

public class SaleDAO {
	private static String RDB_DRIVE="com.mysql.jdbc.Driver";
	private static String URL="jdbc:mysql://localhost/mybookdb";
	private static String USER="root";
	private static String PASSWD="root123";

	public static Connection getConnection(){
		try{
			Class.forName(RDB_DRIVE);
			Connection con = DriverManager.getConnection(URL, USER, PASSWD);
			return con;
		}catch(Exception e){
			throw new IllegalStateException(e);
		}
	}

//	---------------------------selectBySales--------------------------------

	public ArrayList<Sale> selectBySales(String year , String month){

		Connection con = null;
		Statement smt = null;


		if(month.length() == 1) {
			month = "0" + month;
		}



	String sql = "SELECT b.isbn, title, price, sum(quantity) as quantity FROM orderinfo o inner join bookinfo b ON o.isbn=b.isbn "
				+" WHERE date LIKE '"+year+"-"+month+"%' GROUP BY b.isbn ORDER BY b.isbn";
	System.out.println(sql);

	ArrayList<Sale> sales = new ArrayList<Sale>();


	try {
		con = SaleDAO.getConnection();
		smt = con.createStatement();

		ResultSet rs = smt.executeQuery(sql);


		while(rs.next()) {
			Sale sale = new Sale();
			sale.setIsbn(rs.getString("isbn"));
			sale.setTitle(rs.getString("title"));
			sale.setPrice(rs.getInt("price"));
			sale.setQuantity(rs.getInt("quantity"));
			sales.add(sale);
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
	return sales;

}

//	---------------------------selectBySales--------------------------------


}
