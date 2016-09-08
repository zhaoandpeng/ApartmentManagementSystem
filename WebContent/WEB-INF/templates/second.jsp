<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@include file="include/header.jsp"%>
    <div class="container-fluid content">
        <div class="row-fluid">
            <%@include file="include/left.jsp"%>
            <div class="span10 content-right">
                <div class="container-fluid">
			        <div class="row-fluid">
			            <h4>数据列表</h4>
			            <div class="add">
			            	<a class="btn btn-success" onclick="openadd();">新增</a>
			            	<a class="btn btn-success" onclick="find();">查询</a><input type="text" id="text" placeholder="学员姓名" style="height:20px;position:absolute">
			            </div>
			            <div class="w">
			                <div class="span12">
			                    <table class="table table-condensed table-bordered table-hover tab">
			                        <thead>
			                            <tr>
			                            	<th style="display: none"></th>
			                                <th>姓名</th>
			                                <th>续租金额</th>
			                                <th>续租房号</th>
			                                <th>缴费日期</th>
			                                <th>房租到期</th>
			                                <th>操作</th>
			                            </tr>
			                        </thead>
			                        <tbody id="tbody">
			                        <c:forEach items="${listPersonFlow}" var="p">
			                        	<tr>
			                        		<td style="display: none">${p.id}</td>
			                                <td>${p.personName}</td>
			                                <td>${p.personRent}元</td>
			                                <td>${p.roomNo}室</td>
			                                <td><fmt:formatDate value="${p.createTime}" pattern="yyyy-MM-dd"/> </td>
			                                <td><fmt:formatDate value="${p.leaveTime}" pattern="yyyy-MM-dd" /> </td>
			                                <td><%-- <button onclick="edit('${p.id}','${p.personName}','${p.personRent}','${p.roomNo}','${p.createTime}','${p.leaveTime}')
			                                "> 编辑</button> --%>&nbsp;&nbsp;
			                                <button onclick="del('${p.id}')
			                                "> 删除</button>
			                                </td>
			                            </tr>
			                        </c:forEach>
			                        </tbody>
			                    </table>
			                    <div id="page" class="tr"></div>
			                </div>
			                <div>
			                	<span class="pageButton">
			                	<a style="margin-left: 420px;border-width: 1;padding: 2 4 0 4;background-color: #ece9d8;height: 23;font-size: 16px;text-decoration:none" href="/skipPayMent.do?pageNo=1">首页</a>&nbsp;
			                	<a style="border-width: 1;padding: 2 4 0 4;background-color: #ece9d8;height: 23;font-size: 16px;text-decoration:none" href="/skipPayMent.do?pageNo=${currentPage-1}">上一页</a>&nbsp;
			                	<a style="border-width: 1;padding: 2 4 0 4;background-color: #ece9d8;height: 23;font-size: 16px;text-decoration:none" href="/skipPayMent.do?pageNo=${currentPage+1}">下一页</a>&nbsp;
			                	<a style="border-width: 1;padding: 2 4 0 4;background-color: #ece9d8;height: 23;font-size: 16px;text-decoration:none" href="/skipPayMent.do?pageNo=${TotalPage}">末页</a>
			                	</span>
                			</div>
			            </div>
			
			
			            <div id="addModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			                <div class="modal-header">
			                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
			                    <h3 id="myModalLabel">添加缴费流水</h3>
			                </div>
			                <div class="modal-body">
			                    <form class="form-horizontal">
			                        <div class="control-group">
			                            <label class="control-label" for="userName">姓名</label>
			                            <div class="controls">
			                                <input type="text" id="userName" name="personName">
			                            </div>
			                        </div>
			                        <div class="control-group">
			                            <label class="control-label" for="personRent">续租金额</label>
			                            <div class="controls">
			                                <input type="text" id="personRent" name="personRent">
			                            </div>
			                        </div>
			                        <div class="control-group">
			                            <label class="control-label" for="roomNo">续租房号</label>
			                            <div class="controls">
			                                <input type="text" id="roomNo" name="roomNo">
			                            </div>
			                        </div>
			                        <div class="control-group">
			                            <label class="control-label" for="createtime">创建日期</label>
			                            <div class="controls">
			                                <input type="text" id="createtime" name="createTime">
			                            </div>
			                        </div>
			                        <div class="control-group">
			                            <label class="control-label" for="leavetime">房租到期</label>
			                            <div class="controls">
			                                <input type="text" id="leavetime" name="leaveTime">
			                            </div>
			                        </div>
			                        <input style="display: none" value="" id="personId"/>
			                    </form>
			                </div>
			                <div class="modal-footer">
			                    <button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
			                    <button class="btn btn-primary" id="add" onclick="add();">保存</button>
			                </div>
			            </div>
			        </div>
			    </div>
            </div>
        </div>
    </div>
    <%@include file="include/foot.jsp"%>
</body>
<script type="text/javascript">
$(function () {
	//alert($("#img").contents().find("#img").text());//"欢迎您1111,"+$.cookie("userName")
	$("#createtime").datetimepicker({
	      format: "yyyy-mm-dd",
	      minView: "month",
	      language: 'zh-CN',
	      autoclose:true,
	      showMeridian : true,
	});
	$("#leavetime").datetimepicker({
	      format: "yyyy-mm-dd",
	      minView: "month",
	      language: 'zh-CN',
	      autoclose:true,
	      showMeridian : true
	});
});
function openadd() {
	  $("#addModal").modal("show");
	  $('input').val("");
	  $("#add").show();
}
function add(){
	var data = {
			personName:$("#userName").val(),
			personRent:$("#personRent").val(),
			roomNo:$("#roomNo").val(),
			createtime:$("#createtime").val(),
			leavetime:$("#leavetime").val(),
			};
	var id = $("#personId").val();
	if(id!=""){
		data.id=id;
	}
	$.ajax({
	      url: "insertPayRent.do",
	      dataType: "json",
	      type: "post",
	      data: data,
	      success:function(index){
	         if(index==1){
	        	 alert("添加成功!")
	         }else if(index==3){
	        	 alert("您所添加的学员在系统不存在!") 
	         }else if(index==2){
	        	 alert("数据更新成功!")
	         }else{
	        	 alert("系统错误,请稍后重试!")
	         }
	         $("#addModal").attr("style","display:none");
	         location.reload(true);
	         window.location.href="/skipPayMent.do"
    	  }
	})
}
function edit(id,personName,personRent,roomNo,createtime,leavetime){
	$("#addModal").modal("show");
	createtime = Todate(createtime);
	leavetime = Todate(leavetime);
	$("#userName").val(personName);
	$("#personRent").val(personRent);
	$("#roomNo").val(roomNo);
	$("#createtime").val(createtime);
	$("#leavetime").val(leavetime);
	$("#personId").val(id);
}  
function del(id){
	layer.confirm('您确定要删除？', {btn: ['确定', '取消']},function(){
		window.location.href="/delPayMentFlow.do?id="+id;
	},function(){layer.close();});
	
}
function find(){
	var text = $("#text").val();
	if(text==""){
		layer.confirm('请输入学员姓名', {btn: ['确定', '取消']},function(){
			window.location.href="/skipPayMent.do";
		},function(){layer.close();});
	}else{
		window.location.href="/skipPayMent.do?personName="+text;
	}
}
</script>
</html>