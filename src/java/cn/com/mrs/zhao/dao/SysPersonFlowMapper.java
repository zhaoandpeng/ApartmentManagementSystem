package cn.com.mrs.zhao.dao;

import java.util.List;
import java.util.Map;

import cn.com.mrs.zhao.object.SysPersonFlow;

public interface SysPersonFlowMapper {

	void insertPayRent(SysPersonFlow sysPersonFlow);

	void updateSysPersonFlow(SysPersonFlow sysPersonFlow);

	void delPayMentFlow(Long id);

	@SuppressWarnings("rawtypes")
	List selectListSysPersonFlow(Map<String, Object> map);

	int selectCountSysPersonFlow(Map<String, Object> map);

	SysPersonFlow selectSimplePersonFlow(Long personId);

}
