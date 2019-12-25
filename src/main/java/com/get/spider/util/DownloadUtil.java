package com.get.spider.util;


import com.get.config.Global;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class DownloadUtil {

    public static String downRunHtml(String urlStr) {
        try {
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
            int left = s.indexOf("JsonStart***");
            if (left == -1) {
                return null;
            } else {
                left += 12;
            }
            int right = s.indexOf("***JsonEnd", left);
            s = s.substring(left, right);
            return s;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        String d = downRunHtml("http://www.cas.ac.cn/sygz/201912/t20191220_4728612.shtml");
        System.out.println(d);
    }
}
