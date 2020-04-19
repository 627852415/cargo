
function getPath(){
    //获取当前网址，如： http://localhost:8080/test/Pages/Basic/Person.jsp
    var curWwwPath = window.document.location.href;
    //获取主机地址之后的目录，如： /test/Pages/Basic/Person.jsp
    var pathName = window.document.location.pathname;
    var pos = curWwwPath.indexOf(pathName);
    //获取主机地址，如： http://localhost:8080
    var localhostPath = curWwwPath.substring(0, pos);
    //获取带"/"的项目名，如：/test, 该项目没项目名称，所以不需要
    //var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
    return localhostPath;
}
document.write('<meta charset="utf-8">');
document.write('<meta name="renderer" content="webkit">');
document.write('<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">');
document.write('<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">');
document.write(' <link type="text/css" rel="stylesheet" href="' + getPath() + '/layui/css/layui.css"/>');
document.write(' <link type="text/css" rel="stylesheet" href="' + getPath() + '/css/common.css"/>');
document.write('<script type="text/javascript" src="' + getPath() + '/js/jquery.js"></script>');
document.write(' <script type="text/javascript" src="' + getPath() + '/js/ukey.js"></script>');
document.write('<script type="text/javascript" src="' + getPath() + '/js/sha256.js"></script>');
document.write('<script type="text/javascript" src="' + getPath() + '/layui/layui.js"></script>');
document.write('<script type="text/javascript" src="' + getPath() + '/js/date.js"></script>');
document.write(' <script type="text/javascript" src="' + getPath() + '/js/layui.define.js"></script>');
document.write('<script type="text/javascript" src="' + getPath() + '/js/common.js"></script>');
document.write('<script type="text/javascript" src="' + getPath() + '/js/ajax.js"></script>');
document.write('<script type="text/javascript" src="' + getPath() +'/formSelects/js/formSelects-v4.js"></script>');
document.write('<link type="text/css" rel="stylesheet" href="' + getPath() +'/formSelects/css/formSelects-v4.css"/>');
