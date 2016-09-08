package cn.com.mrs.zhao.service;

import java.util.Map;

import cn.com.mrs.zhao.object.SysRoom;
import cn.com.mrs.zhao.utils.Page;

public interface SysRoomServiceInter {

	void addRoom(SysRoom sysRoom);

	Page<SysRoom> selectListSysRoom(Integer pageNo, int pagenumber,Map<String, Object> map);

	void updateSysRoom(SysRoom sysRoom);

}
