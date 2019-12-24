package com.get.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.FileInputStream;

@Controller
public class SrcController extends BaseController {

    @Value("${static.path}")
    private String staticPath;

    @Value("${profilePictures.path}")
    private String fileProfilePicturesPath;

    @Value("${backgroundPictures.path}")
    private String fileBackgroundPicturesPath;

    /**
     * 返回用户头像
     *
     * @param name
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/profilePictures/{name}", produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] locateProfilePicture(@PathVariable("name") String name) {
        String filepath = staticPath + fileProfilePicturesPath + name;
        logger.info("请求头像:" + filepath);
        try {
            File file = new File(filepath);
            if (!file.exists()) {
                logger.info("请求图像不存在:" + name);
                return null;
            }
            FileInputStream inputStream = new FileInputStream(file);
            byte[] bytes = new byte[inputStream.available()];
            inputStream.read(bytes, 0, inputStream.available());
            inputStream.close();
            return bytes;
        } catch (Exception e) {
            logger.error("返回用户头像失败");
        }
        return null;
    }

    /**
     * 返回用户背景图像
     *
     * @param name
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/backgroundPictures/{name}", produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] locateBackgroundPicture(@PathVariable("name") String name) {
        String filepath = staticPath + fileBackgroundPicturesPath + name;
        logger.info("请求背景:" + filepath);
        try {
            File file = new File(filepath);
            if (!file.exists()) {
                logger.info("请求背景图片不存在:" + name);
                return null;
            }
            FileInputStream inputStream = new FileInputStream(file);
            byte[] bytes = new byte[inputStream.available()];
            inputStream.read(bytes, 0, inputStream.available());
            inputStream.close();
            return bytes;
        } catch (Exception e) {
            logger.error("返回用户背景图片失败");
        }
        return null;
    }
}
