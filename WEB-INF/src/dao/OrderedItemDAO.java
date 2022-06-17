package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import bean.Order;
import bean.OrderedItem;

public class OrderedItemDAO {
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

	//	③DBのorderinfoとbookinfoテーブルを結合して購入情報を取得するメソッド定義

	public ArrayList<OrderedItem> selectAll() {
		//		戻り値が1カラムではない。ジョインした結果が戻り値として戻るときは?

		ArrayList<OrderedItem> orderedList = new ArrayList<OrderedItem>();

		String sql = "SELECT o.user, b.title ,o.quantity, o.date FROM bookinfo b, orderinfo o WHERE b.isbn = o.isbn";

		System.out.println(sql);
		Connection con = null;
		Statement smt = null;

		try {
			con = OrderedItemDAO.getConnection();
			smt = con.createStatement();

			ResultSet rs = smt.executeQuery(sql);

			//
			while (rs.next()) {
				OrderedItem item = new OrderedItem();
				item.setUserid(rs.getString("user"));
				item.setQuantity(rs.getInt("quantity"));
				item.setTitle(rs.getString("title"));
				item.setDate(rs.getString("date"));
				orderedList.add(item);

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
		return orderedList;

	}

	//	---------------------------selectByUser---------------------------------

	public HashMap<String, Object> selectByUser(String userid) {

		Connection con = null;
		Statement smt = null;

		String sql = "SELECT o.user , b.title , o.quantity, o.date FROM bookinfo b, orderinfo o"
				+ " WHERE b.isbn = o.isbn and o.user = '" + userid + "'";

		//		ArrayList<OrderedItem> list = new ArrayList<OrderedItem>();
		//		ArrayList<Order> order_list = new ArrayList<Order>();

		HashMap<String, Object> map = new HashMap<>();
		int index = 0;

		try {
			con = OrderedItemDAO.getConnection();
			smt = con.createStatement();

			ResultSet rs = smt.executeQuery(sql);

			while (rs.next()) {
				OrderedItem item = new OrderedItem();
				Order order = new Order();
				item.setUserid(rs.getString("user"));
				item.setTitle(rs.getString("title"));
				order.setQuantity(rs.getInt("quantity"));
				item.setDate(rs.getString("date"));
				map.put("item" + index, item);
				map.put("order" + index, order);
				index++;

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
		return map;

	}
	//	---------------------------selectByUser---------------------------------

}
