package pl.com.happyhouse.krzeptow.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.com.happyhouse.krzeptow.model.Child;
import pl.com.happyhouse.krzeptow.model.Presence;
import pl.com.happyhouse.krzeptow.model.User;
import pl.com.happyhouse.krzeptow.repository.PresenceRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PresenceService {

    private final PresenceRepository presenceRepository;

    public Presence save(Presence presence) {
        return presenceRepository.save(presence);
    }

    public List<Presence> registerAbsence(List<Child> children, List<LocalDate> dates, User parent) {
        List<Presence> absences = new ArrayList<>();
        for (Child child : children) {
            for (LocalDate date : dates) {
                Presence absence = presenceRepository.getByChildAndDate(child, date).get();
                absences.add(absence);
            }
        }
        return absences.stream()
                .map(a->a.setHours(-1))
                .map(a->a.setReporter(parent))
                .map(presenceRepository::save).collect(Collectors.toList());
    }

    public Presence registerAbsence(Child child, LocalDate date, User parent) {
        Presence presence = getByChildAndDate(child, date);
        presence.setHours(-1);
        presence.setReporter(parent);
        return presenceRepository.save(presence);
    }

    public List<Presence> saveAll(List<Presence> presences) {
        return presenceRepository.saveAll(presences);
    }

    public Presence getByChildAndDate(Child child, LocalDate date) {
        return presenceRepository.getByChildAndDate(child, date).orElse(Presence.builder()
                .child(child)
                .date(date)
                .build());
    }

    public List<Presence> getByChildrenAndDates(List<Child> children, List<LocalDate> dates) {
        List<Presence> result = new ArrayList<>();
        for (Child child : children) {
            for (LocalDate date : dates) {
                Presence presence = presenceRepository.getByChildAndDate(child, date).get();
                result.add(presence);
            }
        }
        return result;
    }
}
