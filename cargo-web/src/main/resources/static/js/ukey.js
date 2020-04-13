/*****************************************************************
 jQuery Ajax封装通用类  (用于通用UKEY请求)
 *****************************************************************/



$(function(){
    jQuery.ajaxUkey=function(ajaxBody) {
        $.get('/manage/usb/token/switch?userName',function(result){
            var isOpenUsbToken = result.data.openUsbMode;
            if(isOpenUsbToken){
                //开关打开，进行USB验证并且登录
                var token = result.data.token;
                verifyTokenAndCallBack (token,ajaxBody);
            }else{
                $.ajax(ajaxBody);
            }
        });
    };


    jQuery.ajaxUrlUkey=function() {
        $.get('/manage/usb/token/switch',function(result){
            var isOpenUsbToken = result.data.openUsbMode;
            if(isOpenUsbToken){
                //开关打开，进行USB验证并且登录
                var token = result.data.token;
                console.log("token"+token);
                verifyTokenByUrl(token);
            }else{
                return false;
            }
        });
    };

    /*jQuery.ajaxUkeyDef=function(callback) {
        $.get('/manage/usb/token/switch?userName',function(result){
            var isOpenUsbToken = result.data.openUsbMode;
            if(isOpenUsbToken){
                //开关打开，进行USB验证并且登录
                var token = result.data.token;
                verifyTokenAndDefindCallBack(token,callback);
            }else{
                callback();
            }
        });
    };*/

});



function verifyTokenByUrl(token){

    var org = {};
    var extensionId = 'ihjdinpccpakfpcafodpgfgcfmapokhc';

    var str = getCurrentTime();
    var hex = sha256(str);
    try {
        chrome.runtime.sendMessage(
            extensionId,
            {
                "jsonrpc": "2.0",
                "method": "Token_signRsa",
                "params": {
                    "hash": hex
                },
                id: ""
            },
            function (response) {
                if (!response) {
                    layer.alert('操作失败，失败原因：usbtoken签名过程无返回\n');
                    return false;
                } else if (!response.result) {
                    layer.alert('操作失败，没有USB token');
                    return false;
                } else {
                    org.sign = response.result.signature;
                    org.time = str;
                    org.cert = response.result.cert;
                    org.token = token;
                    return org;
                }
            });
    }
    catch(err)
    {
        layer.alert('USB TOKEN 验证失败，请检查域名配置!');
        return false;
    }
    return false;
}



function verifyTokenAndCallBack(token,ajaxBody){

    var org = {};
    var extensionId = 'ihjdinpccpakfpcafodpgfgcfmapokhc';

    var str = getCurrentTime();
    var hex = sha256(str);
    try {
        chrome.runtime.sendMessage(
            extensionId,
            {
                "jsonrpc": "2.0",
                "method": "Token_signRsa",
                "params": {
                    "hash": hex
                },
                id: ""
            },
            function (response) {
                if (!response) {
                    layer.alert('操作失败，失败原因：usbtoken签名过程无返回\n');
                    return false;
                } else if (!response.result) {
                    layer.alert('操作失败，没有USB token');
                    return false;
                } else {
                    org.sign = response.result.signature;
                    org.time = str;
                    org.cert = response.result.cert;
                    org.token = token;
                    ajaxBody.headers =  {'token': token,"sign":org.sign,"time":org.time,"cert":org.cert};
                    $.ajax(ajaxBody);
                   /* $.ajax({
                        url: '/manage/usb/token/verify',
                        type: 'post',
                        dateType: 'json',
                        async: false,
                        headers: {'token': token,"sign":sign,"time":time,"cert":cert},
                        data: JSON.stringify(org),
                        "contentType": "application/json",
                        "dataType": "json",
                        success: function (result) {
                            if (result.data == true) {
                                //验证成功，进行登录
                                $.ajax(ajaxBody);
                            } else {
                                layer.alert('令牌验证失败');
                                return false;
                            }
                        },
                        error: function (result) {
                            layer.alert('操作失败，失败原因：' + result.msg + '\r\n');
                            return false;
                        }
                    })*/
                }
            });
    }
    catch(err)
    {
        layer.alert('USB TOKEN 验证失败，请检查域名配置!');
        return false;
    }
    return false;
}



/**
  * 获取当前时间 格式：yyyy-MM-dd HH:MM:SS
  */
function getCurrentTime() {
    var date = new Date();//当前时间
    var month = zeroFill(date.getMonth() + 1);//月
    var day = zeroFill(date.getDate());//日
    var hour = zeroFill(date.getHours());//时
    var minute = zeroFill(date.getMinutes());//分
    var second = zeroFill(date.getSeconds());//秒

    //当前时间
    var curTime = date.getFullYear() + "-" + month + "-" + day
        + " " + hour + ":" + minute + ":" + second;

    return curTime;
}

/**
  * 补零
  */
function zeroFill(i){
    if (i >= 0 && i <= 9) {
        return "0" + i;
    } else {
        return i;
    }
}