package cn.com.mrs.zhao.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.mrs.zhao.dao.SysPersonFlowMapper;
import cn.com.mrs.zhao.object.SysPersonFlow;
import cn.com.mrs.zhao.utils.Page;
@Service
public class SysPersonFlowService implements SysPersonFlowServiceInter{

	@Autowired
	private SysPersonFlowMapper sysPersonFlowMapper;
	
	
	@Override
	public void insertPayRent(SysPersonFlow sysPersonFlow) {
		this.sysPersonFlowMapper.insertPayRent(sysPersonFlow);
	}


	@Override
	public void updateSysPersonFlow(SysPersonFlow sysPersonFlow) {
		this.sysPersonFlowMapper.updateSysPersonFlow(sysPersonFlow);
	}


	@Override
	public void delPayMentFlow(Long id) {
		this.sysPersonFlowMapper.delPayMentFlow(id);
	}


	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Page<SysPersonFlow> selectListPersonFlow(Integer pageNo,int pageNumber, Map<String, Object> map) {
		Page page = new Page();
		int count = this.sysPersonFlowMapper.selectCountSysPersonFlow(map);
		page.setTotalNumber(count);
		page.setPageNo(pageNo);
		map.put("start", (pageNo-1)*pageNumber+1);
		map.put("stop", pageNo*pageNumber);
		page.setResultList(this.sysPersonFlowMapper.selectListSysPersonFlow(map));
		return page;
	}


	@Override
	public SysPersonFlow selectSimplePersonFlow(Long personId) {
		SysPersonFlow sysPersonFlow = this.sysPersonFlowMapper.selectSimplePersonFlow(personId);
		return sysPersonFlow;
	}

	
}
