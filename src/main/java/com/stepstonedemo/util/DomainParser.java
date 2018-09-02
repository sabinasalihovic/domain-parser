package com.stepstonedemo.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by sabinasalihovic on 2.9.2018.
 */
public class DomainParser {


    public static Map<String, Long> extractDomainFromURL(String url)  {
        Document document = null;
        try {
            document = Jsoup.connect(url).get();
        } catch (IOException e) {
            return  null;
        }
        return extractDomains(document);
    }

    public static Map<String, Long> extractDomains(Document document) {

        Elements linkElements = document.select("a[href]");
        if (linkElements == null) return Collections.<String, Long>emptyMap();

        List<String> urls = linkElements.eachAttr("abs:href");
        if (urls == null || urls.isEmpty()) return Collections.<String, Long>emptyMap();


        return urls.stream()
                .map(s -> extractPattern(s))
                .filter(s -> s != null)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }

    private static String extractPattern( String url) {
        try {

            URI uri = new URI(url);
            return uri.getHost();

        } catch (URISyntaxException e) {
            return null;
        }
    }
}
