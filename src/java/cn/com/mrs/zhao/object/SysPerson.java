package cn.com.mrs.zhao.object;

import java.io.Serializable;
import java.util.Date;

public class SysPerson implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String personName;
	
	private Date expireTime; //第一次入住预计的到期时间
	
	private Date createTime; //第一次入住时间
	
	private String roomNo; //房间号
	
	private Double totalMount; //第一次入住收入的总金额
	
	private Double twoMonthMount; //第一次两个月月租
	
	private Double deposit;//押金
	
	private String isBack;//是否退租 0代表未退租 1代表已退租
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIsBack() {
		return isBack;
	}

	public void setIsBack(String isBack) {
		this.isBack = isBack;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public Date getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(Date expireTime) {
		this.expireTime = expireTime;
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

	public Double getTotalMount() {
		return totalMount;
	}

	public void setTotalMount(Double totalMount) {
		this.totalMount = totalMount;
	}

	public Double getTwoMonthMount() {
		return twoMonthMount;
	}

	public void setTwoMonthMount(Double twoMonthMount) {
		this.twoMonthMount = twoMonthMount;
	}

	public Double getDeposit() {
		return deposit;
	}

	public void setDeposit(Double deposit) {
		this.deposit = deposit;
	}
	
}
