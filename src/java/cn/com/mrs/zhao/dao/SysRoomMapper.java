package cn.com.mrs.zhao.dao;

import java.util.List;
import java.util.Map;

import cn.com.mrs.zhao.object.SysRoom;

public interface SysRoomMapper {

	void addRoom(SysRoom sysRoom);

	List selectListSysRoom(Map<String, Object> map);

	int selectCountSysRoom(Map<String, Object> map);

	void updateSysRoom(SysRoom sysRoom);

}
