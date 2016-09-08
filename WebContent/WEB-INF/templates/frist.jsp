<body>
    <div class="container-fluid">
        <div class="row-fluid">
            <h4>数据列表</h4>
            <div class="add">
            	<a class="btn btn-success" onclick="openadd();">新增</a>
            	<a class="btn btn-success" onclick="find();">查询</a><input type="text" id="textone" placeholder="学员姓名" style="height:20px;position:relative"><input type="text" id="texttwo" placeholder="房间号" style="height:20px;position:relative"></span>
            </div>
            <div class="w">
                <div class="span12">
                    <table class="table table-condensed table-bordered table-hover tab">
                        <thead>
                            <tr>
                            	<th style="display: none"></th>
                                <th>房间号</th>
                                <th>姓名</th>
                                <th>入住时间</th>
                                <th>租金</th>
                                <th>押金</th>
                        		<th>合计金额</th>
                                <th>房租到期时间</th>
                                <th>操作</th>
                            </tr>
                        </thead>
                        <tbody id="tbody">
                        	<c:forEach items="${listData}" var="p">
                        	<tr>
                        		<td style="display: none">${p.id}</td>
                                <td>${p.roomNo}室</td>
                                <td>${p.personName}</td>
                                <td>
                                <fmt:formatDate value="${p.createTime}" pattern="yyyy-MM-dd"/>
                                </td>
                                <td>${p.twoMonthMount}元</td>
                                <td>${p.deposit}元</td>
                                <td>${p.totalMount}元</td>
                        		<td class="datess">
                        		<fmt:formatDate value="${p.expireTime}" pattern="yyyy-MM-dd"/>
                        		</td>
                                <td>
                                <c:if test="${username=='000001'}">
                                <button onclick="edit('${p.id}','${p.roomNo}','${p.personName}','${p.createTime}','${p.twoMonthMount}','${p.deposit}','${p.totalMount}','${p.expireTime}')
			                      ">编辑</button>
			                    </c:if>
                                <button onclick="addfee('${p.id}','${p.personName}','${p.roomNo}')"> 缴费</button>
                                <c:if test="${p.isBack=='0'||p.isBack==null}">
                                	<button onclick="pay_back ('${p.id}','${p.personName}','${p.roomNo}')"> 退租</button>
                                </c:if>
                                <c:if test="${p.isBack=='1'}">
                                	<button style="background:#00BFFF;color: blue" disabled="disabled">退租</button>
                                </c:if>
                                </td>
                            </tr>
                            </c:forEach>
                        </tbody>
                        
                    </table>
                    <div id="page" class="tr"></div>
                </div>
                <div>
                	<span class="pageButton">
                	<a style="margin-left: 420px;border-width: 1;padding: 2 4 0 4;background-color: #ece9d8;height: 23;font-size: 16px;text-decoration:none" href="/getSysPersonData.do?pageNo=1">首页</a>&nbsp;
                	<a style="border-width: 1;padding: 2 4 0 4;background-color: #ece9d8;height: 23;font-size: 16px;text-decoration:none" href="/getSysPersonData.do?pageNo=${currentPage-1}">上一页</a>&nbsp;
                	<a style="border-width: 1;padding: 2 4 0 4;background-color: #ece9d8;height: 23;font-size: 16px;text-decoration:none" href="/getSysPersonData.do?pageNo=${currentPage+1}">下一页</a>&nbsp;
                	<a style="border-width: 1;padding: 2 4 0 4;background-color: #ece9d8;height: 23;font-size: 16px;text-decoration:none" href="/getSysPersonData.do?pageNo=${TotalPage}">末页</a>
                	</span>
                </div>
            </div>
			
			<!-- 退租弹出框 -->
			<div id="backModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	                <div class="modal-header">
	                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
	                    <h3 id="myModalLabel">退租</h3>
	                </div>
	                <div class="modal-body">
	                    <form class="form-horizontal">
	                        <div class="control-group">
	                            <label class="control-label">姓名</label>
	                            <div class="controls">
	                                <input type="text" id="personName" name="personName">
	                            </div>
	                        </div>
	                        <div class="control-group">
	                            <label class="control-label">退租房号</label>
	                            <div class="controls">
	                                <input type="text" id="roomNo" name="roomNo">
	                            </div>
	                        </div>
	                        <div class="control-group">
	                            <label class="control-label">退租金额</label>
	                            <div class="controls">
	                                <input type="text" id="personRent" name="personRent">
	                            </div>
	                        </div>
	                        <div class="control-group">
	                            <label class="control-label">退租日期</label>
	                            <div class="controls">
	                                <input type="text" id="createtime" name="createTime">
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
	                    <button class="btn btn-primary" onclick="payBack();">保存</button>
	                </div>
	                <input style="display: none" id="personId" />
			</div>
			 
			<!-- 缴费弹出框 -->
			<div id="feeModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	                <div class="modal-header">
	                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
	                    <h3 id="myModalLabel">缴费流水</h3>
	                </div>
	                <div class="modal-body">
	                    <form class="form-horizontal">
	                        <div class="control-group">
	                            <label class="control-label">姓名</label>
	                            <div class="controls">
	                                <input type="text" id="personName" name="personName">
	                            </div>
	                        </div>
	                        <div class="control-group">
	                            <label class="control-label">续租房号</label>
	                            <div class="controls">
	                                <input type="text" id="roomNo" name="roomNo">
	                            </div>
	                        </div>
	                        <div class="control-group">
	                            <label class="control-label">续租金额</label>
	                            <div class="controls">
	                                <input type="text" id="personRent" name="personRent">
	                            </div>
	                        </div>
	                        <div class="control-group">
	                            <label class="control-label">创建日期</label>
	                            <div class="controls">
	                                <input type="text" id="createtime" name="createTime">
	                            </div>
	                        </div>
	                        <div class="control-group">
	                            <label class="control-label">房租到期</label>
	                            <div class="controls">
	                                <input type="text" id="leavetime" name="leaveTime">
	                            </div>
	                        </div>
	                    </form>
	                </div>
	                <div class="modal-footer">
	                    <button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
	                    <button class="btn btn-primary" onclick="insert();">保存</button>
	                </div>
	                <input style="display: none" id="personId"/>
			 </div>
			<!--  -->
            <div id="addModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                    <h3 id="myModalLabel">入住信息</h3>
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
                            <label class="control-label">姓名</label>
                            <div class="controls">
                                <input type="text" id="personName">
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">入住时间</label>
                            <div class="controls">
                                <input type="text" id="startTime">
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">租金</label>
                            <div class="controls">
                                <input type="text" id="twoMonthMount">
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">收入押金</label>
                            <div class="controls">
                                <input type="text" id="deposit">
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">合计金额</label>
                            <div class="controls">
                                <input type="text" id="totalMount">
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label" for="expireTimes">房租到期时间</label>
                            <div class="controls">
                                <input type="text" id="expireTime">
                            </div>
                        </div>
                        <input style="display: none" value="" id="id"/>
                    </form>
                </div>
                <div class="modal-footer">
                    <button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
                    <button class="btn btn-primary" id="add" onclick="add();">保存</button>
                    <button class="btn btn-primary" id="update" onclick="update();">修改</button>
                </div>
            </div>
        </div>
    </div>
	
    <script src="/assets/js/jquery-1.8.2.min.js"></script>
    <script src="/resource/bootstrap2.3.2/js/bootstrap.min.js"></script>
    <script src="/resource/layer-v2.3/layer/layer.js"></script>
    <script src="/resource/laypage-v1.3/laypage/laypage.js"></script>
    <script src="/assets/js/log4javascript.js"></script>
