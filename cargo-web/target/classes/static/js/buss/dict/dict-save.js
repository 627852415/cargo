layui.use('form', function () {
    var form = layui.form;
    //监听提交
    form.on('submit(sub-btn)', function (data) {
        var formData = JSON.stringify(data.field)
        submit('/dict/save', formData);
        return false;
    });
});




