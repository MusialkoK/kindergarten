package pl.com.happyhouse.krzeptow.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/parent")
@AllArgsConstructor
public class ParentController {

    @GetMapping("")
    public String dashboard(){
        return "parent/dashboard";
    }

    @PostMapping("/reports")
    public String reports(){
        return "parent/reports";
    }

    @PostMapping("/absences")
    public String absences(){
        return "parent/absences";
    }

    @PostMapping("/meal")
    public String meal(){
        return "parent/meal";
    }

}
