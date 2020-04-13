function getTitleId(card, title) {
    var id = -1;
    $(window.parent.document).find(".layui-tab[lay-filter=" + card + "] ul li").each(function () {
        if (title === $(this).find('span').text()) {
            id = $(this).attr('lay-id');
        }
    });
    return id;
}

window.getTitleId = getTitleId;

function hasTagCardId(card, id) {
    var result = false;
    $(window.parent.document).find(".layui-tab[lay-filter='" + card + "'] ul li").each(function () {
        if (id === $(this).attr('lay-id')) {
            result = true;
        }
    });
    return result;
}

window.hasTagCardId = hasTagCardId;

/**
 * 删除当前选项卡
 */
function tabDeleteCurrent() {
    var cur_ray_id = $(window.parent.document).find(".layadmin-tabsbody-item.layui-show").children().attr("src");
    layui.router();
    var l = parent === self ? layui : top.layui;
    l.element.tabDelete('layadmin-layout-tabs', cur_ray_id);
}
window.tabDeleteCurrent = tabDeleteCurrent;

/**
 * 自定义跳转选项卡
 * @param cardId 选项卡iframe src
 */
function tabChangeDef(cardId) {
    layui.router();
    var l = parent === self ? layui : top.layui;
    l.element.tabChange('layadmin-layout-tabs', cardId);
    $(window.parent.document).find("li > a[layadmin-event='refresh']")[0].click();
}
window.tabChangeDef = tabChangeDef;

/**
 * 跳转/切换选项卡 - 币种主页
 */
function tabChangeToCoinIndex() {
    layui.router();
    var l = parent === self ? layui : top.layui;
    l.element.tabChange('layadmin-layout-tabs', "/coin/index");
    l.element.autoRefresh = true;
    tabRefresh();
    location.reload();
    $(window.parent.document).find("li > a[layadmin-event='refresh']")[0].click();
    // $(window.parent.document).find(".layui-laypage-refresh").click();
}
window.tabChangeToCoinIndex = tabChangeToCoinIndex;

/**
 * 选项卡刷新
 */
function tabRefresh() {
    var src = $(window.parent.document).find(".layadmin-tabsbody-item.layui-show").children().attr("src");
    $(window.parent.document).find(".layadmin-tabsbody-item.layui-show").children().attr("src", src);
}
window.tabRefresh = tabRefresh;

/**
 * 将表单数据转为json对象
 */
$.fn.serializeJson = function() {
    var serializeObj = {};
    var array = this.serializeArray();
    $(array).each(function() {
        if (serializeObj[this.name]) {
            if ($.isArray(serializeObj[this.name])) {
                serializeObj[this.name].push(this.value);
            } else {
                serializeObj[this.name] = [ serializeObj[this.name], this.value ];
            }
        } else {
            serializeObj[this.name] = this.value;
        }
    });
    return serializeObj;
};

/**
 * 删除
 * @param url 请求地址
 * @param jsonData 参数
 * @param offset 弹出框的位置（"100px")
 */
function  delData(url, jsonData, offset) {
    layer.confirm('您确定要删除吗？', {icon: 3, offset: offset}, function (index) {
        $.ajaxUkey({
            url : url,
            type : 'POST',
            data : jsonData,
            dataType : 'json',
            contentType : 'application/json;charset=UTF-8',
            success : function(data) {
                if (data.success) {
                    var message = data.data;
                    if($.isEmptyObject(message)){
                        message = "操作成功";
                    }
                    layer.msg(message,
                        {
                            offset : '15px',
                            time : 1000
                        },
                        function() {
                            layer.close(index);
                            $(".layui-laypage-btn")[0].click();
                        });
                } else {
                    layer.msg(data.msg);
                }
            },
            error : function(e) {
                //最后数据加载完 让 loading层消失
                layer.msg('系统升级维护中，请稍后重试');
            }
        });
    });
}
var form, layer, laydate;
layui.use(['form', 'layer', 'laydate' ], function () {
    layer = layui.layer, laydate = layui.laydate, form=layui.form;
})

/**
 * 打开弹出框
 * @param title 弹出框标题
 * @param url 跳转地址
 * @param width 弹出框宽度
 * @param height 弹出框高度
 */
function  openModal(title, url, width, height) {
    layer.open({
        type: 2,
        closeBtn: 1,
        title: '<b>' + title +'</b>',
        area: [width, height], //宽高
        //content: $("#modifyData")});//TODO 修改为打开新页面
        content: url
    });
}

