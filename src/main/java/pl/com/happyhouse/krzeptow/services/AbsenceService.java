package pl.com.happyhouse.krzeptow.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.com.happyhouse.krzeptow.model.Absence;
import pl.com.happyhouse.krzeptow.repository.AbsenceRepository;


import java.util.List;

@Service
@AllArgsConstructor
public class AbsenceService {

    private final AbsenceRepository absenceRepository;

    public Absence save(Absence absence){
        return absenceRepository.save(absence);
    }

    public List<Absence> saveAll(List<Absence> absences){
        return absenceRepository.saveAll(absences);
    }
}
