//
//var curr = 1;
//$(function () {
////    load(curr);
//})
//
//
//function load(curr) {
//    $.ajax({
//        url: "../Json/Index.aspx",
//        timeout: 300000,
//        dataType: "json",
//        type: "post",
//        data: { "flag": "load", "curr": curr },
//        success: function (data) {
//
//            var html = "";
//            $.each(data.items, function (i, item) {
//                html += " <tr> " +
//                        " <td>" + item.userName + "</td> " +
//                        " <td>" + item.Chinese + "</td> " +
//                        " <td>" + item.Math + "</td> " +
//                        " <td>" + item.English + "</td> " +
//                        " <td><a class=\"btn btn-info\" onclick='openedt(\"" + item.userName + "\");'>修改</a>&nbsp;&nbsp;<a class=\"btn btn-warning\" onclick='del(\"" + item.userName + "\");'>删除</a></td> " +
//                        " </tr>";
//            })
//            $("#tbody").html(html);
//            laypage({
//                cont: 'page', //容器。值支持id名、原生dom对象，jquery对象。【如该容器为】：<div id="page1"></div>
//                pages: Math.ceil(data.cnt / 10), //通过后台拿到的总页数
//                skin: "#49afcd",
//                curr: curr || 1, //当前页
//                jump: function (obj, first) { //触发分页后的回调
//                    if (!first) { //点击跳页触发函数自身，并传递当前页：obj.curr
//                        curr = obj.curr;
//                        load(curr);
//                    }
//                }
//            });
//
//        }
//    })
//}