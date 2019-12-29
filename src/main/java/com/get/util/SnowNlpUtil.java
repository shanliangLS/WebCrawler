package com.get.util;

import com.get.config.Global;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SnowNlpUtil {
    public static String getZy(String ss) {
        try {

//            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
//
//            Matcher m = p.matcher(ss);
//
//            ss = m.replaceAll("");
            ss=ss.replaceAll("\\s*|\t|\r|\n|　", "");

            String sss="感染。　　JEV属于黄病毒科（Flaviviridae）黄病毒属（";
            System.out.println(sss.replaceAll("\\s*|\t|\r|\n|[JEV]", ""));

            String cmd = "python " + Global.snowNlpPath + " " + ss;
            System.out.println(cmd);
            Runtime rt = Runtime.getRuntime();
            Process pc = rt.exec(cmd);
            InputStream is = pc.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb = new StringBuilder();
            String tmp = null;
            while ((tmp = br.readLine()) != null) {
                sb.append(tmp);
            }
            String s = sb.toString();
//            System.out.println(s);
            int left = s.indexOf("start**");
            if (left == -1) {
                return null;
            } else {
                left += 7;
            }
            int right = s.indexOf("**end", left);
            if (left + 5 >= right) {
                return null;
            }
            s = s.substring(left, right);
            return s;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        String out = getZy("　近日，国际学术期刊Antimicrobial Agents and Chemotherapy(《抗微生物制剂与化学治疗》)在线发表了中国科学院武汉病毒研究所/生物安全大科学研究中心肖庚富、王薇团队的最新研究成果，论文题为Screening of Natural Extracts for Inhibitors against Japanese Encephalitis Virus Infection（《抗乙型脑炎病毒药物的筛选及鉴定》）。该研究发现钠钾泵抑制剂乌本苷和地高辛能有效抑制日本乙型脑炎病毒（Japanese Encephalitis Virus, JEV）感染。\n" +
                "\n" +
                "　　JEV属于黄病毒科（Flaviviridae）黄病毒属（Flavivirus），通过蚊虫叮咬传播、引起严重病毒性脑炎。JEV流行区域包含27个国家和地区，波及人口超过30亿。我国是乙型脑炎的高发区，其诱发的乙型脑炎致死、致残率高，对人类的健康产生重大威胁。虽然JEV疫苗的使用在一定程度上减少乙型脑炎的发病率，但目前仍缺乏针对JEV的特异性抗病毒药物，相关药物的研发是目前亟待解决的科学问题。\n" +
                "\n" +
                "　　肖庚富、王薇课题组的研究人员通过对药物单体库进行筛选，发现钠钾泵抑制剂乌本苷和地高辛在细胞水平上显著抑制JEV感染。在此基础上，通过对不同感染时间段进行考察，确定乌本苷和地高辛均作用于病毒的复制阶段。同时，研究人员在动物水平对乌本苷潜在的抗病毒效果进行评估，发现乌本苷显著地降低感染JEV小鼠的致死率。研究结果揭示乌本苷通过抑制JEV在小鼠中枢神经系统中的复制增殖，减轻JEV对脑组织的病理损伤，发挥抗病毒效果。该研究为今后研发JEV抗病毒药物提供了重要的理论参考。\n" +
                "\n" +
                "　　武汉病毒所博士生郭娇为该论文第一作者，研究员肖庚富和副研究员王薇为共同通讯作者。该工作得到国家重点研发计划（2018YFA0507204）、国家自然科学基金资助（31670165）。");
        long endTime = System.currentTimeMillis();

        System.out.printf("消耗时间为:%d毫秒\n", (endTime - startTime));
        System.out.printf("识别的摘要为:%s\n", out);
    }
}
