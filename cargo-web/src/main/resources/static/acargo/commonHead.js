
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

document.write(' <link type="text/css" rel="stylesheet" href="' + getPath() + '/acargo/nav/css/font-awesome.css"/>');
document.write(' <link type="text/css" rel="stylesheet" href="' + getPath() + '/acargo/nav/css/style.css"/>');

document.write('<script type="text/javascript" src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>');
document.write(' <script type="text/javascript" src="' + getPath() + '/acargo/nav/js/script.js"></script>');


