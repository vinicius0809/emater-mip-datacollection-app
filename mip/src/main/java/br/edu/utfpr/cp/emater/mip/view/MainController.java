package br.edu.utfpr.cp.emater.mip.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping ("/")
    public String view() {
        return "/index";
    }
}