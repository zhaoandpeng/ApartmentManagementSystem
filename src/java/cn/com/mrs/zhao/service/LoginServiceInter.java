package cn.com.mrs.zhao.service;

import org.springframework.stereotype.Service;

@Service
public interface LoginServiceInter{

	public Boolean getLoginResult(String username, String password);
}
