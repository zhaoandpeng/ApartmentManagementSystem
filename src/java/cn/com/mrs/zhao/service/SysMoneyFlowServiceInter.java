package cn.com.mrs.zhao.service;

import java.util.Map;

import cn.com.mrs.zhao.object.SysMoneyFlow;
import cn.com.mrs.zhao.object.SysPersonFlow;
import cn.com.mrs.zhao.utils.Page;

public interface SysMoneyFlowServiceInter {

	void insertSysMoneyFlow(SysMoneyFlow sysMoneyFlow);

	void delSysMoneyFlowFlow(Long id);

	Page<SysPersonFlow> selectListSysMoneyFlow(Integer pageNo, int pagenumber,Map<String, Object> map);

	SysMoneyFlow selectSimpleSysMoneyFlow(Long id);

}
