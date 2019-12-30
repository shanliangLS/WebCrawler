package com.get.config;

import java.io.File;

public class Global {

    public static final boolean isLinux = System.getProperty("os.name").contains("Linux");

    public static final String outPath = isLinux ? "/home/dong/youGet/" : "D:\\youGet\\";
    // 缓存路径
    public static final String cachePath = isLinux ? "cache/" : "cache\\";

    // 用户头像路径
    public static final String profilePicturesPath = isLinux ? "profilePictures/" : "profilePictures\\";

    // 背景图像路径
    public static final String backgroundPicturesPath = isLinux ? "backgroundPictures/" : "backgroundPictures\\";

    // phantomJs路径
    public static final String phantomJsPath = isLinux ? "/home/dong/youGet/phantomjs-2.1.1-linux-x86_64/bin/phantomjs" : "D:\\youGet\\phantomjs-2.1.1-windows\\bin\\phantomjs.exe";

    public static final String newsCnnPyPath = "/home/dong/untitled3/news_cnn.py";

    // 项目路径
    public static final String downHtmlJs;

    public static final String downHtmlConfigJson;

    private static final String projectPath;

    private static final String javaPath;

    public static final String userAgentJsonPath;

    private static final String FILE_SEPARATOR;

    public static final String jieBaPyPath;

    public static final String rcPyPath;

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
        rcPyPath = getRealFilePath(javaPath + "com/get/util/rc.py");
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
        System.out.println();
    }
}
