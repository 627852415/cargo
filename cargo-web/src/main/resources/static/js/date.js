function formatDate(d) {
    if(d == undefined){
        return "";
    }
    var now = new Date(d);
    var year = now.getFullYear();
    var month = now.getMonth() + 1 >= 10 ? (now.getMonth() + 1) : "0" + (now.getMonth() + 1);
    var date = now.getDate() >= 10 ? now.getDate() : "0" + now.getDate();
    var std = year + "-" + month + "-" + date;
    return std;
}

function formatDateL(d) {
    if(d == undefined){
        return "";
    }
    var now = new Date(d);
    var year = now.getFullYear();
    var month = now.getMonth() + 1 >= 10 ? (now.getMonth() + 1) : "0" + (now.getMonth() + 1);
    var date = now.getDate() >= 10 ? now.getDate() : "0" + now.getDate();
    var hour = now.getHours() >= 10 ? now.getHours() : "0" + now.getHours();
    var minute = now.getMinutes() >= 10 ? now.getMinutes() : "0" + now.getMinutes();
    var second = now.getSeconds() >= 10 ? now.getSeconds() : "0" + now.getSeconds();
    var std = year + "-" + month + "-" + date + " " + hour + ":" + minute + ":" + second;
    return std;
}

//1 短时间，形如 (13:04:06)
function isTime(str) {
    var a = str.match(/^(\d{1,2})(:)?(\d{1,2})\2(\d{1,2})$/);
    if (a == null) {
        alert('输入的参数不是时间格式');
        return false;
    }
    if (a[1] > 24 || a[3] > 60 || a[4] > 60) {
        alert("时间格式不对");
        return false
    }
    return true;
}

//2. 短日期，形如 (2008-07-22)
function strDate(str) {
    var r = str.match(/^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2})$/);
    if (r == null) return false;
    var d = new Date(r[1], r[3] - 1, r[4]);
    return (d.getFullYear() == r[1] && (d.getMonth() + 1) == r[3] && d.getDate() == r[4]);
}

//3 长时间，形如 (2008-07-22 13:04:06)
function strDateTime(str) {
    var reg = /^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2}) (\d{1,2}):(\d{1,2}):(\d{1,2})$/;
    var r = str.match(reg);
    if (r == null) return false;
    var d = new Date(r[1], r[3] - 1, r[4], r[5], r[6], r[7]);
    return (d.getFullYear() == r[1] &&
        (d.getMonth() + 1) == r[3] && d.getDate() == r[4] && d.getHours() == r[5] && d.getMinutes() == r[6] && d.getSeconds() == r[7
            ]);
}

//比较日前大小
function compareDate(checkStartDate, checkEndDate) {
    var arys1 = new Array();
    var arys2 = new Array();
    if (checkStartDate != null && checkEndDate != null) {
        arys1 = checkStartDate.split('-');
        var sdate = new Date(arys1[0], parseInt(arys1[1] - 1), arys1[2]);
        arys2 = checkEndDate.split('-');
        var edate = new Date(arys2[0], parseInt(arys2[1] - 1), arys2[2]);
        if (sdate > edate) {
            return false;
        } else {
            return true;
        }
    }
}

//判断日期，时间大小
function compareTime(startDate, endDate) {
    if (startDate.length > 0 && endDate.length > 0) {
        var startDateTemp = startDate.split(" ");
        var endDateTemp = endDate.split(" ");

        var arrStartDate = startDateTemp[0].split("-");
        var arrEndDate = endDateTemp[0].split("-");

        var arrStartTime = startDateTemp[1].split(":");
        var arrEndTime = endDateTemp[1].split(":");

        var allStartDate = new Date(arrStartDate[0], arrStartDate[1], arrStartDate[2], arrStartTime[0], arrStartTime[1], arrStartTime[2]);
        var allEndDate = new Date(arrEndDate[0], arrEndDate[1], arrEndDate[2], arrEndTime[0], arrEndTime[1], arrEndTime[2]);

        if (allStartDate.getTime() >= allEndDate.getTime()) {
            return false;
        } else {
            return true;
        }
    } else {
        return false;
    }
}

//比较日期，时间大小
function compareCalendar(startDate, endDate) {
    var result = false;
    startDate = formatDateL(startDate);
    endDate = formatDateL(endDate)
    if (startDate.indexOf(" ") != -1 && endDate.indexOf(" ") != -1) {
        //包含时间，日期
        result = compareTime(startDate, endDate);
    } else {
        //不包含时间，只包含日期
        result = compareDate(startDate, endDate);
    }
    return result;
}

//获取几天后日期
function getDateAfter(AddDayCount) {
    var dd = new Date();
    dd.setDate(dd.getDate()+AddDayCount);//获取AddDayCount天后的日期
    var y = dd.getFullYear();
    var m = dd.getMonth()+1;//获取当前月份的日期
    var d = dd.getDate();
    return y+"-"+m+"-"+d;
}

/**
 * 获取几个月之前的日期
 * @param currDate 
 * @param mondiff >0 表示几个月之前，< 0 表示几个月之后
 * @returns
 */
function getPreMonth(currDate, mondiff) {  
		var year = currDate.getFullYear();//获取当前日期的年份  
	    var month = currDate.getMonth()+1;//获取当前日期的月份  
	    var day = currDate.getDate();  //获取当前日期的日  
    var days = new Date(year, month, 0);  
    days = days.getDate(); //获取当前日期中月的天数  
    var year2 = year;  
    var month2 = parseInt(month) + mondiff;  
    if (month2 == 0) {  
        year2 = parseInt(year2) + mondiff;  
        month2 = 12;  
    }  
    var day2 = day;  
    var days2 = new Date(year2, month2, 0);  
    days2 = days2.getDate();  
    if (day2 > days2) {  
        day2 = days2;  
    }  
    if (month2 < 10) {  
        month2 = '0' + month2;  
    }  
    var t2 = year2 + '-' + month2 + '-' + day2;  
    return t2;  
}