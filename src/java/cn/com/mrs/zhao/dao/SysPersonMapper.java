package cn.com.mrs.zhao.dao;

import java.util.List;
import java.util.Map;

import cn.com.mrs.zhao.object.SysPerson;

public interface SysPersonMapper {

	int insertSysPerson(SysPerson sysPerson);

	List<SysPerson> HandleSysPersonData();

	SysPerson selectSysPerson(String personName);

	void updateSysPerson(SysPerson sysPerson);

	void delSysPerson(Long id);

	int selectCountSysPerson(Map<String, Object> map);

	List selectListSysPerson(Map<String, Object> map);

}
