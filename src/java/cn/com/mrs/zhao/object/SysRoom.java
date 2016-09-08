package cn.com.mrs.zhao.object;

import java.io.Serializable;

public class SysRoom implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String roomNo;
	
	private String roomType; // 3:2人间,0:4人间,1:6人间,2:8人间
	
	private Integer roomStatus;//房间状态 1：已住满 2：未住满
	
	private Integer roomRealNumber; //实际入住人数
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRoomNo() {
		return roomNo;
	}

	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public Integer getRoomStatus() {
		return roomStatus;
	}

	public void setRoomStatus(Integer roomStatus) {
		this.roomStatus = roomStatus;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getRoomRealNumber() {
		return roomRealNumber;
	}

	public void setRoomRealNumber(Integer roomRealNumber) {
		this.roomRealNumber = roomRealNumber;
	}
	
}
