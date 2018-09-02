package com.stepstonedemo;

import com.stepstonedemo.util.DomainParser;

import java.util.Map;

/**
 * Created by sabinak on 2.9.2018.
 */
public class Demo {

    public static void main(String args[])  {

            Map<String, Long> stringLongMap = DomainParser.extractDomainFromURL("http://wawalove.pl ");
            stringLongMap.forEach((k,v)->System.out.println(k + " - " + v));
    }
}
