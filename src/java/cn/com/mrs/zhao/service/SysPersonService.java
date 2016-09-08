package cn.com.mrs.zhao.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.mrs.zhao.dao.SysPersonMapper;
import cn.com.mrs.zhao.object.SysPerson;
import cn.com.mrs.zhao.utils.Page;
@Service
public class SysPersonService implements SysPersonServiceInter{

	@Autowired
	private SysPersonMapper personMapper;
	
	public int insertSysPerson(SysPerson sysPerson) {
		int index = 1;
		this.personMapper.insertSysPerson(sysPerson);
		return index;
	}

	@SuppressWarnings("rawtypes")
	public List HandleSysPersonData() {
		List<SysPerson> list = this.personMapper.HandleSysPersonData();
		return list;
	}

	@Override
	public void updateSysPerson(SysPerson sysPerson) {
		this.personMapper.updateSysPerson(sysPerson);
	}

	@Override
	public void delSysPerson(Long id) {
		this.personMapper.delSysPerson(id);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Page searchData(int pageNo, int pageNumber, Map<String, Object> map) {
		Page page = new Page();
		int count = this.personMapper.selectCountSysPerson(map);
		page.setTotalNumber(count);
		page.setPageNo(pageNo);
		map.put("start", (pageNo-1)*pageNumber+1);
		map.put("stop", pageNo*pageNumber);
		page.setResultList(this.personMapper.selectListSysPerson(map));
		return page;
	}
}
