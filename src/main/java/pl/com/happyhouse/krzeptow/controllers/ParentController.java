package pl.com.happyhouse.krzeptow.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.com.happyhouse.krzeptow.model.User;
import pl.com.happyhouse.krzeptow.services.UserService;

@Controller
@RequestMapping("/parent")
@AllArgsConstructor
public class ParentController {

    private final UserService userService;

    @GetMapping("")
    public String dashboard(){
        return "parent/dashboard";
    }

    @PostMapping("/reports")
    public String reports(){
        User user = User.builder()
                .name("Adam")
                .surname("Adamski")
                .email("guardian1@wp.pl")
                .password("guardian1")
                .confirmPassword("guardian1")
                .login("guardian1")
                .phone("+48154256987")
                .build();
        userService.save(user);
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
