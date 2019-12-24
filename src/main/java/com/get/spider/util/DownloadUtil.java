package com.get.spider.util;


import com.get.config.Global;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class DownloadUtil {

    public static void downRunHtml(String urlStr) throws IOException {
        String cmd = Global.phantomJsPath + " --config=" + Global.downHtmlConfigJson + "  " + Global.downHtmlJs + " " + urlStr;
        Runtime rt = Runtime.getRuntime();
        Process pc = rt.exec(cmd);
        InputStream is = pc.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String tmp = null;
        while ((tmp = br.readLine()) != null) {
            sb.append(tmp);
        }
        String s = sb.toString();
        int left = s.indexOf("JsonStart***") + 12;
        int right = s.indexOf("***JsonEnd", left);
        s = s.substring(left, right);
        System.out.println(s);
    }

    public static void main(String[] args) {
        try {
            downRunHtml("https://cloud.tencent.com/developer/ask/205813");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
