layui.use(['form'], function(){
    var form = layui.form;
    //日期时间
    initializeDatetime("startTime", true);
    initializeDatetime("endTime", true);
    initializeDatetime("unlockTime", true);
    //自定义验证规则
    form.verify({
        coinName: function(value, obj){
            if (value == 0) {
                return "请选择币种";
            }
        },
        type: function(value){
            if (value == undefined) {
                return "请选择空投对象";
            }
        }
    });

    form.on('select(coinName)', function (data) {
        if (data.elem[data.elem.selectedIndex].title == "true"
            || data.elem[data.elem.selectedIndex].title == true
            || "BYB" == data.elem[data.elem.selectedIndex].value) {
            $(".unlockTime").hide();
            $("input[name='unlockTime']").val("");
            $("input[name='unlockTime']").removeAttr("lay-verify");
        }else {
            $("input[name='unlockTime']").attr("lay-verify", "required");
            $(".unlockTime").show();
        }
    });
    form.on('radio(type)', function (data) {
        if (data.value == "2" || data.value == 2) {
            $(".unitAmount").hide();
            $("input[name='unitAmount']").val("");
            $("input[name='unitAmount']").removeAttr("lay-verify");
        }else {
            $("input[name='unitAmount']").attr("lay-verify", "required");
            $(".unitAmount").show();
        }
    });

    //监听提交
    form.on('submit(save)', function(data){
        if (!data.field.type){
            layer.alert(JSON.stringify("请选择空投对象"), {
                title: '填写信息有误'
            });
            return false;
        }
        return submit('/airdrop/add', JSON.stringify(data.field), '/airdrop/index');
    });
});