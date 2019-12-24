package com.get.config;

import org.springframework.beans.factory.annotation.Value;

import java.io.File;

public class Global {
    @Value("${newPassword.url}")
    public static String NewPasswordUrl;

    @Value("${profilePictures.path}")
    private static String fileProfilePicturesPath;

    @Value("${static.path}")
    private static String staticPath;

    @Value("${backgroundPictures.path}")
    private static String fileBackgroundPicturesPath;


    public static final String userAgentJsonPath = "/home/dong/IdeaProjects/get/src/main/java/com/get/spider/util/useragent/fake_useragent.json";

    public static final String phantomJsPath = "/home/dong/youGet/phantomjs-2.1.1-linux-x86_64/bin/phantomjs";

    public static final String downHtmlJs = "/home/dong/IdeaProjects/get/src/main/java/com/get/spider/util/js/downHtml.js";

    public static final String downHtmlConfigJson = "/home/dong/IdeaProjects/get/src/main/java/com/get/spider/util/js/downHtmlConfigJson.json";

    public static final String downloadPath = "/home/dong/youGet/download/";


//    static {
//        File file = new File("");
//        projectPath = file.getAbsolutePath() + "/";
//        javaPath = projectPath + "src/main/java/";
//        userAgentJsonPath = javaPath + "com/get/util/useragent/fake_useragent.json";
//    }

//    public static void main(String[] args) {
//        System.out.println(projectPath);
//        System.out.println(javaPath);
//        System.out.println(userAgentJsonPath);
//    }
}