/**
 * 打开弹出框【无滚动条】
 * @param title 弹出框标题
 * @param url 跳转地址
 * @param width 弹出框宽度
 * @param height 弹出框高度
 */
function  openModalWithinScroll(title, url, width, height) {
    layer.open({
        type: 2,
        closeBtn: 1,
        title: '<b>' + title +'</b>',
        area: [width, height], //宽高
        //content: $("#modifyData")});//TODO 修改为打开新页面
        content: [url,'no']
    });
}

/**
 * 打开弹出框
 * @param title 弹出框标题
 * @param url 跳转地址
 * @param width 弹出框宽度
 * @param height 弹出框高度
 * @param offset 弹出框的位置
 */
function  openModalWithOffet(title, url, width, height, offset) {
    layer.open({
        type: 2,
        closeBtn: 1,
        offset : offset,
        title: '<b>' + title +'</b>',
        area: [width, height], //宽高
        //content: $("#modifyData")});//TODO 修改为打开新页面
        content: url
    });
}

/**
 * 打开新选项卡
 * @param title 选项卡标题
 * @param url 跳转地址
 */
function  openTab(title, url) {
    var e = $(this),
        t = title,
        i = url;
    layui.router();
    var l = parent === self ? layui : top.layui;
    l.index.openTabsPage(i, t || e.text())
}

/**
 * 提交
 * @param url 提交地址
 * @param data  提交数据
 * @param redirectUrl 选项卡提交时，关闭后要跳转的地址（弹出框时，该参数不要传参）
 * @param isChild 是否要跳转到父页，如果不需要，不传，如果是刷新当前页，则传入
 */
function submit(url, data, redirectUrl, isChild) {
    $.ajaxUkey({//异步请求返回给后台
        url : url,
        type : 'POST',
        contentType : 'application/json;charset=UTF-8',
        data : data,
        dataType : 'json',
        success : function(data) {
            if (data.success) {
                var message = data.data;
                if($.isEmptyObject(message)){
                    message = "操作成功";
                }
                layer.alert(message, {title: '提示信息', icon: 6}, function (index) {
                    if(redirectUrl){
                        // 刷新并切换tab回列表
                        tabChangeDef(redirectUrl);
                        tabDeleteCurrent();
                        location.reload();
                    }else{
                        parent.layer.closeAll();
                        if(!isChild) {
                            parent.window.location.reload();
                        } else {
                            window.location.reload();
                        }
                    }

                });

            } else {
                layer.msg(data.msg);
            }
        },
        error : function(e) {
            //最后数据加载完 让 loading层消失
            layer.msg('系统升级维护中，请稍后重试');
        }
    });
    return false;
}


var table;
/**
 * 列表加载
 * @param eid   表格ID
 * @param url  请求地址
 * @param columns 列表字段
 */
function loadTable(eid, url, columns, sortFild, sortOrder) {
    layui.use('table', function (){
        table = layui.table;
        table.render({
            elem: '#' + eid,
            url: url,
            method: 'post',
            toolbar: '#toolbarData',
            // defaultToolbar: ['filter', 'print', 'exports'],//筛选、打印、导出
            defaultToolbar: ['filter', 'exports'],
            request:{
                pageName:'current',
                limitName:'size'
            },
            parseData : function(res) {
                var msg,code = "1",records,total=0;
                if(!res.success || $.isEmptyObject(res.data)){
                    if(res.msg){
                        msg = '<b style="color: red">' + res.msg + '</b>';
                    }else{
                        msg = '<b style="color: red">未查询到相关数据</b>';
                    }
                }else{
                    records = res.data.records;
                    if(records !=undefined &&  records.length > 0){
                        code = '0';
                        total = res.data.total;
                    }else{
                        msg = '<b style="color: red">未查询到相关数据</b>';
                    }
                }
                return {
                    "code" : code,
                    "msg" : msg,
                    "count" : total,
                    "data" : records
                }
            },
            cols: columns,
            id: 'dataReload' ,
            page: true
        })
    });
}

/**
 * 列表加载,列写到html中
 * @param url
 * @param filter
 * @param hideFieldId 需要隐藏的控件
 * @param fid 搜索条件的formID
 */
