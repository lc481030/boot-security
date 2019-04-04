function addFavorite(url, title) {
    // 浏览器用于 HTTP 请求的用户代理头的值，可判断浏览器种类
    var userAgent = navigator.userAgent.toLowerCase();
    if (userAgent.indexOf("360se") > -1) {
        console.log(1);
        alert("由于360浏览器功能限制，请按 Ctrl+D 手动收藏！");
    } else if (userAgent.indexOf("msie 8") > -1) {
        console.log(2);
        window.external.AddToFavoritesBar(url, title); // IE8
    } else if (document.all) { // 用于判断IE
        console.log(3);
        try {
            window.external.addFavorite(url, title);
        } catch (e) {
            alert('您的浏览器不支持,请按 Ctrl+D 手动收藏!');
        }
    } else if (window.sidebar) { // Firefox已经取消window.sidebar.addPanel 处理方法见后面
        // window.sidebar.addPanel(title, url, "");
        console.log(4);
    } else {
        console.log(5);
        alert('您的浏览器不支持,请按 Ctrl+D 手动收藏!');
    }
}

//保存到桌面
function toDesktop(sUrl,sName){
    try {
        var WshShell = new ActiveXObject("WScript.Shell");
        var oUrlLink =          WshShell.CreateShortcut(WshShell.SpecialFolders("Desktop")     + "\\" + sName + ".url");
        oUrlLink.TargetPath = sUrl;
        oUrlLink.Save();
    }
    catch(e)  {
        alert("当前IE安全级别不允许操作！");
    }
}


function goStoreList() {
    location.href = "/storeList.html";          //location.href实现客户端页面的跳转
}