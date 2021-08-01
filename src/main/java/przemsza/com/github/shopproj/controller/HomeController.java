package przemsza.com.github.shopproj.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;

@Controller
public class HomeController {

    @GetMapping("")
    public String homePage(){
        return "index";
    }
}
