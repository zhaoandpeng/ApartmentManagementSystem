package cn.com.mrs.zhao.object;

import java.io.Serializable;

public class SysUser implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String userName;
	
	private String password;

	public SysUser(Long i, String string, String string2) {
		this.id=i;
		this.userName = string;
		this.password = string2;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
