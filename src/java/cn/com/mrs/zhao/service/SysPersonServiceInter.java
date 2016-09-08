package cn.com.mrs.zhao.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import cn.com.mrs.zhao.object.SysPerson;
import cn.com.mrs.zhao.utils.Page;

@Service
public interface SysPersonServiceInter{

	int insertSysPerson(SysPerson sysPerson);

	List HandleSysPersonData();

	void updateSysPerson(SysPerson sysPerson);

	void delSysPerson(Long id);

	Page searchData(int pageNo, int pageNumber, Map<String, Object> map);

}
