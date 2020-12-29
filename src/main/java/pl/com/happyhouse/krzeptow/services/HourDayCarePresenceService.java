package pl.com.happyhouse.krzeptow.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.com.happyhouse.krzeptow.model.Child;
import pl.com.happyhouse.krzeptow.model.HourDayCarePresence;
import pl.com.happyhouse.krzeptow.repository.HourDayCarePresenceRepository;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class HourDayCarePresenceService {

    private final HourDayCarePresenceRepository hourDayCarePresenceRepository;

    public Integer countHoursForChildBetweenDates(Child child, LocalDate start, LocalDate end){
        return hourDayCarePresenceRepository
                .getHourDayCarePresencesByChildAndDateBetween(child, start, end).stream()
                .map(HourDayCarePresence::getHours)
                .reduce(0,Integer::sum);
    }

}
