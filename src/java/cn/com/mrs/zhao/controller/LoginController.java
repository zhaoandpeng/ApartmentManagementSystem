package cn.com.mrs.zhao.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.mrs.zhao.object.SysMoneyFlow;
import cn.com.mrs.zhao.object.SysPerson;
import cn.com.mrs.zhao.object.SysPersonFlow;
import cn.com.mrs.zhao.object.SysRoom;
import cn.com.mrs.zhao.service.LoginServiceInter;
import cn.com.mrs.zhao.service.SysMoneyFlowServiceInter;
import cn.com.mrs.zhao.service.SysPersonFlowServiceInter;
import cn.com.mrs.zhao.service.SysPersonServiceInter;
import cn.com.mrs.zhao.service.SysRoomServiceInter;
import cn.com.mrs.zhao.utils.DateUtils;
import cn.com.mrs.zhao.utils.Page;
@Controller
public class LoginController{
	
	@Autowired
	private LoginServiceInter loginServiceInter;
	
	@Autowired
	private SysPersonServiceInter sysPersonServiceInter;
	
	@Autowired
	private SysPersonFlowServiceInter sysPersonFlowServiceInter;
	
	@Autowired
	private SysMoneyFlowServiceInter  sysMoneyFlowServiceInter;
	
	@Autowired
	private SysRoomServiceInter  sysRoomServiceInter;
	
	
	public Map<String,Object> map;
	
