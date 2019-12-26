package com.get.util;

import com.get.config.Global;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class JiebaUtil {

    public static String getKeyWords(String ss) {
        try {
            String cmd = "python " + Global.jieBaPyPath + "  " + ss;
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


    public static void main(String[] args) {
//        String content = "孩子上了幼儿园 安全防拐教育要做好";
//        int topN = 5;
//        TFIDFAnalyzer tfidfAnalyzer = new TFIDFAnalyzer();
//        List<Keyword> list = tfidfAnalyzer.analyze(content, topN);
//        for (Keyword word : list)
//            System.out.println(word.getName() + ":" + word.getTfidfvalue() + ",");
//        // 防拐:0.1992,幼儿园:0.1434,做好:0.1065,教育:0.0946,安全:0.0924
    }
}
