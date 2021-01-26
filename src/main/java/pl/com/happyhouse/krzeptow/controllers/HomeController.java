package pl.com.happyhouse.krzeptow.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
@AllArgsConstructor
public class HomeController {

    @GetMapping("")
    public String dashboard(){
        return "redirect:/parent";
    }

    @PostMapping("/contact")
    public String contact(){
        return "home/contact";
    }

    @PostMapping("/oNas")
    public String aboutUs(){
        return "home/aboutUs";
    }
}
