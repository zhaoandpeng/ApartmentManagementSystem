package cn.com.mrs.zhao.dao;

import java.util.List;
import java.util.Map;

import cn.com.mrs.zhao.object.SysMoneyFlow;

public interface SysMoneyFlowMapper {

	void insertSysMoneyFlow(SysMoneyFlow sysMoneyFlow);

	void delSysMoneyFlowFlow(Long id);

	int selectCountSysMoneyFlow(Map<String, Object> map);

	@SuppressWarnings("rawtypes")
	List selectListSysMoneyFlow(Map<String, Object> map);

	SysMoneyFlow selectSimpleSysMoneyFlow(Long personId);

}
