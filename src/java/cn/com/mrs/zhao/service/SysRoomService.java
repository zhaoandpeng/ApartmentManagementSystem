package cn.com.mrs.zhao.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.mrs.zhao.dao.SysRoomMapper;
import cn.com.mrs.zhao.object.SysRoom;
import cn.com.mrs.zhao.utils.Page;
@Service
public class SysRoomService implements SysRoomServiceInter{

	@Autowired
	private SysRoomMapper sysRoomMapper;
	
	@Override
	public void addRoom(SysRoom sysRoom) {
		this.sysRoomMapper.addRoom(sysRoom);
	}

	@Override
	public Page<SysRoom> selectListSysRoom(Integer pageNo, int pageNumber,Map<String, Object> map) {
		Page page = new Page();
		int count = this.sysRoomMapper.selectCountSysRoom(map);
		page.setTotalNumber(count);
		page.setPageNo(pageNo);
		map.put("start", (pageNo-1)*pageNumber+1);
		map.put("stop", pageNo*pageNumber);
		page.setResultList(this.sysRoomMapper.selectListSysRoom(map));
		return page;
	}

	@Override
	public void updateSysRoom(SysRoom sysRoom) {
		this.sysRoomMapper.updateSysRoom(sysRoom);
	}

	
}