</body>
<script type="text/javascript">
$(function () {
	$("#expireTime,#startTime,#createtime,#leavetime").datetimepicker({
	      format: "yyyy-mm-dd",
	      minView: "month",
	      language: 'zh-CN',
	      autoclose:true,
	      showMeridian : true,
	});
	var flag = '${flag}';
	if(flag=="0"){
		$("#tbody tr").attr("bgcolor","red")
	}
	
	//自动计算两值相加
	$("#addModal #deposit").blur(function(){
		$("#addModal #totalMount").val((parseInt($("#addModal #deposit").val())+parseInt($("#addModal #twoMonthMount").val())));
	});
	
	//计算房间可用状态
	$("#addModal #roomNo").blur(function(){
		$.ajax({
		      url: "findRoomStatus.do",
		      dataType: "json",
		      type: "post",
		      data: {"roomNo":$("#addModal #roomNo").val()},
		      success:function(map){
		         if(map.flag!==0){
		        	 layer.msg(map.message);
		        	 $("#addModal input:eq(1),#addModal input:eq(2),#addModal input:eq(3),#addModal input:eq(4),#addModal input:eq(5),#addModal input:eq(6)").attr("disabled",true);
		         }else{
		        	 $("#addModal input").attr("disabled",false)
		         }
	      	  }
	  	})
	})
});
//缴费弹出框
function addfee(id,personName,roomNo){
	$("#feeModal #personName").val(personName);
	$("#feeModal #roomNo").val(roomNo);
	$("#feeModal #personId").val(id);
	$("#feeModal").modal("show");
}
//添加学员弹出框
function openadd() {
	$("#addModal #update").attr("disabled",true);
	$("#addModal input").val('');
	$("#addModal").modal("show");
}
//添加用户
function add(){
	var data = {"roomNo":$("#addModal #roomNo").val(),"personName":$("#addModal #personName").val(),
				"start_Time":$("#addModal #startTime").val(),"twoMonthMount":$("#addModal #twoMonthMount").val(),
				"deposit":$("#addModal #deposit").val(),"totalMount":$("#addModal #totalMount").val(),
				"expire_Time":$("#addModal #expireTime").val()}
	$.ajax({
	      url: "insertSysPerson.do",
	      dataType: "json",
	      type: "post",
	      data: data,
	      success:function(index){
	         if(index==1){
	        	 layer.msg('添加成功!')
	         }else if(index==3){
	        	 layer.msg('数据更新成功!')
	         }else{
	        	 layer.msg("系统错误!") 
	         }
	         $("#addModal").attr("style","display:none");
	         location.reload(true); 
      	  }
  	})
}
//流水增加
function insert(){
	var data = {"roomNo":$("#feeModal #roomNo").val(),"personName":$("#feeModal #personName").val(),
				"leave_time":$("#feeModal #leavetime").val(),"create_time":$("#feeModal #createtime").val(),
				"personRent":$("#feeModal #personRent").val(),"personId":$("#feeModal #personId").val()}
	$.ajax({
	      url: "insertPayRent.do",
	      dataType: "json",
	      type: "post",
	      data: data,
	      success:function(index){
	         if(index==1){
	        	 layer.msg('缴费成功')
	         }else if(index==2){
	        	 layer.msg('数据更新成功')
	         }else{
	        	 layer.msg('系统错误,请稍后重试')
	         }
	         $("#addModal").attr("style","display:none");
	         setTimeout(function(){window.location.href="/skipPayMent.do"}, 1000);
  	  }
	})
}

