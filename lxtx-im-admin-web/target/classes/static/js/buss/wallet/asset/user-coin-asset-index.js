layui.use(['form', 'laydate'], function() {
    var laydate = layui.laydate;
    var form = layui.form;
    var curDate = formatDate(new Date);
    var startTime,endTime;
    var updateTime,updateEndTime;
    laydate.render({
        elem: '#startTime'
        , done: function (value, date) {
            if (value > curDate) {
                layer.msg("[[#{text.start.date.cannot.be.greater.than.the.current.date}]]");
                $("#startTime").val("");
                return;
            } else if (value > endTime) {
                layer.msg("[[#{text.start.date.cannot.be.greater.than.end.date}]]");
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
                layer.msg("[[#{text.end.date.cannot.be.greater.than.the.current.date}]]");
                $("#endTime").val("");
                return;
            } else if (value < startTime) {
                layer.msg("[[#{text.end.date.cannot.be.less.than.start.date}]]");
                $("#endTime").val("");
                return;
            }
            endTime = value;
        }
    });

    form.on('select(typeFilter)', function (data) {
        var type = data.value;
        switch (type) {
            case "1":{
                $("#subDiv").css("display", "block");
                $("#subtype").empty();
                $("#subtype").append('<option value="">[[#{text.please.select.a.subtype}]]</option>');
                $("#subtype").append('<option value="11">[[#{text.transfer.within.the.station}]]</option>');
                $("#subtype").append('<option value="12">[[#{text.withdraw.in.the.station}]]</option>');
                $("#subtype").append('<option value="13">[[#{text.cash.withdrawal}]]</option>');
                $("#subtype").append('<option value="14">[[#{text.collecting.funds.to.merchants}]]</option>');
                $("#subtype").append('<option value="15">OTC[[#{text.withdrawal}]]</option>');
                $("#subtype").append('<option value="16">[[#{text.platform.withdrawal}]]</option>');
                $("#subtype").append('<option value="17">[[#{text.flash}]]</option>');
                $("#subtype").append('<option value="18">[[#{text.scan.code.payment}]]</option>');
                $("#subtype").append('<option value="19">[[#{text.yu.e.bao.transfer.out}]]</option>');
                $("#subtype").append('<option value="20">[[#{text.yu.e.bao.transfer.fee}]]</option>');
                $("#subtype").append('<option value="46">BCB[[#{text.application.form.fee}]]</option>');
                $("#subtype").append('<option value="45">BCB[[#{text.bank.transfer}]]</option>');
                form.render('select');
                break;
            }
            case "2":{
                $("#subDiv").css("display", "block");
                $("#subtype").empty();
                $("#subtype").append('<option value="">[[#{text.please.select.a.subtype}]]</option>');
                $("#subtype").append('<option value="21">[[#{text.recharge.inside.the.station}]]</option>');
                $("#subtype").append('<option value="22">[[#{text.recharge.outside.the.station}]]</option>');
                $("#subtype").append('<option value="23">OTC[[#{text.recharge}]]</option>');
                $("#subtype").append('<option value="24">[[#{text.collection.code.receipt}]]</option>');
                $("#subtype").append('<option value="25">[[#{text.yu.e.bao.transfer}]]</option>');
                $("#subtype").append('<option value="26">BCB[[#{text.bank.transfer}]]</option>');
                form.render('select');
                break;
            }
            case "6":{
                $("#subDiv").css("display", "block");
                $("#subtype").empty();
                $("#subtype").append('<option value="">[[#{text.please.select.a.subtype}]]</option>');
                $("#subtype").append('<option value="31">[[#{text.niu.niu}]]</option>');
                $("#subtype").append('<option value="32">21[[#{text.point.game}]]</option>');
                $("#subtype").append('<option value="33">[[#{text.game.sharing}]]</option>');
                $("#subtype").append('<option value="34">[[#{text.minesweeper.game}]]</option>');
                form.render('select');
                break;
            }
            case "7":{
                $("#subDiv").css("display", "block");
                $("#subtype").empty();
                $("#subtype").append('<option value="61">[[#{text.merchant.consumption}]]</option>');
                form.render('select');
                break;
            }
            case "8":{
                $("#subDiv").css("display", "block");
                $("#subtype").empty();
                $("#subtype").append('<option value="62">[[#{text.merchant.collection}]]</option>');
                form.render('select');
                break;
            }
            case "5":{
                $("#subDiv").css("display", "block");
                $("#subtype").empty();
                $("#subtype").append('<option value="">[[#{text.please.select.a.subtype}]]</option>');
                $("#subtype").append('<option value="41">[[#{text.red.envelopes}]]</option>');
                $("#subtype").append('<option value="42">[[#{text.red.envelope}]]</option>');
                form.render('select');
                break;
            }
            case "9":{
                $("#subDiv").css("display", "block");
                $("#subtype").empty();
                $("#subtype").append('<option value="">[[#{text.please.select.a.subtype}]]</option>');
                $("#subtype").append('<option value="63">[[#{text.merchant.margin.deduction}]]</option>');
                $("#subtype").append('<option value="64">[[#{text.merchant.margin.refund}]]</option>');
                $("#subtype").append('<option value="67">[[#{text.merchants.post.deductions.for.conversion.freeze.amount}]]</option>');
                $("#subtype").append('<option value="68">[[#{text.merchants.issue.refund.of.exchange.freeze.amount}]]</option>');
                $("#subtype").append('<option value="65">[[#{text.buyers.exchange.funds.to.the.account}]]</option>');
                $("#subtype").append('<option value="66">[[#{text.merchant.exchange.and.rebate}]]</option>');
                form.render('select');
                break;
            }
            default:{
                $("#subtype").empty();
                form.render('select');
                $("#subDiv").css("display", "none");
                break;
            }

        }
    });
});
loadTableWithoutColumns('/user/coin/asset/list/page','dataTable', 'searchForm');

//如果资产大于0 显示黑色，小于0红色
function setStyle(obj) {
    if(obj > 0){
        return "<b style='color: black'>" + obj +"</b>";
    }
    if(obj < 0){
        return "<b style='color: red'>" + obj +"</b>";
    }
    return obj;
}

//[[#{text.lazy.loading}]]
setTimeout('rowTool()', 1000); //延迟1秒

function rowTool(){
    layui.use('table', function (){
        //[[#{text.listen.for.line.tool.events}]]
        table.on('tool(dataTable)', function(obj){
            if(obj.event === 'detail'){
                detail("/user/coin/asset/detail?id=" + obj.data.id);
            }
        });
    });
}

//保存，编辑
function detail(url){
    openModal("[[#{text.see.details}]]", url, "1000px", "420px");
}

//[[#{text.application.list.file.export}]]
function download() {
    var url = "/user/coin/asset/download/list?" + $("#searchForm").serialize();
    $.ajaxUkey({
        url:url,
        type:"get",
        async:"true",
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        success:function(responseText){
            if(responseText.success == false){
                layer.alert(responseText.msg, { title: '[[#{text.information}]]', icon: 2 });
            }else{
                window.location.href = url;
            }

        },
        error:function(){
            layer.alert("[[#{text.sys.op.fail}]]", { title: '[[#{text.information}]]', icon: 2 });
        }
    });
}
