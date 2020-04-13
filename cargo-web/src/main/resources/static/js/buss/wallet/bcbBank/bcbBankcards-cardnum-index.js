layui.use(['form', 'laydate'], function () {
    var laydate = layui.laydate;
    var curDate = formatDate(new Date);
    var startTime, endTime;

    // 手续费统计
    laydate.render({
        elem: '#startTime'
        , done: function (value, date) {
            if (value > curDate) {
                layer.msg("开始日期不能大于当前日期");
                $("#startTime").val("");
                return;
            } else if (value > endTime) {
                layer.msg("开始日期不能大于结束日期");
                $("#startTime").val("");
                return;
            }
            startTime = value;
        }
    });
    laydate.render({
        elem: '#endTime'
        , done: function (value, date) {
            if (value > curDate) {
                layer.msg("结束日期不能大于当前日期");
                $("#endTime").val("");
                return;
            } else if (value < startTime) {
                layer.msg("结束日期不能小于开始日期");
                $("#endTime").val("");
                return;
            }
            endTime = value;
        }
    });

});
loadTableWithoutColumns('/bcb/bankcard/cardnum/list', 'dataTable', 'searchForm');

//延时加载
setTimeout('rowTool()', 1000); //延迟1秒

function rowTool() {
    layui.use('table', function () {

        //监听行工具事件
        table.on('tool(dataTable)', function (obj) {
            if (obj.event === 'detail') {
                bcbBankApplyDetail("/bcb/bankcard/toDetail?id=" + obj.data.id);
            } else if (obj.event === 'add') {
                //alert('');
                add();
            } else if (obj.event === 'edit') {
                edit(obj.data.id);
            }

        });


    });
}

//新增或编辑
function add() {
    layer.open({
        type: 2,
        closeBtn: 1,
        offset: '50px',
        title: '<b>新增银行卡</b>',
        area: ['540px', '390px'], //宽高
        content: "/bcb/bankcard/toSave"
    });
}

function edit(id) {
    console.log(id);
    layer.open({
        type: 2,
        closeBtn: 1,
        offset: '50px',
        title: '<b>编辑银行卡</b>',
        area: ['540px', '390px'], //宽高
        content: "/bcb/bankcard/toEdit?id=" + id
    });
}

function cancelModify() {
    $("#flag").empty()
    this.layer.closeAll();
}


//BCB银行卡详情
function bcbBankApplyDetail(url) {
    var e = $(this),
        i = url,
        t = "BCB银行卡详情";
    layui.router();
    var l = parent === self ? layui : top.layui;
    l.index.openTabsPage(i, t || e.text())
}

//查看详情
function detail(url) {
    openModal("BCB银行卡详情", url, "860px", "730px");
}