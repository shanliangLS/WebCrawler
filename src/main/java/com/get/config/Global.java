package com.get.config;

import java.io.File;

public class Global {

    public static final String outPath = "D:\\youGet\\";
    // 缓存路径
    public static final String cachePath = "cache\\";

    // 用户头像路径
    public static final String profilePicturesPath = "profilePictures\\";

    // 背景图像路径
    public static final String backgroundPicturesPath = "backgroundPictures\\";

    // phantomJs路径
    public static final String phantomJsPath = "D:\\youGet\\phantomjs-2.1.1-windows\\bin\\phantomjs.exe";

    // 项目路径
    public static final String downHtmlJs;

    public static final String downHtmlConfigJson;

    private static final String projectPath;

    private static final String javaPath;

    public static final String userAgentJsonPath;

    private static final String FILE_SEPARATOR;

    public static final String jieBaPyPath;

    public static final String snowNlpPath;

    static {
        FILE_SEPARATOR = System.getProperty("file.separator");
        File file = new File("");
        projectPath = getRealFilePath(file.getAbsolutePath() + "/");
        javaPath = getRealFilePath(projectPath + "src/main/java/");
        userAgentJsonPath = getRealFilePath(javaPath + "com/get/spider/util/useragent/fake_useragent.json");
        downHtmlConfigJson = getRealFilePath(javaPath + "com/get/spider/util/js/downHtmlConfigJson.json");
        downHtmlJs = getRealFilePath(javaPath + "com/get/spider/util/js/downHtml.js");
        jieBaPyPath = getRealFilePath(javaPath + "com/get/util/jiebaKeywords.py");
        snowNlpPath = getRealFilePath(javaPath + "com/get/util/snowNlpZy.py");
    }


    private static String getRealFilePath(String path) {
        return path.replace("/", FILE_SEPARATOR).replace("\\", FILE_SEPARATOR);
    }


    public static void main(String[] args) {
        System.out.println(projectPath);
        System.out.println(javaPath);
        System.out.println(userAgentJsonPath);
        System.out.println(downHtmlJs);
        System.out.println(FILE_SEPARATOR);
    }
}