	//登录
	@RequestMapping("getLoginResult")
	public String getLoginResult(Model model,String username,String password,HttpServletResponse response){
		Boolean index = this.loginServiceInter.getLoginResult(username,password);
		if(index){
			Cookie cookie = new Cookie("userName", username);
			try {
				URLEncoder.encode(username,"utf-8");
				cookie.setMaxAge(-1);
				response.addCookie(cookie);
				//获取数据
				Page<SysPerson> page = HandleSysPersonData(new HashMap<String,Object>(),1);
				model.addAttribute("listData", page.getResultList());
				model.addAttribute("TotalPage", page.getTotalPages());
				model.addAttribute("currentPage", page.getPageNo());
				model.addAttribute("username", username);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			return "main";
		}else{
			model.addAttribute("tip", "用户名或密码错误!");
			return "/index";
		}
	}
	
	//默认首页数据查询
	@RequestMapping("/getSysPersonData")
	public String getSysPersonData(Model model,HttpServletRequest request){
		map = new HashMap<String,Object>();
		String personName = request.getParameter("personName");
		String pageNoTemp = request.getParameter("pageNo");
		String roomNo = request.getParameter("roomNo");
		String expireTime = request.getParameter("expireTime");
		int pageNo = 1;
		if(!StringUtils.isEmpty(personName)){
			map.put("personName",personName);
		}
		if(!StringUtils.isEmpty(roomNo)){
			map.put("roomNo",roomNo);
		}
		if(!"".equals(pageNoTemp)&&null!=pageNoTemp){
			pageNo = Integer.parseInt(pageNoTemp);
		}
		if("thisTime".equals(expireTime)){
			try {
				map.put("expireTime", DateUtils.dateToDate(new Date()));
				model.addAttribute("flag", 0);
			} catch (ParseException e) {
				System.out.println("系统异常");
			}
		}
		Page<SysPerson> page = HandleSysPersonData(map,pageNo);
		model.addAttribute("listData", page.getResultList());
		model.addAttribute("TotalPage", page.getTotalPages());
		model.addAttribute("currentPage", page.getPageNo());
		return "main";
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Page<SysPerson> HandleSysPersonData(Map<String,Object> map,Integer pageNo) {
		Page page = this.sysPersonServiceInter.searchData(pageNo,Page.getPagenumber(),map);
		return page;
	}

	//添加学员
	@SuppressWarnings("unchecked")
	@RequestMapping("/insertSysPerson")
	@ResponseBody
	public int insertSysPerson(SysPerson sysPerson,Long id,HttpServletRequest request) {
		int index = 1;
		int pageNo =1;
		String startTime = request.getParameter("start_Time");
		String expireTime = request.getParameter("expire_Time");
		try {
			sysPerson.setCreateTime(DateUtils.stringToDate(startTime));
			sysPerson.setExpireTime(DateUtils.stringToDate(expireTime));
			sysPerson.setIsBack("0");
			if(!StringUtils.isEmpty(id)){
				
				//更新数据
				map = new HashMap<String,Object>();
				map.put("id", id);
				Page<SysPerson> page = this.sysPersonServiceInter.searchData(pageNo, Page.getPagenumber(), map);
				//房间号变更需要更新
				if(!page.getResultList().get(0).getRoomNo().equals(sysPerson.getRoomNo())){
					//更换后的新房间入住人数加一
		 			if(!StringUtils.isEmpty(sysPerson.getRoomNo())){
		 				map = new HashMap<String,Object>();
		 				map.put("roomNo", Integer.parseInt(sysPerson.getRoomNo()));
		 			}
		 			Page<SysRoom> pages = this.sysRoomServiceInter.selectListSysRoom(pageNo,Page.getPagenumber(),map);
		 			SysRoom sysRoom = new SysRoom();
		 			sysRoom.setRoomRealNumber(pages.getResultList().get(0).getRoomRealNumber()+1);
		 			sysRoom.setId(pages.getResultList().get(0).getId());
		 			this.sysRoomServiceInter.updateSysRoom(sysRoom);
		 			//老房间号人数减一
		 			if(!StringUtils.isEmpty(page.getResultList().get(0).getRoomNo())){
		 				map = new HashMap<String,Object>();
		 				map.put("roomNo", Integer.parseInt(page.getResultList().get(0).getRoomNo()));
		 			}
		 			Page<SysRoom> spage = this.sysRoomServiceInter.selectListSysRoom(pageNo,Page.getPagenumber(),map);
		 			SysRoom ssysRoom = new SysRoom();
		 			ssysRoom.setRoomRealNumber(spage.getResultList().get(0).getRoomRealNumber()-1);
		 			ssysRoom.setId(spage.getResultList().get(0).getId());
		 			this.sysRoomServiceInter.updateSysRoom(ssysRoom);
				}
				//删除最早记录的一条流水
	 			SysPersonFlow sysPersonFlow = this.sysPersonFlowServiceInter.selectSimplePersonFlow(id);
	 			this.sysPersonFlowServiceInter.delPayMentFlow(sysPersonFlow.getId());
	 			//增加一条新的流水
	 			SysPersonFlow personFlow = new SysPersonFlow();
	 			personFlow.setPersonId(id);
	 			personFlow.setCreateTime(DateUtils.stringToDate(startTime));
	 			personFlow.setPersonName(sysPerson.getPersonName());
	 			personFlow.setPersonRent(sysPerson.getTotalMount());
	 			personFlow.setRoomNo(sysPerson.getRoomNo());
	 			personFlow.setLeaveTime(DateUtils.stringToDate(expireTime));
				this.sysPersonFlowServiceInter.insertPayRent(personFlow);
				//删除最早的收支流水记录
				SysMoneyFlow sysMoneyFlow = this.sysMoneyFlowServiceInter.selectSimpleSysMoneyFlow(id);
				this.sysMoneyFlowServiceInter.delSysMoneyFlowFlow(sysMoneyFlow.getId());
				//插入修改数据后的收支流水
				SysMoneyFlow moneyFlow = new SysMoneyFlow();
				moneyFlow.setPersonId(id);
				moneyFlow.setType("1");
				moneyFlow.setCreateTime(DateUtils.stringToDate(startTime));
				moneyFlow.setAmount(sysPerson.getTotalMount());
				moneyFlow.setText("房租缴费");
	 			this.sysMoneyFlowServiceInter.insertSysMoneyFlow(moneyFlow);
				//TODO 更新之前需要先将变更之前的房间号查出来-1
				sysPerson.setId(id);
				this.sysPersonServiceInter.updateSysPerson(sysPerson);
				index=3;
			}else{
				this.sysPersonServiceInter.insertSysPerson(sysPerson);//添加成功后获取该用户的ID执行下面方法增加缴费流水信息
				SysPersonFlow sysPersonFlow = new SysPersonFlow();
				sysPersonFlow.setPersonId(sysPerson.getId());
				sysPersonFlow.setCreateTime(DateUtils.stringToDate(startTime));
				sysPersonFlow.setPersonName(sysPerson.getPersonName());
				sysPersonFlow.setPersonRent(sysPerson.getTotalMount());
				sysPersonFlow.setRoomNo(sysPerson.getRoomNo());
				sysPersonFlow.setLeaveTime(DateUtils.stringToDate(expireTime));
				this.sysPersonFlowServiceInter.insertPayRent(sysPersonFlow);
				//增加收支流水
				SysMoneyFlow sysMoneyFlow = new SysMoneyFlow();
				sysMoneyFlow.setPersonId(sysPerson.getId());
				sysMoneyFlow.setType("1");
	 			sysMoneyFlow.setCreateTime(DateUtils.stringToDate(startTime));
	 			sysMoneyFlow.setAmount(sysPerson.getTotalMount());
	 			sysMoneyFlow.setText("房租缴费");
	 			this.sysMoneyFlowServiceInter.insertSysMoneyFlow(sysMoneyFlow);
	 			//更新房间入住人数
	 			if(!StringUtils.isEmpty(sysPerson.getRoomNo())){
	 				map = new HashMap<String,Object>();
	 				map.put("roomNo", Integer.parseInt(sysPerson.getRoomNo()));
	 			}
	 			Page<SysRoom> page = this.sysRoomServiceInter.selectListSysRoom(pageNo,Page.getPagenumber(),map);
	 			SysRoom sysRoom = new SysRoom();
	 			sysRoom.setRoomRealNumber(page.getResultList().get(0).getRoomRealNumber()+1);
	 			sysRoom.setId(page.getResultList().get(0).getId());
	 			this.sysRoomServiceInter.updateSysRoom(sysRoom);
			}
		} catch (ParseException e1) {
			index=0;
		}
		return index;
	}
	
	//增加缴费流水
	@RequestMapping("/insertPayRent")
	@ResponseBody
	public int insertPayRent(SysPersonFlow sysPersonFlow,HttpServletRequest request){
		int index = 1;
		String leavetime = request.getParameter("leave_time");//房租到期
		String createtime = request.getParameter("create_time");//缴费日期
		try {
			sysPersonFlow.setCreateTime(DateUtils.stringToDate(createtime));
			sysPersonFlow.setLeaveTime(DateUtils.stringToDate(leavetime));
			this.sysPersonFlowServiceInter.insertPayRent(sysPersonFlow);
			SysPerson sysPerson = new SysPerson();
			sysPerson.setExpireTime(sysPersonFlow.getLeaveTime());
			sysPerson.setId(sysPersonFlow.getPersonId());
 			this.sysPersonServiceInter.updateSysPerson(sysPerson);//交完费的同时更新用户列表房租到期时间
 			//TODO缴费完成同时增加一条收支流水记录
 			SysMoneyFlow sysMoneyFlow = new SysMoneyFlow();
 			sysMoneyFlow.setPersonId(sysPersonFlow.getPersonId());
 			sysMoneyFlow.setType("1");
 			sysMoneyFlow.setCreateTime(DateUtils.stringToDate(createtime));
 			sysMoneyFlow.setAmount(sysPersonFlow.getPersonRent());
 			sysMoneyFlow.setText("房租缴费");
 			this.sysMoneyFlowServiceInter.insertSysMoneyFlow(sysMoneyFlow);
		} catch (ParseException e1) {
			index =3;
		}
		return index;
	}
	
	//退租请求处理
	@RequestMapping("/payBack")
	@ResponseBody
	public int payBack(HttpServletRequest request){
		int index = 1;
		try {
			//增加收支流水记录
			String textarea = request.getParameter("textarea");//退租备注
			String createtime = request.getParameter("create_time");//创建日期
			String roomNo = request.getParameter("roomNo");//退租房号
			String personName = request.getParameter("personName");//退租人名
			String personRent = request.getParameter("personRent");//退租金额
			String personId = request.getParameter("personId");//退租金额
			SysMoneyFlow sysMoneyFlow = new SysMoneyFlow();
			sysMoneyFlow.setAmount(Double.parseDouble(personRent));
			sysMoneyFlow.setType("0");
			sysMoneyFlow.setCreateTime(DateUtils.stringToDate(createtime));
			sysMoneyFlow.setText(roomNo+":"+personName+"退租,退租金额:"+personRent);
			this.sysMoneyFlowServiceInter.insertSysMoneyFlow(sysMoneyFlow);
			//更新房间可住人数
			if(!StringUtils.isEmpty(roomNo)){
 				map = new HashMap<String,Object>();
 				map.put("roomNo", Integer.parseInt(roomNo));
 			}
 			Page<SysRoom> page = this.sysRoomServiceInter.selectListSysRoom(1,Page.getPagenumber(),map);
 			SysRoom sysRoom = new SysRoom();
 			sysRoom.setRoomRealNumber(page.getResultList().get(0).getRoomRealNumber()-1);
 			sysRoom.setId(page.getResultList().get(0).getId());
 			this.sysRoomServiceInter.updateSysRoom(sysRoom);
 			//更新用户的退租状态
 			SysPerson sysPerson = new SysPerson();
 			sysPerson.setId(Long.parseLong(personId));
 			sysPerson.setIsBack("1");
 			this.sysPersonServiceInter.updateSysPerson(sysPerson);
		} catch (ParseException e) {
			index = 2;
		}
		return index;
	}
	
	//AJAX查询房间的可用状态
	@RequestMapping("/findRoomStatus")
	@ResponseBody
	public Map<String,Object> findRoomStatus(String roomNo){
		map = new HashMap<String,Object>();
		int pageNo = 1;
		int flag = 0;
		int count;
		map.put("roomNo", Integer.parseInt(roomNo));
		Page<SysRoom> page = this.sysRoomServiceInter.selectListSysRoom(pageNo, Page.getPagenumber(), map);
		if("0".equals(page.getResultList().get(0).getRoomType())){
			count = 4;
			if(count==page.getResultList().get(0).getRoomRealNumber()){
				map.put("message", "房间人数已满,请选择其它房间");
				flag=1;
			}
		}
		if("1".equals(page.getResultList().get(0).getRoomType())){
			count = 6;
			if(count==page.getResultList().get(0).getRoomRealNumber()){
				map.put("message", "房间人数已满,请选择其它房间");
				flag=1;
			}
		}
		if("2".equals(page.getResultList().get(0).getRoomType())){
			count = 8;
			if(count==page.getResultList().get(0).getRoomRealNumber()){
				map.put("message", "房间人数已满,请选择其它房间");
				flag=1;
			}
		}
		map.put("flag", flag);
		return map;
	}
	
	
	//支付流水页面跳转
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/skipBothEnds")
	public String skipBothEnds(String startTime,String endTime,Integer pageNo,Model model){
		map = new HashMap<String,Object>();
		try {
			if(!StringUtils.isEmpty(startTime)){
				Date starttime = DateUtils.stringToDate(startTime);
				map.put("startTime",starttime);
			}
			if(!StringUtils.isEmpty(endTime)){
				Date endtime = DateUtils.stringToDate(endTime);
				map.put("endTime",endtime);
			}
			if(pageNo==null){
				pageNo=1;
			}
			Page<SysPersonFlow> page = this.sysMoneyFlowServiceInter.selectListSysMoneyFlow(pageNo,Page.getPagenumber(),map);
			List list = page.getResultList();
			String money = HandleskipBothEnds(list);
			String totalInCome = money.substring(0,money.indexOf("&"));//总收入
			String totalCost = money.substring(money.indexOf("&")+1,money.length()); //总支出
			model.addAttribute("listMoneyFlow", page.getResultList());
			model.addAttribute("TotalPage", page.getTotalPages());
			model.addAttribute("currentPage", page.getPageNo());
			model.addAttribute("totalInCome", totalInCome);
			model.addAttribute("totalCost", totalCost);
		} catch (ParseException e) {
			System.out.println("系统异常");
		}
		return "third";
	}
	
	
	private String HandleskipBothEnds(List<SysMoneyFlow> list) {
		Double totalInCome = 0.00;//总收入
		Double totalCost = 0.00;//总支出
		for (SysMoneyFlow sysMoneyFlow : list) {
			if("1".equals(sysMoneyFlow.getType())){
				totalInCome = totalInCome + sysMoneyFlow.getAmount();
			}else{
				totalCost = totalCost + sysMoneyFlow.getAmount();
			}
		}
		return totalInCome+"&"+totalCost;
	}
	
	//AJAX获取到期学员
	@SuppressWarnings("rawtypes")
	@RequestMapping("/ajaxGetSysPersonData")
	@ResponseBody
	public Map<String,Object> ajaxGetSysPersonData(){
		map = new HashMap<String,Object>();
		int pageNo = 1;
		try {
			map.put("expireTime", DateUtils.dateToDate(new Date()));
			Page page = this.sysPersonServiceInter.searchData(pageNo,Page.getPagenumber(),map);
			map.put("count", page.getTotalNumber());
		} catch (ParseException e) {
			System.out.println("系统异常");
		}
		return map;
	}
	
	//跳入缴费页面
	@RequestMapping("/skipPayMent")
	public String skipPayMent(Model model,Integer pageNo,String personName) {
		map = new HashMap<String,Object>();
		//TODO查询缴费流水
		if(pageNo==null){
			pageNo=1;
		}
		if(!StringUtils.isEmpty(personName)){
			map.put("personName",personName);
		}
		Page<SysPersonFlow> page = this.sysPersonFlowServiceInter.selectListPersonFlow(pageNo,Page.getPagenumber(),map);
		model.addAttribute("listPersonFlow", page.getResultList());
		model.addAttribute("TotalPage", page.getTotalPages());
		model.addAttribute("currentPage", page.getPageNo());
		return "second";
	}
	
	

	@SuppressWarnings("unused")
	private int HandleUpdateSysPersonFlow(SysPersonFlow sysPersonFlow,
			String createtime, String leavetime) {
		int result = 2;
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		try {
			java.util.Date start=sdf.parse(createtime);
			java.util.Date end=sdf.parse(leavetime);
			sysPersonFlow.setLeaveTime(end);
			sysPersonFlow.setCreateTime(start);
		} catch (Exception e) {
			result = 0;
		}
		this.sysPersonFlowServiceInter.updateSysPersonFlow(sysPersonFlow);
		return result;
	}
	
	//删除缴费流水
	@RequestMapping("/delPayMentFlow")
	public String delPayMentFlow(Long id){
		this.sysPersonFlowServiceInter.delPayMentFlow(id);
		return "redirect:/skipPayMent.do";
	}
	
	//收支流水添加
	@RequestMapping("/addBothEnds")
	@ResponseBody
	public int addBothEnds(SysMoneyFlow sysMoneyFlow){
		int index = 1;
		sysMoneyFlow.setCreateTime(new Date());
		try {
			this.sysMoneyFlowServiceInter.insertSysMoneyFlow(sysMoneyFlow);
		} catch (Exception e) {
			index=0;
		}
		return index;
	}
		
	//删除收支流水
	@RequestMapping("/delAmountFlow")
	public String delSysMoneyFlowFlow(Long id){
		this.sysMoneyFlowServiceInter.delSysMoneyFlowFlow(id);
		return "redirect:/skipBothEnds.do";
	}
	
	//进入房间管理界面
	@RequestMapping("/skipRoomManager")
	public String skipRoomManager(Model model,Integer pageNo,HttpServletRequest request){
		String roomNo = request.getParameter("roomNo");
		map = new HashMap<String,Object>();
		//TODO查询缴费流水
		if(pageNo==null){
			pageNo=1;
		}
		if(!StringUtils.isEmpty(roomNo)){
			map.put("roomNo", roomNo);
		}
		Page<SysRoom> page = this.sysRoomServiceInter.selectListSysRoom(pageNo,Page.getPagenumber(),map);
		model.addAttribute("listSysRoom", page.getResultList());
		model.addAttribute("TotalPage", page.getTotalPages());
		model.addAttribute("currentPage", page.getPageNo());
		return "four";
	}
	
	//增加房间
	@RequestMapping("/addRoom")
	@ResponseBody
	public int addRoom(SysRoom sysRoom){
		int index = 1;
		sysRoom.setRoomStatus(2);
		sysRoom.setRoomRealNumber(0);
		try {
			this.sysRoomServiceInter.addRoom(sysRoom);
		} catch (Exception e) {
			index=0;
		}
		return index;
	}
	
	//删除学员信息
	@RequestMapping("/delSysPerson")
	public String delSysPerson(Long id){
		this.sysPersonServiceInter.delSysPerson(id);
		return "redirect:/getSysPersonData.do";
	}
	
	
	
	
	
	
	public static void main(String[] args) {
		getNowDateShort();
	}
	public static Date getNowDateShort() {  
	    Date currentTime = new Date();  
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
	    String dateString = formatter.format(currentTime);  
	    ParsePosition pos = new ParsePosition(8);  
	    Date currentTime_2 = formatter.parse(dateString, pos);  
	    System.out.println(currentTime_2);
	    return currentTime_2;  
	} 
}
