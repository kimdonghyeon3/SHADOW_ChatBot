package com.example.shadow.domain.docs;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DocsController {
    @RequestMapping("/shadow/doc")
    public String index(){
        return "docs/docs_index";
    }
    @RequestMapping("shadow/doc/tutorial")
    public String tutor(){
        return "docs/docs_tutorial";
    }
}
