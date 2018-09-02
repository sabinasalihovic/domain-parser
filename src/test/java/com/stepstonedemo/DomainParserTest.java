package com.stepstonedemo;

import com.google.common.collect.ImmutableMap;
import com.stepstonedemo.util.DomainParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * Created by sabinak on 2.9.2018.
 */
public class DomainParserTest {

    @Test
    public void testNonExistingUrl() {

        Map<String, Long> domainFromURL = DomainParser.extractDomainFromURL("http://someunexistingurl.com");
        assertNull(domainFromURL);
    }

    @Test
    public void testPageWithoutLink() {
        Map<String, Long> expectedResult = ImmutableMap.of();
        Map<String, Long> domainFromURL = DomainParser.extractDomains(
                    createDocumentFromResource("src/test/resources/test1.html"));
        assertEquals(expectedResult, domainFromURL);
    }

    @Test
    public void testParsedPageWithLinks() {
        Map<String, Long> expectedResult = ImmutableMap.of("onet.pl", 2l, "nbp.pl", 1l);
        Map<String, Long> domainFromURL = DomainParser.extractDomains(
                    createDocumentFromResource("src/test/resources/test2.html"));
        assertEquals(expectedResult, domainFromURL);
    }

    private Document createDocumentFromResource(  String pathname)  {
        try {
            return Jsoup.parse(new File(pathname), "UTF-8", "http://testurl.com");
        } catch (IOException e) {
            e.printStackTrace();
            return  null;
        }
    }
}
