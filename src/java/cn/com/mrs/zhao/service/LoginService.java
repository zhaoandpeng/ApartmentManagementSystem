package cn.com.mrs.zhao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.mrs.zhao.dao.SysUserMapper;
import cn.com.mrs.zhao.object.SysUser;

@Service
public class LoginService implements LoginServiceInter{

	@Autowired
	private SysUserMapper sysUserMapper;
	
	public Boolean getLoginResult(String username, String password) {
		Boolean index = false;
		SysUser user = this.sysUserMapper.getLoginResult(username,password);
		if(null!=user&&user.getPassword().equals(password)&&user.getUserName().equals(username)){
			index = true;
		}else{
			index = false;
		}
		return index;
	}
}
