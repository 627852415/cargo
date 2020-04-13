layui.use(['form', 'layedit', 'upload', 'element'], function() {
    var form = layui.form
        , layer = layui.layer
        , layedit = layui.layedit
        , element = layui.element
        , upload = layui.upload;

    //上传表情包封面
    upload.render({
        elem: '#cover' //绑定元素
        , url: '/sticker/upload/cover' //上传接口
        , method: 'POST'
        , accept: 'jpg|png'
        , done: function (res) {
            //上传完毕回调
            if (res.success) {
                document.getElementById("coverHidden").value = res.data;
                layer.msg('上传成功！');
            }else if (res.msg != null) {
                layer.msg(res.msg);
            }
        }
        , error: function () {
            layer.msg('上传失败');
        }
        , before: function (obj) {
            //预读本地文件示例，不支持ie8
            obj.preview(function (index, file, result) {
                $('#demo1').attr('src', result); //图片链接（base64）
            });
        }
    });

    //监听提交
    form.on('submit(sub-btn)', function (data) {
        var formData = JSON.stringify(data.field)
        submit("/sticker/update", formData, "/sticker/list");
        return false;
    });

});

loadTableWithoutColumns('/sticker/detail/list','dataTable', 'searchDetailForm');

//延时加载
setTimeout('rowTool()', 1000); //延迟1秒

function rowTool(){
    layui.use('table', function (){
        //监听行工具事件
        table.on('edit(dataTable)', function(obj){
            var value = obj.value //得到修改后的值
                ,data = obj.data //得到所在行所有键值
                ,field = obj.field
                ,id = data.id; //得到字段
            var formData = JSON.stringify({
                "id": id,
                "name" : field,
                "value" : value
            });
            sendAjaxPost("/sticker/update/detail", formData);
        });
    });
}
