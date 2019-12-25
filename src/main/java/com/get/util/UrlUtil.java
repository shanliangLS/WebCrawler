package com.get.util;

import java.net.MalformedURLException;
import java.net.URL;

public class UrlUtil {
    public static String join(String baseUrl, String hrefUrl) {
        try {
            URL url = new URL(new URL(baseUrl), hrefUrl);
            return url.toExternalForm();
        } catch (MalformedURLException e) {
            return null;
        }
    }
}
