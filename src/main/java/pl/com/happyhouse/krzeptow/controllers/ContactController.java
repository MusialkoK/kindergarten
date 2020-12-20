package pl.com.happyhouse.krzeptow.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/contact")
@AllArgsConstructor
public class ContactController {
    @GetMapping("")
    public String dashboard(){
        return "home/contact";
    }
}
