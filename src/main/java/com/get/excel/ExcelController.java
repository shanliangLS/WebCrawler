package com.get.excel;

import com.get.crawl.domain.WebSiteCrawlPolicy;
import com.get.crawl.repository.WebSiteCrawlPolicyRepository;
import com.get.domain.WebSiteSubtype;
import com.get.domain.res.ExceptionMsg;
import com.get.excel.domain.ExcelData;
import com.get.excel.util.ExcelUtil;
import com.get.repository.WebSiteSubtypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/excel")
public class ExcelController {

    @Autowired
    private WebSiteSubtypeRepository subtypeRepository;

    @Autowired
    private WebSiteCrawlPolicyRepository policyRepository;

    @RequestMapping("/test/subtype")
    public void testSubtype(HttpServletResponse response) {
        try {
            int rowIndex = 0;
            List<WebSiteSubtype> subtypes = subtypeRepository.findAll();

            ExcelData data = new ExcelData();
            data.setName("subtype");
            List<String> titles = new ArrayList<>();
            titles.add("编号");
            titles.add("名称");
            titles.add("URL");
            titles.add("策略编号");
            data.setTitles(titles);

            List<List<Object>> rows = new ArrayList<>();
            for (WebSiteSubtype subtype : subtypes) {
                List<Object> row = new ArrayList<>();
                row.add(subtype.getId());
                row.add(subtype.getName());
                row.add(subtype.getUrl());
                row.add(subtype.getSiteId());
                rows.add(row);
            }
            data.setRows(rows);
            try {
                ExcelUtil.exportExcel(response, "subtype", data);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @RequestMapping("test/crawlPolicy")
    public void testCrawlPolicy(HttpServletResponse response) {
        try {
            int rowIndex = 0;
            List<WebSiteCrawlPolicy> policies = policyRepository.findAll();

            ExcelData data = new ExcelData();
            data.setName("crawlPolicy");
            List<String> titles = new ArrayList<>();
            titles.add("编号");
            titles.add("名称");
            titles.add("URL");
            titles.add("标题选择器");
            titles.add("标题选择器类型");
            titles.add("内容选择器");
            titles.add("内容选择器类型");
            titles.add("作者选择器");
            titles.add("作者选择器类型");
            titles.add("时间选择器");
            titles.add("时间选择器类型");
            titles.add("图片选择器");
            titles.add("下一页选择器");
            titles.add("下一页选择文本");
            titles.add("列表页下载方式");
            titles.add("文章页下载方式");
            data.setTitles(titles);

            List<List<Object>> rows = new ArrayList<>();
            for (WebSiteCrawlPolicy policy : policies) {
                List<Object> row = new ArrayList<>();
                row.add(policy.getId());
                row.add(policy.getName());
                row.add(policy.getUrl());
                row.add(policy.getTitleSelector());
                row.add(policy.getTitleSelectorType());
                row.add(policy.getContentSelector());
                row.add(policy.getContentSelectorType());
                row.add(policy.getAuthorSelector());
                row.add(policy.getAuthorSelectorType());
                row.add(policy.getTimeSelector());
                row.add(policy.getTimeSelectorType());
                row.add(policy.getPhotoCss());
                row.add(policy.getNextCss());
                row.add(policy.getNextText());
                row.add(policy.getListDownloadType());
                row.add(policy.getArticleDownloadType());
                rows.add(row);
            }
            data.setRows(rows);
            try {
                ExcelUtil.exportExcel(response, "crawlPolicy", data);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    @Resource
//    private UserInfoService userInfoService;

//    @RequestMapping("/test")
//    public RetResult<Integer> test() {
//        int rowIndex = 0;
//        List<UserInfo> list = userInfoService.selectAlla(0, 0);
//        ExcelData data = new ExcelData();
//        data.setName("hello");
//        List<String> titles = new ArrayList();
//        titles.add("ID");
//        titles.add("userName");
//        titles.add("password");
//        data.setTitles(titles);
//
//        List<List<Object>> rows = new ArrayList();
//        for (int i = 0, length = list.size(); i < length; i++) {
//            UserInfo userInfo = list.get(i);
//            List<Object> row = new ArrayList();
//            row.add(userInfo.getId());
//            row.add(userInfo.getUserName());
//            row.add(userInfo.getPassword());
//            rows.add(row);
//        }
//        data.setRows(rows);
//        try {
//            rowIndex = ExcelUtils.generateExcel(data, ExcelConstant.FILE_PATH + ExcelConstant.FILE_NAME);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return RetResponse.makeOKRsp(Integer.valueOf(rowIndex));
//    }
//
//    @RequestMapping("/test2")
//    public void test2(HttpServletResponse response) {
//        int rowIndex = 0;
//        List<UserInfo> list = userInfoService.selectAlla(0, 0);
//        ExcelData data = new ExcelData();
//        data.setName("hello");
//        List<String> titles = new ArrayList();
//        titles.add("ID");
//        titles.add("userName");
//        titles.add("password");
//        data.setTitles(titles);
//
//        List<List<Object>> rows = new ArrayList();
//        for (int i = 0, length = list.size(); i < length; i++) {
//            UserInfo userInfo = list.get(i);
//            List<Object> row = new ArrayList();
//            row.add(userInfo.getId());
//            row.add(userInfo.getUserName());
//            row.add(userInfo.getPassword());
//            rows.add(row);
//        }
//        data.setRows(rows);
//        try {
//            ExcelUtil.exportExcel(response, "test2", data);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}