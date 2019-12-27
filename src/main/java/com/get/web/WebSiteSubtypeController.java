package com.get.web;

import com.get.domain.WebSiteSubtype;
import com.get.domain.res.AjaxResult;
import com.get.repository.WebSiteSubtypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/subtype/")
public class WebSiteSubtypeController extends BaseController {

    @Autowired
    private WebSiteSubtypeRepository subtypeRepository;

    @RequestMapping("/add")
    public AjaxResult add(WebSiteSubtype subtype) {
        try {
            subtypeRepository.save(subtype);
            return successAjax();
        } catch (Exception e) {
            e.printStackTrace();
            return errorAjax();
        }
    }

    @RequestMapping("/all")
    public AjaxResult all() {
        try {
            List<WebSiteSubtype> subtypeList = subtypeRepository.findAll();
            return AjaxResult.success(subtypeList);
        } catch (Exception e) {
            e.printStackTrace();
            return errorAjax();
        }
    }

    @RequestMapping("/update")
    public AjaxResult update(WebSiteSubtype subtype) {
        try {
            subtypeRepository.save(subtype);
            return successAjax();
        } catch (Exception e) {
            e.printStackTrace();
            return errorAjax();
        }
    }
}
