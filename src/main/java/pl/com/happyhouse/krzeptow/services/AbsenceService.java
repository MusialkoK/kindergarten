package pl.com.happyhouse.krzeptow.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.com.happyhouse.krzeptow.model.Absence;
import pl.com.happyhouse.krzeptow.repository.AbsenceRepository;

@Service
@AllArgsConstructor
public class AbsenceService {

    private final AbsenceRepository absenceRepository;

    private Absence save(Absence absence){
        return absenceRepository.save(absence);
    }
}
