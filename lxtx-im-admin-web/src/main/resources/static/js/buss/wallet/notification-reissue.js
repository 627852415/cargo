var current = 1;
var size = 10;
var coinId;
var userId;
var type;
var respData;
layui.use(['form', 'laydate', 'table'], function () {
    var laydate = layui.laydate;
    var curDate = formatDate(new Date);
    var createTime, updateTime;
    var table = layui.table;
    // [[#{text.time.selection.general.usage}]]
    laydate.render({
        elem: '#createTime'
        , done: function (value, date) {
            if (value > curDate) {
                layer.msg("[[#{text.start.trading.period.cannot.be.larger.than.current.period}]]");
                $("#createTime").val("");
                return;
            } else if (value > updateTime) {
                layer.msg("[[#{text.start.transaction.date.cannot.be.greater.than.end.transaction.date}]]");
                $("#createTime").val("");
                return;
            }
            createTime = value;
        }
    });
    laydate.render({
        elem: '#updateTime'
        , done: function (value, date) {
            if (value > curDate) {
                layer.msg("[[#{text.end.of.trading.period.cannot.be.larger.than.the.current.period}]]");
                $("#updateTime").val("");
                return;
            } else if (value < createTime) {
                layer.msg("[[#{text.the.end.of.the.trading.period.cannot.be.less.than.the.beginning.of.the.trading.period}]]");
                $("#updateTime").val("");
                return;
            }
            updateTime = value;
        }
    });

    //[[#{text.listen.for.line.tool.events}]]
    table.on('tool(dataTable)', function(obj){
        var data = obj.data;
        var tt = "[[#{text.are.you.sure.to.process.this.piece.of.data}]]";
        if(obj.event === 'reissueHandler'){
            layer.confirm(tt, function(index){
                reissueHandler(data);
            });
        }
    });

});

loadTableWithoutColumns('/notification/reissue/list/page', 'dataTable', 'searchForm');

function getStatusStr(status) {
    var statusStr = "";
    if (status == 0) {
        statusStr = "[[#{text.unsuccessful}]]";
    } else if (status == 1) {
        statusStr = "<font color='#ffd700'>[[#{text.processing.succeeded}]]</font>";
    }
    return statusStr;
}

function renderBtn(data) {
    if (data.dealFlag == 0) {
        var html = '';
        html += '<a href="javascript:;" lay-event="reissueHandler" class="layui-btn" style="line-height: 28px;">重发</a>';
        return html;
    } else {
        return "无";
    }
}

function reissueHandler(data){
    var tishi = layer.load(1, {shade: [0.8, '#393D49']})
    $.ajaxUkey({
        url : '/notification/reissue/handle',
        type : 'POST',
        data : {
            params : data.params,
            type: data.type,
            notificationId: data.id
        },
        dataType : 'json',
        success : function(data) {
            console.log(data);
            if (data.success) {
                if (typeof(data.data) == "object") {
                    layer.msg(data.data.message);
                } else {
                    layer.msg(data.data);
                }
                $(".layui-laypage-btn")[0].click();
            } else {
                layer.msg(data.msg);
            }
            //最后数据加载完 让 loading层消失
            layer.close(tishi);
        },
        error : function(e) {
            layer.msg('[[#{text.system.upgrade.and.maintenance,.please.try.again.later}]]');
            //最后数据加载完 让 loading层消失
            layer.close(tishi);
        }
    });

}
