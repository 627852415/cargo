/*****************************************************************
 jQuery Ajax封装通用类  (用于通用UKEY请求)
 *****************************************************************/



$(function(){
    jQuery.ajaxUkey=function(ajaxBody) {
        $.ajax(ajaxBody);
    };


    jQuery.ajaxUrlUkey=function() {
        callback();
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


