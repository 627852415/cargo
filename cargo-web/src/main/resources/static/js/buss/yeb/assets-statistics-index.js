layui.use(['form', 'laydate'], function () {
    var laydate = layui.laydate;
    var curDate = formatDate(new Date);
    var startTime,endTime;
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

    laydate.render({
        elem: '#redoDateTimes'
        , done: function (value, date) {
            if (value > curDate) {
                layer.msg("日期不能大于当前日期");
                $("#redoDateTimes").val("");
                return;
            }
        }
    });

});
loadTableWithoutColumns('/yeb/assets/statistics/page','dataTable', 'searchForm');

layui.use('table', function (){
    //监听行工具事件
    var table = layui.table;
    table.on('tool(dataTable)', function(obj){
      if(obj.event === 'download'){
            var url="/yeb/assets/statistics/down?id="+obj.data.id;
            $.fileDownload(url,{
                httpMethod: 'get',
                prepareCallback:function(url){
                    console.log("Excel准备中...");
                },
                successCallback:function(url){
                    console.log('导出Excel');
                },
                failCallback: function (html, url) {
                    console.log(html);
                    layer.msg("系统找不到下载文件!");
                }
            });
      }
    });
});


function reDoData(){
    if($("#redoDateTimes").val()==''){
        layer.alert('请选择生成快照日期', { title: '提示信息', icon: 2 });
        return ;
    }
    var redoDateTimes = $("#redoDateTimes").val();
    var url = "/yeb/assets/statistics/create?recordDate=" + redoDateTimes;
    var startDates = new Date().getTime();
    $.ajaxUkey({
        url:url,
        type:"post",
        contentType:"application/json;charset=utf-8",
        beforeSend:function(){
            //敲黑板
            _index = layer.load(1, {
                content: "<div style='margin-left:-23px;padding-top:44px;width:120px;color:#FFF;'>数据生成中</div>",
                shade: [0.5, '#000']
            });
            var end = new Date().getTime();
            var stns = end+","+startDates;
            _lp_baseTime = Math.abs(new Date(end).valueOf() - new Date(startDates).valueOf()) / 3600000 * stns.split(',').length * 150;
            _lp_startTime = new Date().valueOf();

            setTimeout(function () { updateLoadProgress(); return; }, 60);
        },
        success:function(responseText){
            _lp_baseTime = -1; $("#loadProgress").html("100");layer.close(_index);
            if(responseText.success == false){
                layer.alert(responseText.msg, { title: '提示信息', icon: 2 });
            }else{
                layer.alert("快照生成完成!");
            }
        },
        error:function(){
            _lp_baseTime = -1;
            layer.close(_index);
            layer.alert("系统异常，导出失败", { title: '提示信息', icon: 2 });
        }
    });
}

var _index;
var _lp_baseTime = 0;
var _lp_startTime = 0;
function updateLoadProgress() {
    if (_lp_baseTime < 0) {
        layer.close(_index);
        return;
    }
    var dval = parseInt(new Date().valueOf())- parseInt(_lp_startTime);
    var timeDifference = (dval / _lp_baseTime).toFixed(2);
    var lp = timeDifference < 1 ? timeDifference * 100 : 99;
    $("#loadProgress").html(parseInt(lp));
    setTimeout(function () { updateLoadProgress(); return; }, 650);
}