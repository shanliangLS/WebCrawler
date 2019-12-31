package com.get.word;

import com.get.config.Global;
import com.get.domain.Information;
import com.get.domain.res.AjaxResult;
import com.get.domain.res.ExceptionMsg;
import com.get.repository.InformationRepository;
import com.get.spider.util.UUIDUtil;
import com.get.util.JiebaUtil;
import com.get.util.SnowNlpUtil;
import com.get.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/word/")
@RestController
public class WordController extends BaseController {

    @Autowired
    private InformationRepository informationRepository;

    @RequestMapping("/getAbstract")
    public AjaxResult getAbstract(String infoIdListStr) {
        try {
            if (infoIdListStr == null || "".equals(infoIdListStr)) {
                return failAjax(ExceptionMsg.ParamError);
            }
            StringBuilder totalText = new StringBuilder();
            List<Map<String, Object>> mapList = new ArrayList<>();
            for (String infoIdStr : infoIdListStr.split(",")) {
                if (infoIdStr == null || "".equals(infoIdStr)) {
                    continue;
                }
                int infoId = Integer.parseInt(infoIdStr);
                Information information = informationRepository.findInformationById((long) infoId);
                if (information == null) {
                    continue;
                }
                Map<String, Object> map = new HashMap<>();
                String content = information.getContent();
                String title = information.getContent();
                String url = information.getUrl();
                if (content == null) {
                    content = "";
                }
                if (title == null) {
                    title = "";
                }
                if (url == null) {
                    url = "";
                }
                String zy = SnowNlpUtil.getZy(title + content);
                totalText.append(title).append(content);
                if (zy == null) {
                    zy = "未知";
                }
                map.put("title", information.getTitle());
                map.put("url", information.getUrl());
                map.put("author", information.getAuthor());
                map.put("zy", zy);
                mapList.add(map);
            }
            String totalZy = SnowNlpUtil.getZy(totalText.toString());
            Map<String, Object> hashMap = new HashMap<>();
            hashMap.put("myArticleList", mapList);
            hashMap.put("totalZy", totalZy);
            // 生成uuid
            String uuid = UUIDUtil.random();
            String fileOnlyName = uuid + ".doc";
            String filePath = Global.outPath + Global.cachePath;
            WordUtil.createWord(hashMap, "mb.ftl", filePath, fileOnlyName);
            return successAjax("/" + Global.cachePath + fileOnlyName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return errorAjax();
    }
}

