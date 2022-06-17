package bean;

public class User {

	private String userid; //ユーザー名
	private String password; //パスワード
	private String email; //メールアドレス
	private String authority;//権限（1：一般ユーザー、2：管理者）

	public User(){
		this.userid = null;
		this.password = null;
		this.email = null;
		this.authority = null;
	}


	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUserid() {
		return userid;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public String getAuthority() {
		return authority;
	}


}
