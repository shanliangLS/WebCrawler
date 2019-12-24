package com.get.cache.help;

import org.apache.tika.mime.MimeType;
import org.apache.tika.mime.MimeTypeException;
import org.apache.tika.mime.MimeTypes;

import java.util.HashMap;
import java.util.Map;

public class FileTypeHelp {

    private static final String[] mimeTypes = {
            "application/octet-stream", "application/x-001", "text/h323", "drawing/907", "audio/x-mei-aac", "audio/aiff", "audio/aiff", "text/asa", "text/asp", "audio/basic", "application/vnd.adobe.workflow", "application/x-bmp", "application/x-c4t", "application/x-cals", "application/x-netcdf", "application/x-cel", "application/x-g4", "application/x-cit", "text/xml", "application/x-cmx", "application/pkix-crl", "application/x-csi", "application/x-cut", "application/x-dbm", "text/xml", "application/x-x509-ca-cert", "application/x-dib", "application/msword", "application/x-drw", "Model/vnd.dwf", "application/x-dwg", "application/x-dxf", "application/x-emf", "text/xml", "application/x-ps", "application/x-ebx", "image/fax", "application/fractals", "application/x-frm", "application/x-gbr", "image/gif", "application/x-gp4", "application/x-hmr", "application/x-hpl", "application/x-hrf", "text/x-component", "text/html", "text/html", "image/x-icon", "application/x-iff", "application/x-igs", "application/x-img", "application/x-internet-signup", "java/*", "image/jpeg", "image/jpeg", "application/x-jpg", "text/html", "application/x-laplayer-reg", "audio/x-liquid-secure", "audio/x-la-lms", "application/x-ltr", "video/x-mpeg", "video/mpeg4", "application/x-troff-man", "application/msaccess", "application/x-shockwave-flash", "message/rfc822", "audio/mid", "application/x-mil", "audio/x-musicnet-download", "application/x-javascript", "audio/mp1", "video/mpeg", "video/mpeg4", "application/vnd.ms-project", "video/mpg", "audio/rn-mpeg", "video/x-mpeg", "video/mpg", "application/vnd.ms-project", "text/xml", "image/pnetvue", "message/rfc822", "application/x-out", "application/x-pkcs12", "application/pkcs7-mime", "application/x-pkcs7-certreqresp", "application/x-pc5", "application/x-pcl", "application/pdf", "application/vnd.adobe.pdx", "application/x-pgl", "application/vnd.ms-pki.pko", "text/html", "application/x-plt", "application/x-png", "application/vnd.ms-powerpoint", "application/vnd.ms-powerpoint", "application/x-ppt", "application/pics-rules", "application/x-prt", "application/postscript", "application/vnd.ms-powerpoint", "audio/vnd.rn-realaudio", "application/x-ras", "text/xml", "application/x-red", "application/vnd.rn-realsystem-rjs", "application/x-rlc", "application/vnd.rn-realmedia", "audio/mid", "audio/x-pn-realaudio", "application/vnd.rn-realmedia-secure", "application/vnd.rn-realsystem-rmx", "image/vnd.rn-realpix", "application/vnd.rn-rsml", "application/msword", "video/vnd.rn-realvideo", "application/x-sat", "application/x-sdw", "application/x-slb", "drawing/x-slk", "application/smil", "audio/basic", "text/plain", "application/futuresplash", "application/streamingmedia", "application/vnd.ms-pki.stl", "application/x-sty", "application/x-shockwave-flash", "application/x-tg4", "image/tiff", "image/tiff", "drawing/x-top", "text/xml", "application/x-icq", "text/x-vcard", "application/vnd.visio", "application/x-vpeg005", "application/x-vsd", "application/vnd.visio", "application/vnd.visio", "application/vnd.visio", "audio/wav", "application/x-wb1", "application/x-wb3", "application/msword", "application/x-wk4", "application/x-wks", "audio/x-ms-wma", "application/x-wmf", "video/x-ms-wmv", "application/x-ms-wmz", "application/x-wpd", "application/vnd.ms-wpl", "application/x-wr1", "application/x-wrk", "application/x-ws", "text/xml", "application/vnd.adobe.xdp", "application/vnd.adobe.xfd", "text/html", "application/x-xls", "text/xml", "text/xml", "text/xml", "text/xml", "application/x-xwd", "application/vnd.symbian.install", "application/x-x_t", "application/vnd.android.package-archive",
            "image/tiff", "application/x-301", "application/x-906", "application/x-a11", "application/postscript", "audio/aiff", "application/x-anv", "video/x-ms-asf", "video/x-ms-asf", "video/avi", "text/xml", "application/x-bot", "application/x-c90", "application/vnd.ms-pki.seccat", "application/x-cdr", "application/x-x509-ca-cert", "application/x-cgm", "java/*", "application/x-cmp", "application/x-cot", "application/x-x509-ca-cert", "text/css", "application/x-dbf", "application/x-dbx", "application/x-dcx", "application/x-dgn", "application/x-msdownload", "application/msword", "text/xml", "application/x-dwf", "application/x-dxb", "application/vnd.adobe.edn", "message/rfc822", "application/x-epi", "application/postscript", "application/x-msdownload", "application/vnd.fdf", "text/xml", "application/x-g4", "application/x-", "application/x-gl2", "application/x-hgl", "application/x-hpgl", "application/mac-binhex40", "application/hta", "text/html", "text/webviewhtml", "application/x-icb", "application/x-ico", "application/x-g4", "application/x-iphone", "application/x-internet-signup", "video/x-ivf", "image/jpeg", "application/x-jpe", "image/jpeg", "application/x-javascript", "audio/x-liquid-file", "application/x-latex", "application/x-lbm", "application/x-javascript", "video/x-mpeg", "audio/mpegurl", "application/x-mac", "text/xml", "application/x-mdb", "message/rfc822", "application/x-mi", "audio/mid", "text/xml", "audio/x-musicnet-stream", "video/x-sgi-movie", "audio/mp2", "audio/mp3", "video/x-mpg", "video/x-mpeg", "video/mpg", "application/vnd.ms-project", "application/vnd.ms-project", "video/mpeg", "application/vnd.ms-project", "application/x-mmxp", "application/x-nrf", "text/x-ms-odc", "application/pkcs10", "application/x-pkcs7-certificates", "application/pkcs7-mime", "application/pkcs7-signature", "application/x-pci", "application/x-pcx", "application/pdf", "application/x-pkcs12", "application/x-pic", "application/x-perl", "audio/scpls", "image/png", "application/vnd.ms-powerpoint", "application/x-ppm", "application/vnd.ms-powerpoint", "application/x-pr", "application/x-prn", "application/x-ps", "application/x-ptn", "text/vnd.rn-realtext3d", "audio/x-pn-realaudio", "application/rat-file", "application/vnd.rn-recording", "application/x-rgb", "application/vnd.rn-realsystem-rjt", "application/x-rle", "application/vnd.adobe.rmf", "application/vnd.rn-realsystem-rmj", "application/vnd.rn-rn_music_package", "application/vnd.rn-realmedia-vbr", "application/vnd.rn-realplayer", "audio/x-pn-realaudio-plugin", "text/vnd.rn-realtext", "application/x-rtf", "application/x-sam", "application/sdp", "application/x-stuffit", "application/x-sld", "application/smil", "application/x-smk", "text/plain", "application/x-pkcs7-certificates", "text/xml", "application/vnd.ms-pki.certstore", "text/html", "text/xml", "application/x-tdf", "application/x-tga", "application/x-tif", "text/xml", "application/x-bittorrent", "text/plain", "text/iuls", "application/x-vda", "text/xml", "application/vnd.visio", "application/vnd.visio", "application/x-vst", "application/vnd.visio", "text/xml", "audio/x-ms-wax", "application/x-wb2", "image/vnd.wap.wbmp", "application/x-wk3", "application/x-wkq", "video/x-ms-wm", "application/x-ms-wmd", "text/vnd.wap.wml", "video/x-ms-wmx", "application/x-wp6", "application/x-wpg", "application/x-wq1", "application/x-wri", "application/x-ws", "text/scriptlet", "video/x-ms-wvx", "text/xml", "application/vnd.adobe.xfdf", "application/vnd.ms-excel", "application/x-xlw", "audio/scpls", "text/xml", "text/xml", "text/xml", "application/x-x_b", "application/vnd.symbian.install", "application/vnd.iphone", "application/x-silverlight-app"
    };
    private static final String[] extensions = {
            ".*", ".001", ".323", ".907", ".acp", ".aif", ".aiff", ".asa", ".asp", ".au", ".awf", ".bmp", ".c4t", ".cal", ".cdf", ".cel", ".cg4", ".cit", ".cml", ".cmx", ".crl", ".csi", ".cut", ".dbm", ".dcd", ".der", ".dib", ".doc", ".drw", ".dwf", ".dwg", ".dxf", ".emf", ".ent", ".eps", ".etd", ".fax", ".fif", ".frm", ".gbr", ".gif", ".gp4", ".hmr", ".hpl", ".hrf", ".htc", ".html", ".htx", ".ico", ".iff", ".igs", ".img", ".isp", ".java", ".jpe", ".jpeg", ".jpg", ".jsp", ".lar", ".lavs", ".lmsff", ".ltr", ".m2v", ".m4e", ".man", ".mdb", ".mfp", ".mhtml", ".mid", ".mil", ".mnd", ".mocha", ".mp1", ".mp2v", ".mp4", ".mpd", ".mpeg", ".mpga", ".mps", ".mpv", ".mpw", ".mtx", ".net", ".nws", ".out", ".p12", ".p7c", ".p7r", ".pc5", ".pcl", ".pdf", ".pdx", ".pgl", ".pko", ".plg", ".plt", ".png", ".ppa", ".pps", ".ppt", ".prf", ".prt", ".ps", ".pwz", ".ra", ".ras", ".rdf", ".red", ".rjs", ".rlc", ".rm", ".rmi", ".rmm", ".rms", ".rmx", ".rp", ".rsml", ".rtf", ".rv", ".sat", ".sdw", ".slb", ".slk", ".smil", ".snd", ".sor", ".spl", ".ssm", ".stl", ".sty", ".swf", ".tg4", ".tif", ".tiff", ".top", ".tsd", ".uin", ".vcf", ".vdx", ".vpg", ".vsd", ".vst", ".vsw", ".vtx", ".wav", ".wb1", ".wb3", ".wiz", ".wk4", ".wks", ".wma", ".wmf", ".wmv", ".wmz", ".wpd", ".wpl", ".wr1", ".wrk", ".ws2", ".wsdl", ".xdp", ".xfd", ".xhtml", ".xls", ".xml", ".xq", ".xquery", ".xsl", ".xwd", ".sis", ".x_t", ".apk",
            ".tif", ".301", ".906", ".a11", ".ai", ".aifc", ".anv", ".asf", ".asx", ".avi", ".biz", ".bot", ".c90", ".cat", ".cdr", ".cer", ".cgm", ".class", ".cmp", ".cot", ".crt", ".css", ".dbf", ".dbx", ".dcx", ".dgn", ".dll", ".dot", ".dtd", ".dwf", ".dxb", ".edn", ".eml", ".epi", ".eps", ".exe", ".fdf", ".fo", ".g4", ".", ".gl2", ".hgl", ".hpg", ".hqx", ".hta", ".htm", ".htt", ".icb", ".ico", ".ig4", ".iii", ".ins", ".IVF", ".jfif", ".jpe", ".jpg", ".js", ".la1", ".latex", ".lbm", ".ls", ".m1v", ".m3u", ".mac", ".math", ".mdb", ".mht", ".mi", ".midi", ".mml", ".mns", ".movie", ".mp2", ".mp3", ".mpa", ".mpe", ".mpg", ".mpp", ".mpt", ".mpv2", ".mpx", ".mxp", ".nrf", ".odc", ".p10", ".p7b", ".p7m", ".p7s", ".pci", ".pcx", ".pdf", ".pfx", ".pic", ".pl", ".pls", ".png", ".pot", ".ppm", ".ppt", ".pr", ".prn", ".ps", ".ptn", ".r3t", ".ram", ".rat", ".rec", ".rgb", ".rjt", ".rle", ".rmf", ".rmj", ".rmp", ".rmvb", ".rnx", ".rpm", ".rt", ".rtf", ".sam", ".sdp", ".sit", ".sld", ".smi", ".smk", ".sol", ".spc", ".spp", ".sst", ".stm", ".svg", ".tdf", ".tga", ".tif", ".tld", ".torrent", ".txt", ".uls", ".vda", ".vml", ".vsd", ".vss", ".vst", ".vsx", ".vxml", ".wax", ".wb2", ".wbmp", ".wk3", ".wkq", ".wm", ".wmd", ".wml", ".wmx", ".wp6", ".wpg", ".wq1", ".wri", ".ws", ".wsc", ".wvx", ".xdr", ".xfdf", ".xls", ".xlw", ".xpl", ".xql", ".xsd", ".xslt", ".x_b", ".sisx", ".ipa", ".xap"
    };

