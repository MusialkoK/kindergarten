package pl.com.happyhouse.krzeptow.controllers;

import lombok.AllArgsConstructor;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.com.happyhouse.krzeptow.dto.AbsenceDTO;
import pl.com.happyhouse.krzeptow.factory.MultiAbsenceFactory;
import pl.com.happyhouse.krzeptow.model.*;
import pl.com.happyhouse.krzeptow.services.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/parent")
@AllArgsConstructor
public class ParentController {

    private final UserService userService;
    private final MultiAbsenceFactory multiAbsenceFactory;
    private final AbsenceService absenceService;
    private final ChildService childService;
    private final RoleService roleService;
    private final MealPlanService mealPlanService;
    private final MealChangeService mealChangeService;

    @GetMapping("")
    public String dashboard() {
        return "parent/dashboard";
    }

    @PostMapping("/reports")
    public String reports() {
//        createDatabaseEntries();
//        createMealPlanEntries();
//        addMealChange();
        return "parent/reports";
    }


    @PostMapping("/meal")
    public String meal() {
        return "parent/meal";
    }

    @GetMapping("/absence")
    public String addAbsences(Model model, Principal principal) {
        User user = userService.getByUsername(principal.getName());
        model.addAttribute("absenceDTO", AbsenceDTO.builder().build());
        model.addAttribute("children", user.getChildren());
        return "parent/absence";
    }

    @PostMapping("/absence")
    public String registerAbsences(@Valid @ModelAttribute AbsenceDTO createdAbsenceDTO, BindingResult bindingResult, Principal principal, Model model) {
        if (bindingResult.hasErrors()) {
            User user = userService.getByUsername(principal.getName());
            List<String> errorMsg = bindingResult.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList());
            model.addAttribute("errorMsg", errorMsg);
            model.addAttribute("absenceDTO", createdAbsenceDTO);
            model.addAttribute("children", user.getChildren());
            return "parent/absence";
        } else {
            List<Absence> absences = multiAbsenceFactory.create(createdAbsenceDTO, userService.getByUsername(principal.getName()));
            absences.forEach(absenceService::save);
            return "parent/dashboard";
        }
    }

    private void createMealPlanEntries() {
        mealPlanService.save(MealPlan.builder()
                .name("Standard")
                .description("Lorem Ipsum")
                .mealPrice(new BigDecimal(10))
                .build());
        mealPlanService.save(MealPlan.builder()
                .name("Vege")
                .description("dolor sit amet")
                .mealPrice(new BigDecimal(15))
                .build());
    }

    private void createDatabaseEntries() {
        roleService.save(Role.builder()
                .roleName("ROLE_TEACHER")
                .description("teacher")
                .build());
        roleService.save(Role.builder()
                .roleName("ROLE_PARENT")
                .description("parent")
                .build());
        roleService.save(Role.builder()
                .roleName("ROLE_ADMIN")
                .description("admin")
                .build());

        User user = User.builder()
                .name("Adam")
                .surname("Adamski")
                .email("parent1@wp.pl")
                .password("parent1")
                .confirmPassword("guardian1")
                .phone("+48154256987")
                .role(roleService.getByName("ROLE_PARENT"))
                .build();
        user = userService.save(user);
        Child child1 = Child.builder()
                .name("Jan")
                .surname("Adamski")
                .parent(user)
                .build();
        Child child2 = Child.builder()
                .name("Marysia")
                .surname("Adamska")
                .parent(user)
                .build();
        childService.save(child1);
        childService.save(child2);
    }

    private void addMealChange() {
        MealChange mealChange = MealChange.builder()
                .newMealPlan(mealPlanService.getById(2L))
                .child(childService.getById(1L))
                .build();
        mealChangeService.save(mealChange);
    }
}

