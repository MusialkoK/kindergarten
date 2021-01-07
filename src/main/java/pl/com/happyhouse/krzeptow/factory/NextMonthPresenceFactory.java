package pl.com.happyhouse.krzeptow.factory;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.com.happyhouse.krzeptow.model.Child;
import pl.com.happyhouse.krzeptow.model.Presence;
import pl.com.happyhouse.krzeptow.services.ChildService;
import pl.com.happyhouse.krzeptow.services.WeeklyCarePlanService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class NextMonthPresenceFactory {

    private final ChildService childService;
    private final WeeklyCarePlanService weeklyCarePlanService;

    public List<Presence> create(List<LocalDate> datesEntry, List<Child> children) {
        List<Presence> presenceList = createBlankPresences(children, datesEntry);
        presenceList.forEach(this::fillDefaultPresenceValue);
        return presenceList;
    }

    private void fillDefaultPresenceValue(Presence presence){
        int careDuration = childService.getHoursFor(presence.getChild(), presence.getDate().getDayOfWeek());
        presence.setHours(careDuration);
    }

    private List<Presence> createBlankPresences(List<Child> children, List<LocalDate> dates) {
        List<Presence> result = new ArrayList<>();
        for (Child child : children) {
            for (LocalDate date : dates) {
                Presence presence = Presence.builder()
                        .child(child)
                        .date(date).build();
                result.add(presence);
            }
        }
        result.forEach(p->p.setHours(weeklyCarePlanService.getHoursFor(p.getChild().getWeeklyCarePlan(),
                p.getDate().getDayOfWeek())));
        return result;
    }
}
