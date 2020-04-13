layui.use(['jquery','layer','form', 'upload'], function () {
    var form = layui.form
, upload = layui.upload
        ,$=layui.jquery
        ,layer=layui.layer;

    //上传游戏图片
    upload.render({
        elem: '#gameIconImgId' //绑定元素
        , url: '/game/upload/' //上传接口
        , method: 'POST'
        , accept: 'jpg|png'
        , done: function (res) {
            //上传完毕回调
            if (res.success) {
                document.getElementById("icon").value = res.data;
                $("#gameIconImgIdShow").attr("src", $("#viewHost").val() + res.data);
                layer.msg('上传游戏图片成功！');
            }else if (res.msg != null) {
                layer.msg(res.msg);
            }
        }
        , error: function () {
            layer.msg('上传游戏图片失败');
        }
    });

    //上传类型图标
    upload.render({
        elem: '#gameImageId' //绑定元素
        , url: '/game/upload/' //上传接口
        , method: 'POST'
        , accept: 'jpg|png'
        , done: function (res) {
            //上传完毕回调
            if (res.success) {
                document.getElementById("image").value = res.data;
                $("#gameImageIdShow").attr("src", $("#viewHost").val() + res.data);
                layer.msg('上传类型图标成功！');
            }else if (res.msg != null) {
                layer.msg(res.msg);
            }
        }
        , error: function () {
            layer.msg('上传类型图标失败');
        }
    });

    // 图片预览
    $('div img').click(function(){
        var imgSrc=$(this).attr('src')
        layer.open({
            type:1
            ,title:false
            ,closeBtn:0
            ,skin:'layui-layer-nobg'
            ,shadeClose:true
            ,content:'<img src="'+imgSrc+'" style="max-height:600px;max-width:100%;">'
            ,scrollbar:false
        });
    });

    //监听提交
    form.on('submit(sub-btn)', function (data) {
        var formData = JSON.stringify(data.field)
        submit('/game/save', formData);
        return false;
    });
});




