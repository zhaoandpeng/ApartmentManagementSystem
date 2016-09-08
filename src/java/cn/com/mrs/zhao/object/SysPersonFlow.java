package cn.com.mrs.zhao.object;

import java.io.Serializable;
import java.util.Date;

public class SysPersonFlow implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private Long personId; //续租人ID
	
	private String  personName; //续租人
	
	private Double personRent; //续租金额
	
	private Date createTime; //续租时间
	
	private String roomNo;	//房间号码
	
	private Date leaveTime;  //预计房租到期日

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPersonId() {
		return personId;
	}

	public void setPersonId(Long personId) {
		this.personId = personId;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public Double getPersonRent() {
		return personRent;
	}

	public void setPersonRent(Double personRent) {
		this.personRent = personRent;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getRoomNo() {
		return roomNo;
	}

	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}

	public Date getLeaveTime() {
		return leaveTime;
	}

	public void setLeaveTime(Date leaveTime) {
		this.leaveTime = leaveTime;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
