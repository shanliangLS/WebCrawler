package com.get.spider.util;


import com.get.config.Global;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class UserAgentUtil {

    // gson
    private static final Gson gson = new Gson();

    // 浏览器列表
    private static String[] browsers = {"chrome", "opera", "firefox", "internetexplorer", "safari"};

    // 解析json数据
    private static HashMap<String, ArrayList<String>> userAgentMap;

    static {
        String userAgentJson;
        try {
            userAgentJson = FileUtils.readFileToString(new File(Global.userAgentJsonPath), "utf8");
        } catch (IOException e) {
            userAgentJson = "";
        }
        userAgentMap = gson.fromJson(
                userAgentJson,
                new TypeToken<HashMap<String, ArrayList<String>>>() {
                }.getType());
    }


    /**
     * 返回随机userAgent
     *
     * @return
     */
    public static String random() {
        Random rnd = new Random();
        String browser = browsers[rnd.nextInt(browsers.length)];
        ArrayList<String> userAgentList = userAgentMap.get(browser);
        return userAgentList.get(rnd.nextInt(userAgentList.size()));
    }

    public static void main(String[] args) {
        System.out.println(random());
    }


}
