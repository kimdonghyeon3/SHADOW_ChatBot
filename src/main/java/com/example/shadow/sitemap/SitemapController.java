package com.example.shadow.sitemap;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class SitemapController {
    private String DOMAIN = "https://shadows.site";

    @GetMapping(value = "/sitemap.xml")
    @ResponseBody
    public XmlUrlSet main() {
        XmlUrlSet xmlUrlSet = new XmlUrlSet();

        create(xmlUrlSet, "/", XmlUrl.Priority.HIGH);
        create(xmlUrlSet, "/main", XmlUrl.Priority.HIGH);
        create(xmlUrlSet, "/signup", XmlUrl.Priority.Low);
        create(xmlUrlSet, "/login", XmlUrl.Priority.Low);

        return xmlUrlSet;
    }

    private void create(XmlUrlSet xmlUrlSet, String link, XmlUrl.Priority priority) {
        xmlUrlSet.addUrl(new XmlUrl(DOMAIN + link, priority));
    }
}
