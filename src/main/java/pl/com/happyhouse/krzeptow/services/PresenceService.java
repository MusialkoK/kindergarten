package pl.com.happyhouse.krzeptow.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.com.happyhouse.krzeptow.dto.AbsenceDTO;
import pl.com.happyhouse.krzeptow.model.Child;
import pl.com.happyhouse.krzeptow.model.Presence;
import pl.com.happyhouse.krzeptow.model.User;
import pl.com.happyhouse.krzeptow.repository.PresenceRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class PresenceService {

    private final PresenceRepository presenceRepository;

    public Presence save(Presence presence) {
        return presenceRepository.save(presence);
    }
    public List<Presence> saveAll(List<Presence> presences) {
        return presenceRepository.saveAll(presences);
    }

    public List<Presence> registerAbsence(AbsenceDTO absenceDTO, User parent) {
        List<Presence> absences = getByAbsenceDTO(absenceDTO);
        absences.forEach(a->{
            a.setHours(-1);
            a.setReporter(parent);
        });
        return saveAll(absences);
    }

    public Presence getByChildAndDate(Child child, LocalDate date) {
        return presenceRepository.getByChildAndDate(child, date).orElseThrow(NoSuchElementException::new);
    }

    private List<Presence> getByAbsenceDTO(AbsenceDTO absenceDTO){
        List<Presence> absences = new ArrayList<>();
        for (Child child : absenceDTO.getChildren()) {
            for (LocalDate date : absenceDTO.getSingleDates()) {
                Presence absence = getByChildAndDate(child, date);
                absences.add(absence);
            }
        }
        return absences;
    }

//    public Presence registerAbsence(Child child, LocalDate date, User parent) {
//        Presence presence = getByChildAndDate(child, date);
//        presence.setHours(-1);
//        presence.setReporter(parent);
//        return presenceRepository.save(presence);
//    }

//    public List<Presence> getByChildrenAndDates(List<Child> children, List<LocalDate> dates) {
//        List<Presence> result = new ArrayList<>();
//        for (Child child : children) {
//            for (LocalDate date : dates) {
//                Presence presence = presenceRepository.getByChildAndDate(child, date).get();
//                result.add(presence);
//            }
//        }
//        return result;
//    }





}
