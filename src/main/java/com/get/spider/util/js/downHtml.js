try {
    var args = require('system').args;
    var fs = require('fs');
// 设置编码utf8
    phantom.outputEncoding = 'utf8';
// 初始化
    var page = new WebPage();
// 设置userAgent
    page.settings.userAgent = 'Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2228.0 Safari/537.36';
// 设置不加载图片
    page.settings.loadImages = false;

    if (1 == args.length) {
        console.log('Url address is required');
        phantom.exit();
    }

    var urlAddress = args[1].toLowerCase();

// console.log(urlAddress);
// 不加载css
    page.onResourceRequested = function (requestData, request) {
        // if (mapDict.hasOwnProperty(requestData['url'])) {
        //     console.log(requestData['url']);
        //     var nextUrl = 'file://' + mapDict[requestData['url']];
        //     console.log(nextUrl);
        //     request.changeUrl(nextUrl);
        // }
        // // downloadUrls.push(requestData['url']);
        // 原始请求重定向
        if (requestData['url'].indexOf('http://localhost') == -1) {
            var urlStr = 'http://localhost:8888/cacheByUrl?url=' + encodeURIComponent(requestData['url']) + "&htmlUrl=" + encodeURIComponent(urlAddress);
            request.changeUrl(urlStr);
            // if ((/http:\/\/.+?\.css/gi).test(requestData['url']) || requestData.headers['Content-Type'].indexOf('text/css') != -1) {
            // console.log('The url of the request is matching. Aborting: ' + requestData['url']);
            // var res = postAjax(requestData['url']);
            // console.log(res);
            // request.abort();
            // request.changeUrl(urlStr);
            // }
            // if ((/http:\/\/.+?\.js/gi).test(requestData['url']) || requestData.headers['Content-Type'].indexOf('text/javascript') != -1) {
            // console.log('The url of the request is matching. Aborting: ' + requestData['url']);
            // var res = postAjax(requestData['url']);
            // console.log(res);
            // request.changeUrl(urlStr);
            // }
        }
    };


    var ext = '.png';

    // var clipping = false;

    var viewports = [
        {
            width: 1024,
            height: 768
        }
    ];


    page.open(urlAddress, function (status) {
        if ('success' !== status) {
            console.log('Unable to load the url address!');
            console.log(urlAddress);
        } else {
            // var folder = urlToDir(urlAddress);
            var output, key;

            function render() {
                key = 1 - 1;
                //page.viewportSize = viewports[key];
               // var fileName = urlAddress.replace(/[^a-z0-9]+/gi, "_") + ext;
                //output = 'D:/youGet/photoSave/' + fileName;
                //page.render(output);
                // var myDict = {};
                // myDict['img'] = output;
                // myDict['content'] = page.content;
                // console.log("JsonStart***" + JSON.stringify(myDict) + "***JsonEnd");

                // console.log(JSON.stringify(downloadUrls));
                console.log('JsonStart***' + page.content + '***JsonEnd');
            }

            render();
        }
        phantom.exit();
    });

// 得到文件名
//     function getFileName(viewport) {
//         var d = new Date();
//         var date = [
//             d.getUTCFullYear(),
//             d.getUTCMonth() + 1,
//             d.getUTCDate()
//         ];
//         var time = [
//             d.getHours() <= 9 ? '0' + d.getHours() : d.getHours(),
//             d.getMinutes() <= 9 ? '0' + d.getMinutes() : d.getMinutes(),
//             d.getSeconds() <= 9 ? '0' + d.getSeconds() : d.getSeconds(),
//             d.getMilliseconds()
//         ];
//         var resolution = viewport.width + (clipping ? "x" + viewport.height : '');
//
//         return date.join('-') + '_' + time.join('-') + "_" + resolution + ext;
//     }


// 得到目录
//     function urlToDir(url) {
//         var dir = url
//             .replace(/^(http|https):\/\//, '')
//             .replace(/\/$/, '');
//
//         if (!fs.makeTree(dir)) {
//             console.log('"' + dir + '" is NOT created.');
//             phantom.exit();
//         }
//         return dir;
//     }
} catch (err) {
    console.log("");
}