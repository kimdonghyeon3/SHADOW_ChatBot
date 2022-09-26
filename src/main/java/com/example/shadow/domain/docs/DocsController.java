package com.example.shadow.domain.docs;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DocsController {
    @RequestMapping("/docs")
    public String index(Model model){
        model.addAttribute("pageTitle", "Documentation");
        return "docs/docs_index";
    }
    @RequestMapping("/docs/tutorial")
    public String tutor(Model model){
        model.addAttribute("pageTitle", "Tutorial");
        return "docs/docs_tutorial";
    }
}
