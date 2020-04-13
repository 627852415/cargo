layui.use('form', function () {
    var form = layui.form;
    //监听提交
    form.on('submit(sub-btn)', function (data) {
        if ($.isEmptyObject(data.field.domain)) {
            data.field.domain = "coin_per_amount_max";
        }
        var formData = JSON.stringify(data.field)
        console.info(formData);
        submit('/dict/save', formData);
        return false;
    });
});
