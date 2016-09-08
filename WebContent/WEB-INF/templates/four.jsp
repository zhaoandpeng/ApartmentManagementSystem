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
			            	<a class="btn btn-mini btn-info" onclick="openadd();">创建房间</a>
			            	<a class="btn btn-mini btn-info" onclick="find()">查询</a>
			            	<input id="room" value="" placeholder="房间号码">
			            </div>
			            <div class="w">
			                <div class="span12">
			                    <table class="table table-condensed table-bordered table-hover tab">
			                        <thead>
			                            <tr>
			                                <th>房间号码</th>
			                                <th>房间类型</th>
			                                <th>已住人数</th>
			                                <th>可住人数</th>
			                            </tr>
			                        </thead>
			                        <tbody id="tbody">
			                        <c:forEach items="${listSysRoom}" var="p">
			                        	<tr>
			                        		<td style="display: none">${p.id}</td>
			                                <td>${p.roomNo}室</td>
			                                <td>
			                                <c:if test="${p.roomType==0}">4人间</c:if>
			                                <c:if test="${p.roomType==1}">6人间</c:if>
			                                <c:if test="${p.roomType==2}">8人间</c:if>
			                                <c:if test="${p.roomType==3}">2人间</c:if>
			                                </td>
			                                <td>${p.roomRealNumber}人</td>
			                                <td>
			                                <c:if test="${p.roomType==0}">${4-p.roomRealNumber}人</c:if>
			                                <c:if test="${p.roomType==1}">${6-p.roomRealNumber}人</c:if>
			                                <c:if test="${p.roomType==2}">${8-p.roomRealNumber}人</c:if>
			                                <c:if test="${p.roomType==3}">${2-p.roomRealNumber}人</c:if>
			                                </td>
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
			                    <h3 id="myModalLabel">新建房间</h3>
			                </div>
			                <div class="modal-body">
			                    <form class="form-horizontal">
			                        <div class="control-group">
			                            <label class="control-label">房间号码</label>
			                            <div class="controls">
			                                <input type="text" id="roomNo">
			                            </div>
			                        </div>
			                        <div class="control-group">
			                            <label class="control-label">选择类型</label>
			                            <div class="controls">
			                                <select class="form-control"> 
											      <option selected="selected" value="0">4人间</option> 
											      <option value="1">6人间</option>
											      <option value="2">8人间</option> 
											      <option value="3">2人间</option>  
											</select>
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
function openadd() {
	  $("#addModal").modal("show");
	  $('input').val("");
	  $("#add").show();
}
function add(){
	var type = $(".form-control option:selected").val();
	var data = {"roomType":type,"roomNo":$("#roomNo").val()};
	$.ajax({
	      url: "addRoom.do",
	      dataType: "json",
	      type: "post",
	      data: data,
	      success:function(index){
	         if(index==1){
	        	 layer.msg('添加成功!')
	         }else{
	        	 layer.msg("系统错误!") 
	         }
	         $("#addModal").attr("style","display:none");
	         location.reload(true);
	         window.location.href="/skipRoomManager.do"
    	  }
	})
}
function find(){
	var text = $("#room").val();
	if(text==""){
		window.location.href="/skipRoomManager.do";
	}else{
		window.location.href="/skipRoomManager.do?roomNo="+text;
	}
}
</script>
</html>