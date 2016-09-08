<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<div class="span2 content-left">
     <div class="accordion">
         <div class="accordion-group">
             <div class="accordion-heading">
                 <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion2" href="#collapseOne">
                     <img class="left-icon" src="/resource/images/32/5026_settings.png" /><span class="left-title">系统设置</span>
                 </a>
             </div>
             <div id="collapseOne" class="accordion-body collapse in">
                 <!-- <div class="accordion-inner">
                     <img class="left-icon-child" src="/resource/images/32/4962_sitemap.png" /><span class="left-body"> 新增学员</span>
                 </div> -->
                 <div class="accordion-inner">
                     <img class="left-icon-child" src="/resource/images/32/4992_user.png" /><span class="left-body"><a href="/getSysPersonData.do"> 学员信息</a></span>

                 </div>
                 <div class="accordion-inner">
                     <img class="left-icon-child" src="/resource/images/32/8.png" /><span class="left-body"><a href="/skipRoomManager.do"> 房间管理</a> </span>

                 </div>
                 <div class="accordion-inner" id="change">
                     <img class="left-icon-child" src="/resource/images/32/4957_customers.png" /><span class="left-body" ><a href="/skipPayMent.do"> 缴费流水</a></span>

                 </div>
                 <div class="accordion-inner">
                     <img class="left-icon-child" src="/resource/images/32/612.png" /><span class="left-body"><a href="/skipBothEnds.do"> 收支流水</a></span>

                 </div>
             </div>
         </div>
     </div>
 </div>