//日期格式化
function Todate(num) { 
    num = num + "";
    var date = "";
    var month = new Array();
    month["Jan"] = 01; month["Feb"] = 02; month["Mar"] = 03; month["Apr"] = 04; month["May"] = 05; month["Jan"] = 06;
    month["Jul"] = 07; month["Aug"] = 08; month["Sep"] = 09; month["Oct"] = 10; month["Nov"] = 11; month["Dec"] = 12;
    var week = new Array();
    week["Mon"] = "一"; week["Tue"] = "二"; week["Wed"] = "三"; week["Thu"] = "四"; week["Fri"] = "五"; week["Sat"] = "六"; week["Sun"] = "日";
    str = num.split(" ");
    date = str[5] + "-";
    date = date + month[str[1]] + "-" + str[2];
    return date;
}
