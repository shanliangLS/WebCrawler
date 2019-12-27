package com.get.cache.help;

import com.get.cache.domain.WebPage;
import com.get.config.Global;
import com.get.spider.util.UserAgentUtil;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.UUID;

import static com.get.cache.help.FileTypeHelp.getExtByAll;


public class DownloadHelp {

    private static final String cachePath = Global.outPath + Global.cachePath;

    /**
     * 根据urlStr下载静态文件
     *
     * @param urlStr url
     * @return 信息
     */
    public static WebPage down(String urlStr) {
        Long downloadTime = timeNow();
        String uuid = uuidRandom();
        String fileId = downloadTime + "_" + uuid;

        WebPage page = new WebPage();
        page.setUrl(urlStr);
        page.setDownloadTime(downloadTime);
        page.setDownloadCount(1);

        URL url;
        try {
            url = new URL(urlStr);
        } catch (MalformedURLException e) {
            page.setSuccess(false);
            page.setReason(e.getMessage());
            return page;
        }
        try {
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            // 设置timeout
            con.setConnectTimeout(1000 * 5);
            // 设置
            con.setRequestProperty("User-Agent", UserAgentUtil.random());

            System.out.println(urlStr);
            InputStream is = con.getInputStream();

            String contentType = con.getContentType();
            String contentDisposition = con.getHeaderField("Content-Disposition");
            // 测试读取
            byte[] ts = new byte[32];
            int length = is.read(ts);

            String fileExt = getExtByAll(urlStr, contentType, contentDisposition, ts, length);
            String fileName;
            if ("".equals(fileExt)) {
                fileName = fileId;
            } else {
                fileName = fileId + "." + fileExt;
            }
            String filePath = cachePath + fileName;

            byte[] bs = new byte[1024 * 8];

            OutputStream os = new FileOutputStream(filePath);
            os.write(ts, 0, length);
            while ((length = is.read(bs)) != -1) {
                os.write(bs, 0, length);
            }
            is.close();
            os.close();

            page.setSuccess(true);
            page.setFileExtension(fileExt);
            page.setFileName(fileName);
            page.setFilePath(filePath);
            return page;
        } catch (Exception e) {
            e.printStackTrace();
            page.setSuccess(false);
            page.setReason(e.getMessage());
            return page;
        }
    }

    private static Long timeNow() {
        return System.currentTimeMillis();
    }

    private static String uuidRandom() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}

