package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import bean.Order;

public class OrderDAO {

	private static String RDB_DRIVE = "com.mysql.jdbc.Driver";
	private static String URL = "jdbc:mysql://localhost/mybookdb";
	private static String USER = "root";
	private static String PASSWD = "root123";

	public static Connection getConnection() {
		try {
			Class.forName(RDB_DRIVE);
			Connection con = DriverManager.getConnection(URL, USER, PASSWD);
			return con;
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}
//	----------------------------------insert------------------------------------
//購入書籍の登録メソッド
//引数Order order
	public void insert(Order order) {

		Connection con = null;
		Statement smt = null;
		//		カラムのordernoはオートインクリメント属性のため、「NULL」を設定することで自動で番号が割り振られる。
		//		CURDATE()でcreated_at
		String sql = "INSERT INTO orderinfo VALUES(NULL,'" + order.getUserid() + "','" + order.getIsbn() + "',"
				+ order.getQuantity() + ",CURDATE())";

		try {
			con = OrderDAO.getConnection();
			smt = con.createStatement();

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
}
