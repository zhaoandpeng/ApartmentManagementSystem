<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<style type="text/css">
#winpop { width:200px; height:0px; position:absolute; right:5px; bottom:5px; border:1px solid #999999; margin:0; padding:1px; overflow:hidden; display:none; background:url(http://www.niutw.com/images/bg.png) #FFFFFF}
#winpop .title { width:100%; height:20px; line-height:20px; background:#FFCC00; font-weight:bold; text-align:center; font-size:12px;}
#winpop .con { width:100%; height:80px; line-height:80px; font-weight:bold; font-size:12px; color:#FF0000; text-decoration:underline; text-align:center}
#silu { font-size:13px; color:#999999; position:absolute; right:0; text-align:right; text-decoration:underline; line-height:22px;}
.close { position:absolute; right:4px; top:-1px; color:#FFFFFF; cursor:pointer}
</style>
<%@include file="include/header.jsp"%>
<body>
    <div class="container-fluid content">
        <div class="row-fluid">
           <%@include file="include/left.jsp"%>
           <div class="span10 content-right">
                <%@include file="frist.jsp"%>
           </div>
        </div>
    </div>
    <%@include file="include/foot.jsp"%>
<div id="winpop">
<div class="title">您有新的消息<span class="close" onclick="tips_pop()">X</span></div>
<div class="con"><a href="/getSysPersonData.do?expireTime=thisTime" class="tip"></a></div>
</div>
</body>
<script type="text/javascript">
	function tips_pop() {
		var MsgPop = document.getElementById("winpop");//获取窗口这个对象,即ID为winpop的对象
		var popH = parseInt(MsgPop.style.height);//用parseInt将对象的高度转化为数字,以方便下面比较
		if (popH == 0) { //如果窗口的高度是0
			MsgPop.style.display = "block";//那么将隐藏的窗口显示出来
			show = setInterval("changeH('up')", 2);//开始以每0.002秒调用函数changeH("up"),即每0.002秒向上移动一次
		} else { //否则
			hide = setInterval("changeH('down')", 2);//开始以每0.002秒调用函数changeH("down"),即每0.002秒向下移动一次
		}
	}
	
	function changeH(str) {
		var MsgPop = document.getElementById("winpop");
		var popH = parseInt(MsgPop.style.height);
		if (str == "up") { //如果这个参数是UP
			if (popH <= 100) { //如果转化为数值的高度小于等于100
				MsgPop.style.height = (popH + 4).toString() + "px";//高度增加4个象素
			} else {
				clearInterval(show);//否则就取消这个函数调用,意思就是如果高度超过100象度了,就不再增长了
			}
		}
		if (str == "down") {
			if (popH >= 4) { //如果这个参数是down
				MsgPop.style.height = (popH - 4).toString() + "px";//那么窗口的高度减少4个象素
			} else { //否则
				clearInterval(hide); //否则就取消这个函数调用,意思就是如果高度小于4个象度的时候,就不再减了
				MsgPop.style.display = "none"; //因为窗口有边框,所以还是可以看见1~2象素没缩进去,这时候就把DIV隐藏掉
			}
		}
	}

	window.onload = function() { //加载
		document.getElementById('winpop').style.height = '0px';//我不知道为什么要初始化这个高度,CSS里不是已经初始化了吗,知道的告诉我一下
		setTimeout("findExpirePerson()", 800); //3秒后调用tips_pop()这个函数  tips_pop()
	}
	
	function findExpirePerson(){
			$.ajax({
			      url: "ajaxGetSysPersonData.do",
			      dataType: "json",
			      type: "post",
			      success:function(map){
			    	 if(map.count!==0){
			    	  	 $(".tip").text('有 '+map.count+'位学员房租到期,点击此处查看');
			    	  	 tips_pop();
			    	 }
			      }
			})
	}
</script>
</html>