    private static Map<String, String> head2FileTypeMap = new HashMap<String, String>();

    private static void initHead2FileTypeMap() {
        // 图片
        head2FileTypeMap.put("FFD8FF", "jpg");
        head2FileTypeMap.put("89504E47", "png");
        head2FileTypeMap.put("47494638 ", "gif");
        head2FileTypeMap.put("49492A00", "tif");
        head2FileTypeMap.put("424D", "bmp");
        head2FileTypeMap.put("38425053 ", "psd");
        head2FileTypeMap.put("41433130", "dwg");

        // 文本
        head2FileTypeMap.put("3C3F786D6C", "xml");
        head2FileTypeMap.put("68746D6C3E ", "html");
        head2FileTypeMap.put("7B5C727466", "rtf");
        head2FileTypeMap.put("255044462D312E", "pdf");

        // 压缩文件
        head2FileTypeMap.put("52617221", "rar");
        head2FileTypeMap.put("1F8B08", "gz");

        // 视频
        head2FileTypeMap.put("41564920", "avi");
        head2FileTypeMap.put("00000020667479706D70", "mp4");
        head2FileTypeMap.put("464C5601050000000900", "flv");
        head2FileTypeMap.put("2E524D46000000120001", "rmvb");
        head2FileTypeMap.put("000001BA", "mpg");
        head2FileTypeMap.put("6D6F6F76", "mov");

        // 音频
        head2FileTypeMap.put("57415645", "wav");
        head2FileTypeMap.put("49443303000000002176", "mp3");

        // email
        head2FileTypeMap.put("44656C69766572792D646174", "eml");

        // 运行文件
        head2FileTypeMap.put("4D5A9000030000000400", "exe");

        // 程序
        head2FileTypeMap.put("48544D4C207B0D0A0942", "css");
        head2FileTypeMap.put("696B2E71623D696B2E71", "js");
        head2FileTypeMap.put("3C2540207061676520", "jsp");
        head2FileTypeMap.put("7061636B61676520", "java");
        head2FileTypeMap.put("CAFEBABE0000002E00", "class");

        head2FileTypeMap.put("504B03040A000000", "jar");
        head2FileTypeMap.put("406563686F206f66660D", "bat");
        head2FileTypeMap.put("494E5345525420494E54", "sql");
        head2FileTypeMap.put("49545346030000006000", "chm");
        head2FileTypeMap.put("235468697320636F6E66", "ini");

        // outlook  Express dbx
        head2FileTypeMap.put("CFAD12FEC5FD746F", "dbx");
        // Outlook pst
        head2FileTypeMap.put("2142444E", "pst");
        // Microsoft Access (mdb)
        head2FileTypeMap.put("5374616E64617264204A", "mdb");
        // torrent
        head2FileTypeMap.put("6431303A637265617465", "torrent");
    }

