package cn.com.mrs.zhao.dao;

import cn.com.mrs.zhao.object.SysUser;

public interface SysUserMapper{

	SysUser getLoginResult(String username, String password);
	
}
