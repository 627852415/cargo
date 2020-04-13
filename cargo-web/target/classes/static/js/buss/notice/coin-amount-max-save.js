layui.use('form', function () {
    var form = layui.form;
    //监听提交
    form.on('submit(sub-btn)', function (data) {
        if ($.isEmptyObject(data.field.domain)) {
            data.field.domain = "coin_per_amount_max";
        }
        var formData = JSON.stringify(data.field)
        submit('/dict/save', formData);
        return false;
    });

    //自定义验证规则
    form.verify({
        coinName: function (value) {
            if (!/^[A-Z]+$/.test(value)) {
                return '币种名称必须是英文大写！';
            }
        }
    });

});
