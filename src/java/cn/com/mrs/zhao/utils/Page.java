package cn.com.mrs.zhao.utils;

import java.util.List;
import java.util.Map;

public class Page <E>{

	final static int pageNumber = 9;
	
	private List<E> resultList; //结果集
	
	private int totalNumber; //总记录数
	
	private int pageNo;		 //第几页
	
	private Map<String,Object> map;
	
	//获取总页数
	public int getTotalPages(){
		
		return (totalNumber + pageNumber - 1) / pageNumber;
	
	}
	
	//获取首页
	public int getFirstPageNo() {  
        
		return 1;  

	}
	
	//上一页
	public int getPrePageNo(){
		
		if(pageNo<=1){
			return 1;
		}
		return pageNo-1;
	}
	
	//下一页
	public int getNextPageNo(){
		
		if(pageNo>=getLastPageNo()){
			
			return getLastPageNo();
			
		}
		return pageNo+1;
	}
	
	//取得尾页
	public int getLastPageNo() {  
         
		return getTotalPages();  
    
	}

	public List<E> getResultList() {
		return resultList;
	}

	public void setResultList(List<E> resultList) {
		this.resultList = resultList;
	}

	public int getTotalNumber() {
		return totalNumber;
	}

	public void setTotalNumber(int totalNumber) {
		this.totalNumber = totalNumber;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

	public static int getPagenumber() {
		return pageNumber;
	}  
}
