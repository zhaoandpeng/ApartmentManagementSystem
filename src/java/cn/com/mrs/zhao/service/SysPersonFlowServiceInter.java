package cn.com.mrs.zhao.service;

import java.util.Map;

import cn.com.mrs.zhao.object.SysPersonFlow;
import cn.com.mrs.zhao.utils.Page;

public interface SysPersonFlowServiceInter {

	void insertPayRent(SysPersonFlow sysPersonFlow);

	void updateSysPersonFlow(SysPersonFlow sysPersonFlow);

	void delPayMentFlow(Long id);

	Page<SysPersonFlow> selectListPersonFlow(Integer pageNo, int pagenumber,Map<String, Object> map);

	SysPersonFlow selectSimplePersonFlow(Long personId);

}
