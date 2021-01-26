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
import pl.com.happyhouse.krzeptow.dto.MealChangeDTO;
import pl.com.happyhouse.krzeptow.factory.MealChangeFactory;
import pl.com.happyhouse.krzeptow.factory.NextMonthPresenceFactory;
import pl.com.happyhouse.krzeptow.model.*;
import pl.com.happyhouse.krzeptow.services.*;
import pl.com.happyhouse.krzeptow.utils.LocalDateConverter;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.security.Principal;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/parent")
@AllArgsConstructor
public class ParentController {

    private final UserService userService;
    private final PresenceService presenceService;
    private final ChildService childService;
    private final RoleService roleService;
    private final MealPlanService mealPlanService;
    private final MealChangeService mealChangeService;
    private final MealChangeFactory mealChangeFactory;
    private final DayCareStrategyService dayCareStrategyService;
    private final HolidayService holidayService;
    private final NextMonthPresenceFactory nextMonthPresenceFactory;
    private final WeeklyCarePlanService weeklyCarePlanService;

    @GetMapping("")
    public String dashboard() {

        return "parent/dashboard";
    }

    @PostMapping("/reports")
    public String reports() {
//        createDatabaseEntries();
//        createMealPlanEntries();
//        addMealChange();
//        createDayCareStrategyPlans();
//        weeklyCarePlanWithChildAssignment();
//        createNextMontPresences();
        return "parent/dashboard";
    }

    @GetMapping("/mealchange")
    public String meal(Model model, Principal principal) {
        User user = userService.getByUsername(principal.getName());
        model.addAttribute("mealChangeDTO", MealChangeDTO.builder().build());
        model.addAttribute("mealPlans", mealPlanService.getAllPlans());
        model.addAttribute("children", user.getChildren());
        return "parent/mealChange";
    }

    @PostMapping("/mealchange")
    public String mealChange(@Valid @ModelAttribute MealChangeDTO createdMealChangeDTO, BindingResult bindingResult, Principal principal, Model model) {
        if (bindingResult.hasErrors()) {
            User user = userService.getByUsername(principal.getName());
            List<String> errorMsg = bindingResult.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList());
            model.addAttribute("errorMsg", errorMsg);
            model.addAttribute("mealChangeDTO", createdMealChangeDTO);
            model.addAttribute("mealPlans", mealPlanService.getAllPlans());
            model.addAttribute("children", user.getChildren());
            return "parent/mealChange";
        } else {
            List<MealChange> mealChangeList = mealChangeFactory.create(createdMealChangeDTO);
            mealChangeService.saveAll(mealChangeList);
            return "parent/dashboard";
        }
    }

    @GetMapping("/absence")
    public String addAbsences(Model model, Principal principal) {
        User user = userService.getByUsername(principal.getName());
        List<Child> children = user.getChildren();
        Map<Child, String> presences = new HashMap<>();
        children.forEach(c -> presences.put(c, LocalDateConverter.localDatesToString(presenceService.getRealPresenceDatesBy(c))));
        model.addAttribute("absenceDTO", AbsenceDTO.builder().build());
        model.addAttribute("children", children);
        model.addAttribute("holidays", LocalDateConverter.localDatesToString(holidayService.findAllDates()));
        model.addAttribute("presences", presences);
        model.addAttribute("days", Arrays.asList(1,2,3,4,5));
        return "parent/absence";
    }

    @PostMapping("/absence")
    public String registerAbsences(@Valid @ModelAttribute AbsenceDTO createdAbsenceDTO, BindingResult
            bindingResult, Principal principal, Model model) {
        User parent = userService.getByUsername(principal.getName());
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
            presenceService.registerAbsence(createdAbsenceDTO, parent);
            return "parent/dashboard";
        }
    }


//-----------------------FOR DB ENTRY ONLY----------------------------------------------

    private List<Presence> createNextMontPresences() {
        return presenceService.createNextMonth();
    }


    private void weeklyCarePlanWithChildAssignment() {
        Child child = childService.getById(1);
        WeeklyCarePlan weeklyCarePlan = WeeklyCarePlan.builder()
                .monday(LocalTime.of(7, 0), LocalTime.of(13, 0))
                .tuesday(LocalTime.of(7, 0), LocalTime.of(13, 0))
                .friday(LocalTime.of(12, 0), LocalTime.of(17, 0))
                .child(child)
                .build();
        childService.setWeeklyCarePlan(child, weeklyCarePlan);
    }

    private void createDayCareStrategyPlans() {
        dayCareStrategyService.save(DayCareStrategy.builder()
                .name("Całodzienna")
                .description("Codzienna opieka nad Twoim maluchem w godzinach 7.00-17.00")
                .price(new BigDecimal(1000))
                .type(DayCareStrategyType.MONTHLY)
                .build());

        dayCareStrategyService.save(DayCareStrategy.builder()
                .name("Godzinowa")
                .description("Chętnie zajmieny się Twoim maluchem w okeślonym czasie")
                .price(new BigDecimal(20))
                .type(DayCareStrategyType.HOURLY)
                .build());
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

