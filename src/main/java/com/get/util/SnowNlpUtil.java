package com.get.util;

import com.get.config.Global;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class SnowNlpUtil {
    public static String getZy(String ss) {
        try {
            String cmd = "python " + Global.snowNlpPath + "  " + ss;
//            System.out.println(cmd);
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
//            System.out.println(s);
            int left = s.indexOf("start**");
            if (left == -1) {
                return null;
            } else {
                left += 7;
            }
            int right = s.indexOf("**end", left);
            if (left + 5 >= right) {
                return null;
            }
            s = s.substring(left, right);
            return s;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