//退租请求
function payBack(){
	var data = {"roomNo":$("#backModal #roomNo").val(),"personName":$("#backModal #personName").val(),
				"create_time":$("#backModal #createtime").val(),"personRent":$("#backModal #personRent").val(),
				"textarea":$("#backModal #textarea").val(),"personId":$("#backModal #personId").val()}
	$.ajax({
	      url: "payBack.do",
	      dataType: "json",
	      type: "post",
	      data: data,
	      success:function(index){
	         if(index==1){
	        	 layer.msg('退租成功')
	         }else{
	        	 layer.msg('系统错误,请稍后重试')
	         }
	         $("#backModal").attr("style","display:none");
	         location.reload(true);
  	  }
	})
}

function find(){
	var personName = $("#textone").val();
	var roomNo = $("#texttwo").val();
	window.location.href="/getSysPersonData.do?personName="+personName+"&pageNo="+${currentPage}+"&roomNo="+roomNo;
}

//修改学员信息弹出窗
function edit(id,roomNo,personName,createTime,twoMonthMount,deposit,totalMount,expireTime){
	$("#addModal #id").val(id);
	$("#addModal #roomNo").val(roomNo);
	$("#addModal #personName").val(personName);
	$("#addModal #startTime").val(Todate(createTime));
	$("#addModal #twoMonthMount").val(twoMonthMount);
	$("#addModal #deposit").val(deposit);
	$("#addModal #totalMount").val(totalMount);
	$("#addModal #expireTime").val(Todate(expireTime));
	$("#addModal #update").attr("disabled",false);
	$("#addModal #add").attr("disabled",true);
	$("#addModal").modal("show");
} 

function update(){
	var data = {"roomNo":$("#addModal #roomNo").val(),"id":$("#addModal #id").val(),
				"personName":$("#addModal #personName").val(),"start_Time":$("#addModal #startTime").val(),
				"twoMonthMount":$("#addModal #twoMonthMount").val(),"deposit":$("#addModal #deposit").val(),
				"totalMount":$("#addModal #totalMount").val(),"expire_Time":$("#addModal #expireTime").val()
			   }
	$.ajax({
	      url: "insertSysPerson.do",
	      dataType: "json",
	      type: "post",
	      data: data,
	      success:function(index){
	         if(index==1){
	        	 layer.msg('修改成功')
	         }else if(index==3){
	        	 layer.msg('数据更新成功')
	         }else{
	        	 layer.msg('系统错误,请稍后重试')
	         }
	         $("#updateModal").attr("style","display:none");
	         setTimeout(function(){window.location.href="/getSysPersonData.do"}, 1000);
	  }
	})	
}

//弹出退租框
function pay_back(id,personName,roomNo){
	$("#backModal #personId").val(id);
	$("#backModal #personName").val(personName);
	$("#backModal #roomNo").val(roomNo);
	$("#backModal").modal("show");
}

//JS日期格式化
function Todate(num){

        //Fri Oct 31 18:00:00 UTC+0800 2008  
        num=num+"";
        var date="";
        var month=new Array();
        month["Jan"]=1;month["Feb"]=2;month["Mar"]=3;month["Apr"]=4;month["May"]=5;month["Jan"]=6;
        month["Jul"]=7;month["Aug"]=8;month["Sep"]=9;month["Oct"]=10;month["Nov"]=11;month["Dec"]=12;
        var week=new Array();
        week["Mon"]="一";week["Tue"]="二";week["Wed"]="三";week["Thu"]="四";week["Fri"]="五";week["Sat"]="六";week["Sun"]="日";
        str=num.split(" ");
        date=str[5]+"-";
        date=date+month[str[1]]+"-"+str[2]+" "+str[3];
        //date=date+" 周"+week[str[0]];
        return date;
}

</script>
