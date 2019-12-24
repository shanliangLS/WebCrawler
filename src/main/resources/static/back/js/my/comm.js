//
// 基础函数
//
//XML OBJECT
function getXmlObject() {
    // 根据window.XMLHttpRequest对象是否存在使用不同的创建方式
    if (window.XMLHttpRequest) {
        // FireFox、Opera等浏览器支持的创建方式
        return new XMLHttpRequest();
    } else {
        // IE浏览器支持的创建方式
        return new ActiveXObject("Microsoft.XMLHTTP");
    }
}

//
// 程序
//
$(function () {
    locationUrl('/me', 'me');
});
var mainActiveId = 'home';
// 第一个页面
var firstUrl = null;
// 第二个页面
var secondUrl = null;
//
var flag = 1;
//
var xmlHttp = getXmlObject();

//
function locationUrl(url, activeId) {
    //每次跳转切换页面时，设置该页面page=1
    page = 1;
    //滚动条设置为页面顶部。不设置的话，假设第一个页面加载了10页，第二个页面切换过去后，由于滚动条在下边，会一直加载10页才停止
    window.scrollTo(0, 0);
    if (mainActiveId != null && mainActiveId != "" && activeId != null && activeId != "") {
        $("#" + mainActiveId).removeAttr("class");
        $("#" + activeId).attr("class", "active");
        mainActiveId = activeId;
    }
    goUrl(url, null);
}


function goUrl(url, params) {
    fixUrl(url, params);
    if (xmlHttp) {
        //var params = "";
        xmlHttp.open("GET", url, true);
        xmlHttp.onreadystatechange = handleServerResponse;
        xmlHttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=UTF-8');
        xmlHttp.send(params);
    }
}


function fixUrl(url, params) {
    if (params != null) {
        url = url + "?" + params;
    }
    if (firstUrl == null) {
        firstUrl = url;
    } else if (secondUrl == null) {
        secondUrl = url;
    } else {
        if (flag == 1) {
            firstUrl = url;
            flag = 2;
        } else {
            secondUrl = url;
            flag = 1;
        }
    }
}

function handleServerResponse() {
    if (xmlHttp.readyState == 4) {
        //document.getElementById("mainSection").innerHTML =xmlHttp.responseText;
        var text = xmlHttp.responseText;
        if (text.indexOf("<title>Favorites error Page</title>") >= 0) {
            window.location.href = "/error.html";
        } else {
            $("#content").html(xmlHttp.responseText);
        }
    }
}

