package com.andyron.bcdlj.c14.c141;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;

public class JsoupTest {
    public static void main(String[] args) throws IOException {
//        Document doc = Jsoup.parse(new File("data/articles.html"), "UTF-8");

        // 直接连接URL进行分析
        String url = "https://www.cnblogs.com/swiftma/p/5631311.html";
        Document doc = Jsoup.connect(url).get();

        Elements elements = doc.select("#cnblogs_post_body p a");
        for (Element e : elements) {
            System.out.println(e.id() + " ==== " + e.text() + " ==== " + e.attr("href"));
        }
    }
}
