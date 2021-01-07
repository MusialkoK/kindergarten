package pl.com.happyhouse.krzeptow.services;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.com.happyhouse.krzeptow.model.Child;
import pl.com.happyhouse.krzeptow.model.DayCareStrategy;
import pl.com.happyhouse.krzeptow.model.DayCareStrategyType;
import pl.com.happyhouse.krzeptow.repository.DayCareStrategyRepository;

import java.math.BigDecimal;
import java.time.Month;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class DayCareStrategyService {

    private final DayCareStrategyRepository dayCareStrategyRepository;

    public DayCareStrategy getById(Long id) {
        return dayCareStrategyRepository.getById(id).orElse(DayCareStrategy.emptyStrategy());
    }

    public DayCareStrategy save(DayCareStrategy dayCareStrategy){
        return dayCareStrategyRepository.save(dayCareStrategy);
    }

    public BigDecimal getMonthlyPaymentForChild(Child child, Month month) {
        switch (child.getDayCareStrategy().getType()) {
            case MONTHLY:
                return child.getDayCareStrategy().getPrice();
            case HOURLY:
//                BigDecimal hoursCount = new BigDecimal(hourDayCarePresenceService.countHoursForChildInMonth(child, month));
//                return child.getDayCareStrategy().getPrice().multiply(hoursCount);
            default:
                return new BigDecimal(-1);
        }
    }

    public DayCareStrategy getByType(DayCareStrategyType type){
        return dayCareStrategyRepository.getByType(type).orElseThrow(NoSuchElementException::new);
    }
}
