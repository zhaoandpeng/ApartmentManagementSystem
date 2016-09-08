package cn.com.mrs.zhao.object;

import java.util.Date;

public class SysMoneyFlow {

	private Long id;
	
	private Long personId; //续租人ID
	
	private String type; //收支类型0代表支出1代表收入
	
	private Double amount;//金额
	
	private String text; //备注
	
	private Date createTime; //创建时间

	
	public Long getPersonId() {
		return personId;
	}

	public void setPersonId(Long personId) {
		this.personId = personId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
}