function loadTableWithoutColumns(url, filter, fid,hideField){
    if(fid == undefined){
        fid = "searchForm";
    }
    layui.use('table', function (){
        table = layui.table;
        table.init(filter, {
            method: 'post'
            , url: url
            ,request:{
                pageName:'current',
                limitName:'size'
            }
            ,parseData : function(res) {
                var msg,code = "1",records, total=0;
                if(!res.success || $.isEmptyObject(res.data)){
                    if(res.msg){
                        msg = '<b style="color: red">' + res.msg + '</b>';
                        if(hideField != undefined){
                            $(hideField).show();
                        }
                    }else{
                        msg = '<b style="color: red">未查询到相关数据</b>';
                        if(hideField != undefined){
                            $(hideField).hide();
                        }

                    }

                }else{
                    records = res.data.records;
                    if(records !=undefined &&  records.length > 0){
                        code = '0';
                        total = res.data.total;
                        if(hideField != undefined){
                            $(hideField).show();
                        }
                    }else{
                        msg = '<b style="color: red">未查询到相关数据</b>';
                        if(hideField != undefined){
                            $(hideField).hide();
                        }
                    }
                }
                return {
                    "code" : code,
                    "msg" : msg,
                    "count" : total,
                    "data" : records
                }
            }
            , where: $("#" + fid).serializeJson()

        });
    });
}

/**
 * 搜索
 * @param fid formIID
 * @param tid lay-data id: 'dataReload'
 * @param  isNotPage 如果不分页，则需要传参数，否则不传
 */
function  search(fid, tid, isNotPage) {
    var dataReloadId = "dataReload";
    if(tid){
        dataReloadId = tid;
    }
    //执行重载
    if(!isNotPage){
        table.reload(dataReloadId, {
            page: {
                curr: 1 //重新从第 1 页开始
            }
            , where: $("#" + fid).serializeJson()
        });
    }else{
        table.reload(dataReloadId, {
            where: $("#" + fid).serializeJson()
        });
    }
}

/**
 * 初始化时间插件
 * @param dateId
 * @param hasMin 是否设置最小时间
 */
function initializeDatetime(dateId, hasMin){
    layui.use('laydate', function() {
        var laydate = layui.laydate;
        if(hasMin){
            laydate.render({
                min: getDateAfter(1),
                elem: '#' + dateId
                ,type: 'datetime'
                ,btns: ['clear', 'confirm']
            });
        }else{
            laydate.render({
                elem: '#' + dateId
                ,type: 'datetime'
                ,btns: ['clear', 'confirm']
            });
        }
    });
}




var tipOption = {
    //是否允许多个tips 类型：Boolean，默认：false 允许多个意味着不会销毁之前的tips层。通过tipsMore: true开启
    tipsMore: true
};

//传入id,提示弹出提示
function alertTip(id, tip) {
    $(id).on('click', function () {
        layer.tips(tip, id, tipOption);
    });
}

//移除readOnly属性
function removeReadOnly(id) {
    $(id).removeAttr("readOnly");
}

/**
 * 将科学计算法转为字符串
 * @param num
 * @returns {string}
 */
function toNonExponential(num) {
    var m = num.toExponential().match(/\d(?:\.(\d*))?e([+-]\d+)/);
    return num.toFixed(Math.max(0, (m[1] || '').length - m[2]));
}

/**
 * 发送ajax POSt请求，没有询问提示，场景如：按钮开关
 * @param url
 * @param jsonData
 */
function sendAjaxPost(url, jsonData) {
    $.ajaxUkey({
        url : url,
        type : 'POST',
        data : jsonData,
        dataType : 'json',
        contentType : 'application/json;charset=UTF-8',
        success : function(data) {
            if (data.success) {
                var message = data.data;
                if($.isEmptyObject(message)){
                    message = "操作成功";
                }
                layer.msg(message,
                    {
                        offset : '15px',
                        time : 1000
                    },
                    function() {
                        $(".layui-laypage-btn")[0].click();
                    });
            } else {
                layer.msg(data.msg);
            }
        },
        error : function(e) {
            //最后数据加载完 让 loading层消失
            layer.msg('系统升级维护中，请稍后重试');
        }
    });

}

//初始化国家简码选择框
function initCountryCode(countryCodeId){
    $.ajax({
        url: '/globalCode/listPage',
        type: 'POST',
        data: {current: 1, size: 10000},
        dataType: 'json',
        async: false,
        success: function (data) {
            if (data.success) {
                if (undefined != data && undefined != data.data && undefined != data.data.records) {
                    var html = '';
                    $.each(data.data.records, function (i, item) {
                        html +='<option value="' + item.countryCode + '">' + item.countryCode + '/' + item.phoneCode + '</option>';
                    })
                    // console.log(html)
                    countryCodeId.append(html);
                }
            }
        }
    });
}