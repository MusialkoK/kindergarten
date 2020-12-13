package pl.com.happyhouse.krzeptow.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/teacher")
@AllArgsConstructor
public class TeacherController {

    @GetMapping("")
    public String dashboard(){
        return "teacher/teacherDashboard";
    }

    @PostMapping("/kidsList")
    public String listOfKids(){
        return "teacher/kidsList";
    }

    @PostMapping("/emailSending")
    public String emailSending(){
        return "teacher/emailSending";
    }

    @PostMapping("/absenceReport")
    public String absenceReport(){
        return "teacher/absenceReport";
    }
}
