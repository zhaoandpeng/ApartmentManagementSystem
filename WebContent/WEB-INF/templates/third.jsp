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
			            	<a class="btn btn-mini btn-info" onclick="openadd();">新增收支流水</a>
			            	<a class="btn btn-mini btn-info" onclick="searchTotal()">查询</a>
			            	<input id="startTime">至<input id="endTime">总收入：<input id="totalInCome" value="${totalInCome}元"> 总支出：<input id="totalCost" value="${totalCost}元">
			            </div>
			            <div class="w">
			                <div class="span12">
			                    <table class="table table-condensed table-bordered table-hover tab">
			                        <thead>
			                            <tr>
			                                <th>创建日期</th>
			                                <th>收支类型</th>
			                                <th>金额</th>
			                                <th>备注</th>
			                                <th>操作</th>
			                            </tr>
			                        </thead>
			                        <tbody id="tbody">
			                        <c:forEach items="${listMoneyFlow}" var="p">
			                        	<tr>
			                        		<td style="display: none">${p.id}</td>
			                                <td><fmt:formatDate value="${p.createTime}" pattern="yyyy-MM-dd"/> </td>
			                                <td>
			                                <c:if test="${p.type==0}">支出</c:if>
			                                <c:if test="${p.type==1}">收入</c:if>
			                                </td>
			                                <td>${p.amount}元</td>
			                                <td>${p.text}</td>
			                                <td><button onclick="del('${p.id}')
			                                "> 删除</button></td>
			                            </tr>
			                        </c:forEach>
			                        </tbody>
			                    </table>
			                    <div id="page" class="tr"></div>
			                </div>
			                <div>
			                	<span class="pageButton">
			                	<a style="margin-left: 420px;border-width: 1;padding: 2 4 0 4;background-color: #ece9d8;height: 23;font-size: 16px;text-decoration:none" href="/skipBothEnds.do?pageNo=1">首页</a>&nbsp;
			                	<a style="border-width: 1;padding: 2 4 0 4;background-color: #ece9d8;height: 23;font-size: 16px;text-decoration:none" href="/skipBothEnds.do?pageNo=${currentPage-1}">上一页</a>&nbsp;
			                	<a style="border-width: 1;padding: 2 4 0 4;background-color: #ece9d8;height: 23;font-size: 16px;text-decoration:none" href="/skipBothEnds.do?pageNo=${currentPage+1}">下一页</a>&nbsp;
			                	<a style="border-width: 1;padding: 2 4 0 4;background-color: #ece9d8;height: 23;font-size: 16px;text-decoration:none" href="/skipBothEnds.do?pageNo=${TotalPage}">末页</a>
			                	</span>
                			</div>
			            </div>
			
			
			            <div id="addModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			                <div class="modal-header">
			                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
			                    <h3 id="myModalLabel">收支流水</h3>
			                </div>
			                <div class="modal-body">
			                    <form class="form-horizontal">
			                        <div class="control-group">
			                            <label class="control-label">选择类型</label>
			                            <div class="controls">
			                                <select class="form-control"> 
											      <option selected="selected" value="0">支出</option> 
											      <option value="1">收入</option> 
											</select>
			                            </div>
			                        </div>
			                        <div class="control-group">
			                            <label class="control-label">金额</label>
			                            <div class="controls">
			                                <input type="text" id="amount">
			                            </div>
			                        </div>
			                        <div class="control-group">
			                            <label class="control-label">备注</label>
			                            <div class="controls">
			                            	<textarea rows="6" id="textarea"></textarea>
			                            </div>
			                        </div>
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
	$("#startTime,#endTime").datetimepicker({
	      format: "yyyy-mm-dd",
	      minView: "month",
	      language: 'zh-CN',
	      autoclose:true,
	      showMeridian : true,
	});
});
//查询时间段之间的总收入和总支出
function searchTotal(){
	var startTime = $('#startTime').val();
	var endTime = $('#endTime').val();
	window.location.href="/skipBothEnds.do?startTime="+startTime+"&pageNo="+${currentPage}+"&endTime="+endTime;
}


function openadd() {
	  $("#addModal").modal("show");
	  $('input').val("");
	  $("#add").show();
}
function add(){
	var type = $(".form-control option:selected").val();
	var data = {"type":type,"amount":$("#amount").val(),"text":$("#textarea").val()};
	$.ajax({
	      url: "addBothEnds.do",
	      dataType: "json",
	      type: "post",
	      data: data,
	      success:function(index){
	         if(index==1){
	        	 alert("添加成功!")
	         }else{
	        	 alert("系统错误,请稍后重试!")
	         }
	         $("#addModal").attr("style","display:none");
	         location.reload(true);
	         window.location.href="/skipBothEnds.do"
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
		window.location.href="/delAmountFlow.do?id="+id;
	},function(){layer.close();});
}
function find(){
	var text = $("#text").val();
	if(text==""){
		window.location.href="/searchPayMent.do";
	}else{
		window.location.href="/searchPayMent.do?personName="+text;
	}
}
</script>
</html>