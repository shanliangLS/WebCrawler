//package com.get.crawl.util;
//
//import com.get.spider.entity.enums.HtmlFieldEnum;
//import org.jsoup.nodes.Document;
//import org.jsoup.nodes.Element;
//
//public class JsoupUtil {
//    /**
//     * 使用css选择器,得到相应内容(内容唯一)
//     *
//     * @param doc
//     * @param css
//     * @param type
//     * @return
//     */
//    public static String cssGetOne(Document doc, String css, HtmlFieldEnum type) {
//        if (doc == null) {
//            return null;
//        }
//        Element element = doc.select(css).first();
//        return attrGet(element, type);
//    }
//
//    /**
//     * 得到属性
//     *
//     * @param element
//     * @param type
//     * @return
//     */
//    public static String attrGet(Element element, HtmlFieldEnum type) {
//        if (element != null) {
//            if (type == HtmlFieldEnum.TYPE_TEXT) {
//                // 返回文本
//                return element.text();
//            } else if (type == HtmlFieldEnum.TYPE_LINK) {
//                // 绝对地址
//                return element.attr("abs:href");
//            } else if (type == HtmlFieldEnum.TYPE_IMAGE) {
//                // 绝对地址
//                return element.attr("abs:src");
//            }
//        }
//        return null;
//    }
//
//    public static void main(String[] args) {
//
//    }
//}
