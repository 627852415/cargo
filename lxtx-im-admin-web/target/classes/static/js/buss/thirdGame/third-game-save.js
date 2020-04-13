layui.use(['form', 'layedit', 'upload', 'element'], function () {
    var form = layui.form
        , layer = layui.layer
        , upload = layui.upload;

    // 当没有设置时默认选中启用状态
    if ($('input:radio[name=status]:checked').val() == undefined) {
        $('input:radio[name=status]')[0].checked = true;
        layui.form.render('radio');
    }

    //上传游戏图标
    upload.render({
        elem: '#iconImg' //绑定元素
        , url: '/sdk/game/upload/' //上传接口
        , method: 'POST'
        , accept: 'jpg|png'
        , done: function (res) {
            //上传完毕回调
            if (res.success) {
                document.getElementById("icon").value = res.data;
                layer.msg('上传成功！');
            }else if (res.msg != null) {
                layer.msg(res.msg);
            }
        }
        , error: function () {
            layer.msg('上传失败');
        }
    });

    //监听提交
    form.on('submit(sub-btn)', function (data) {
        var formData = JSON.stringify(data.field)
        submit('/sdk/game/save', formData);
        return false;
    });
});
