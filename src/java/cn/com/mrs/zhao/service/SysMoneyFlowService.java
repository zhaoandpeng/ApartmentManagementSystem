package cn.com.mrs.zhao.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.mrs.zhao.dao.SysMoneyFlowMapper;
import cn.com.mrs.zhao.object.SysMoneyFlow;
import cn.com.mrs.zhao.object.SysPersonFlow;
import cn.com.mrs.zhao.utils.Page;

@Service
public class SysMoneyFlowService implements SysMoneyFlowServiceInter{

	@Autowired
	private SysMoneyFlowMapper sysMoneyFlowMapper;
	
	@Override
	public void insertSysMoneyFlow(SysMoneyFlow sysMoneyFlow) {
		this.sysMoneyFlowMapper.insertSysMoneyFlow(sysMoneyFlow);
	}

	@Override
	public void delSysMoneyFlowFlow(Long id) {
		this.sysMoneyFlowMapper.delSysMoneyFlowFlow(id);
	}

	@Override
	public Page<SysPersonFlow> selectListSysMoneyFlow(Integer pageNo,int pageNumber, Map<String, Object> map) {
		Page page = new Page();
		int count = this.sysMoneyFlowMapper.selectCountSysMoneyFlow(map);
		page.setTotalNumber(count);
		page.setPageNo(pageNo);
		map.put("start", (pageNo-1)*pageNumber+1);
		map.put("stop", pageNo*pageNumber);
		page.setResultList(this.sysMoneyFlowMapper.selectListSysMoneyFlow(map));
		return page;
	}

	@Override
	public SysMoneyFlow selectSimpleSysMoneyFlow(Long personId) {
		return this.sysMoneyFlowMapper.selectSimpleSysMoneyFlow(personId);
	}

}