    static {
        initHead2FileTypeMap();
    }

    /**
     * 将mimeType转化为文件后缀名
     *
     * @param type
     * @return
     */
    private static String getExtByMimeType(String type) {
        for (int i = 0; i < mimeTypes.length; i++) {
            if (type.contains(mimeTypes[i])) {
                return extensions[i];
            }
        }
        try {
            MimeTypes allTypes = MimeTypes.getDefaultMimeTypes();
            MimeType thisType = allTypes.forName(type);
            return thisType.getExtension();
        } catch (MimeTypeException e) {
            return null;
        }

    }

    /**
     * 由disposition获取后缀名
     *
     * @param disposition
     * @return
     */
    private static String getExtByDisposition(String disposition) {
        try {
            if (disposition == null || disposition.length() < 11) {
                return null;
            }
            int ldx = disposition.indexOf("filename=\"");
            if (ldx == -1) {
                return null;
            }
            ldx += 10;
            int rdx = disposition.indexOf("\"", ldx);
            if (rdx == -1) {
                return null;
            }
            String s = disposition.substring(ldx, rdx);
            int ddx = s.indexOf(".");
            if (ddx == -1) {
                return "";
            }
            return s.substring(ddx + 1);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 由url得到扩展名
     *
     * @param url
     * @return
     */
    private static String getExtByUrl(String url) {
        try {
            if (url == null) {
                return null;
            }
            String lowUrl = url.toLowerCase();
            int ldx = lowUrl.lastIndexOf("/");
            String s1 = lowUrl.substring(ldx + 1);
            int rdx = s1.indexOf("?");
            if (rdx != -1) {
                s1 = s1.substring(0, rdx);
            }
            for (String ext : extensions) {
                if (s1.endsWith(ext)) {
                    return ext.substring(1);
                }
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 通过文件头判断扩展名
     *
     * @param bytes
     * @param len
     * @return
     */
    private static String getExtByBytes(byte[] bytes, final int len) {

        String head = bytesToHexString(bytes, len);
        System.out.println(head);
        for (String key : head2FileTypeMap.keySet()) {
            if (head.contains(key)) {
                return head2FileTypeMap.get(key);
            }
        }
        return null;
    }

    /**
     * bytes转字符串
     *
     * @param bs
     * @param len
     * @return
     */
    private static String bytesToHexString(byte[] bs, final int len) {
        if (bs == null || len <= 0) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            int v = bs[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                sb.append(0);
            }
            sb.append(hv);
        }
        return sb.toString().toUpperCase();
    }

    /**
     * 综合判断文件类型
     *
     * @param url
     * @param contentType
     * @param disposition
     * @param bytes
     * @param len
     * @return
     */
    public static String getExtByAll(String url, String contentType, String disposition, byte[] bytes, int len) {
        String ext = getExtByMimeType(contentType);

        if (ext != null && ext.length() > 1) {
            // 去除.
            return ext.substring(1);
        }
        ext = getExtByDisposition(disposition);
        if (ext != null) {
            // 可能为""
            return ext;
        }
        ext = getExtByBytes(bytes, len);
        if (ext != null) {
            System.out.println(contentType);
            System.out.println(ext);
            return ext;
        }
        ext = getExtByUrl(url);
        if (ext != null) {
            return ext;
        }
        return "";
    }

    public static void main(String[] args) {
        // c s d n photo
        final String url1 = "https://img-blog.csdn.net/20180926152943613?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM5NjI5Mjc3/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70";
        // bai du photo
        final String url2 = "https://www.baidu.com/img/bd_logo1.png?qua=high";
        //
        final String url3 = "https://www.cnblogs.com/CB-red/p/8780180.html";

        final String url4 = "https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Errors/Unexpected_type";

        final String url5 = "http://www.fairysoftware.com/content_type.html";
        System.out.println(getExtByUrl(url1));
        System.out.println(getExtByUrl(url2));
        System.out.println(getExtByUrl(url3));
        //        System.out.println(getExtByDisposition("form-data; name=\"attachment\"; filename=\"201407%28%E5%85%A8%E9%87%8F%29.zip\""));
    }
}
