package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import bean.User;



public class UserDAO {

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

	//--------------------------------selectByUser(1)----------------------------------
	//戻り値は1件・USER（DTO）またはNULLとなる
	public User selectByUser(String userid,String password) {
		Connection con = null;
		Statement  smt = null;
		User user = null;
		String sql = "SELECT * FROM userinfo WHERE user ='"+userid+"' AND password='"+password+"'";

		try {
			con = UserDAO.getConnection();
			smt = con.createStatement();

			ResultSet rs = smt.executeQuery(sql);
			System.out.println(sql);


			while(rs.next()) {
				user = new User();
				user.setUserid(rs.getString("user"));
				user.setPassword(rs.getString("password"));
				user.setEmail(rs.getString("email"));
				user.setAuthority(rs.getString("authority"));
			}
			//			getStringの中身はカラム名


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
		return user;


	}

	//	--------------------------------selectByUser(1)----------------------------------

	//	--------------------------------updateForPassword----------------------------------

	//	引数はストリング型でなくてもいけそう？
	public int updateForPassword(String userid,String password) {

		//		ちなみに下の文はここで定義しないと
		//		try 文の中で直接定義するとfinallyブロックがスコープ範囲外になる
		Connection con =null;
		Statement smt = null;

		int count = 0;
		String sql = "UPDATE userinfo SET password =  '"+password+"' WHERE user = '"+userid+"'";
		System.out.println(sql);

		try {
			con = UserDAO.getConnection();
			smt = con.createStatement();

			count = smt.executeUpdate(sql);


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

		return count;

	}
	//	--------------------------------updateForPassword----------------------------------
	//	--------------------------------updateForPassword----------------------------------

	public int insert(User user ) {

		Connection con = null;
		Statement smt = null;

		String sql = "INSERT INTO userinfo VALUES('"+user.getUserid() +"',"
				+ "'"+user.getPassword() +"',"
				+ "'"+user.getEmail()+"',"
				+ "'"+user.getAuthority()+"')";
		int count = 0;

		try {
			con = UserDAO.getConnection();
			smt = con.createStatement();

			count = smt.executeUpdate(sql);

		}catch(Exception e){
			System.out.println(e);
			throw new IllegalStateException(e);
		}finally{
			if(smt != null){
				try{smt.close();}catch(SQLException ignore){}
			}
			if(con != null){
				try{con.close();}catch(SQLException ignore){}
			}
		}

		return count;

	}
	//	--------------------------------updateForPassword----------------------------------
	//	--------------------------------selectAll----------------------------------

	public ArrayList<User> selectAll(){

		Connection con = null;
		Statement smt = null;

		String sql = "SELECT * FROM userinfo";
		ArrayList<User> users = new ArrayList<User>();


		try {
			con = UserDAO.getConnection();
			smt = con.createStatement();

			ResultSet rs = smt.executeQuery(sql);

			while(rs.next()) {
				User user = new User();
				user.setUserid(rs.getString("user"));
				user.setPassword(rs.getString("password"));
				user.setEmail(rs.getString("email"));
				user.setAuthority(rs.getString("authority"));
				users.add(user);
			}


		}catch(Exception e){
			System.out.println(e);
			throw new IllegalStateException(e);
		}finally{
			if(smt != null){
				try{smt.close();}catch(SQLException ignore){}
			}
			if(con != null){
				try{con.close();}catch(SQLException ignore){}
			}
		}

		return users;

	}
	//--------------------------------selectAll----------------------------------



	//--------------------------------selectUser----------------------------------
	public User selectByUser(String userid) {

		Connection con = null;
		Statement  smt = null;
		User user = new User();

		String sql = "SELECT * FROM userinfo WHERE user = '"+userid+"'";

		try {
			con = UserDAO.getConnection();
			smt = con.createStatement();

			ResultSet rs = smt.executeQuery(sql);

			//		戻り値1件でもwhileでポインタ下げる
			while(rs.next()) {
				user.setUserid(rs.getString("user"));
				user.setPassword(rs.getString("password"));
				user.setEmail(rs.getString("email"));
				user.setAuthority(rs.getString("authority"));
			}
			//		getStringの中身はカラム名

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
		return user;

	}

	//--------------------------------selectUser----------------------------------
	//メソッド名 ：update
	//引数 ：User user
	//戻り値 ：int count(更新件数)
	//SQL文例 ：sql = "UPDATE userinfo SET password='"+user.getPassword()+"',email='"+user.getEmail()
	//+"',authority='" +user.getAuthority()+"' WHERE user='"+user.getUserid()+"'";
	//呼び出し元 ：ユーザー更新機能(UpdateUserServlet)


	//--------------------------------update----------------------------------

	public int update(User user ) {

		Connection con = null;
		Statement smt = null;

		String sql = "UPDATE userinfo SET password='"+user.getPassword()+"',email='"+user.getEmail()
		+"',authority='" +user.getAuthority()+"' WHERE user='"+user.getUserid()+"'";
		int count = 0;

		try {
			con = UserDAO.getConnection();
			smt = con.createStatement();

			count = smt.executeUpdate(sql);

		}catch(Exception e){
			System.out.println(e);
			throw new IllegalStateException(e);
		}finally{
			if(smt != null){
				try{smt.close();}catch(SQLException ignore){}
			}
			if(con != null){
				try{con.close();}catch(SQLException ignore){}
			}
		}

		return count;

	}

	//--------------------------------update----------------------------------

	//--------------------------------delete----------------------------------

	public int delete(String userid) {
		Connection con = null;
		Statement  smt = null;

		String sql = "DELETE FROM userinfo WHERE user = '"+userid+"'";

		int count=0;
		try {
			con = UserDAO.getConnection();
			smt = con.createStatement();

			count = smt.executeUpdate(sql);

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
		return count;
	}

	//--------------------------------delete----------------------------------

	//--------------------------------Serchcc----------------------------------
	public ArrayList<User> serch(String userid) {

		Connection con = null;
		Statement smt = null;
		ArrayList<User> users = new ArrayList<User>();

		String sql = "SELECT * FROM userinfo WHERE user LIKE '%" + userid + "%'";

		try {
			con = UserDAO.getConnection();
			smt = con.createStatement();

			ResultSet rs = smt.executeQuery(sql);

			//		戻り値1件でもwhileでポインタ下げる
			while (rs.next()) {
				User user = new User();
				user.setUserid(rs.getString("user"));
				user.setPassword(rs.getString("password"));
				user.setEmail(rs.getString("email"));
				user.setAuthority(rs.getString("authority"));
				users.add(user);
			}
			//		getStringの中身はカラム名

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
		return users;
	}

}
