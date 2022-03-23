package com.alan.demo;

import java.util.*;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.*;
import org.jsoup.select.Elements;

public class KeelungSightsCrawler {
    public Sight[] getItems(String targetZone) {
        targetZone += "區";
        List<String> urlList = getURLfromZone(targetZone); // get all Url from the target zone
        List<Sight> sightList = getSight(urlList, targetZone); // get sight[] from all Urls we've got
        Sight[] arr = new Sight[sightList.size()];

        sightList.toArray(arr);
        for (int i = 0; i < arr.length; ++i) {
            System.out.println(arr[i]);
        }
        return arr;
    }

    public List<Sight> getSight(List<String> urlList, String zone) {
        List<Sight> sightList = new ArrayList<>();
        for (String url : urlList) {
            try {
                Sight curSight = new Sight();
                Document d = Jsoup.connect("https://www.travelking.com.tw" + url).get();

                curSight.sightName = d.select("h1.h1").text();
                curSight.Zone = zone;
                curSight.Category = d.select("span[property='rdfs:label']").text();
                Elements elements = d.select("div.g_s").select("img");
                for (Element ele : elements) {
                    curSight.photoList.add(ele.absUrl("data-src"));
                }
                curSight.Description = d.select("div.text[property='dc:description']").text();
                curSight.Address = d.select("span[property='vcard:street-address']").text();
                sightList.add(curSight);
            } catch (IOException e) {
                System.out.println(url);
                e.printStackTrace();
            }
        }
        return sightList;
    }

    public List<String> getURLfromZone(String targetZone) {
        Document d;
        List<String> urlList = new ArrayList<>();
        try {
            d = Jsoup.connect("https://www.travelking.com.tw/tourguide/taiwan/keelungcity/").timeout(6000).get();
            Elements zones = d.select("div.box").select("h4");

            for (Element ele : zones) {
                String zone = ele.text();
                if (zone.equals(targetZone)) {
                    Elements targetLinks = ele.nextElementSiblings().get(0).select("a");
                    for (Element Link : targetLinks) {
                        urlList.add(Link.attr("href"));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return urlList;
    }
}
