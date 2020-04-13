//全局的AJAX访问，处理AJAX清求时SESSION超时
if(typeof($)!="undefined"){
    $.ajaxSetup({
        complete : function(XMLHttpRequest, textStatus) {
            if(textStatus=="parsererror"){
                // 这里跳转的登录页面
                parent.layer.msg("登录过期，请重新登录", function() {
                    // 重新登录(http请求重新加载页面后，如果没登录，则直接被拦截跳转到登录页面)
                    window.location.reload();
                });
            } else if(textStatus=="error"){
                parent.layer.msg("请求超时！请稍后再试！");
            }
        }
    });
}