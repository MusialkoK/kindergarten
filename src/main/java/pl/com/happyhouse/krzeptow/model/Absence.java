package pl.com.happyhouse.krzeptow.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Absence {

    @Id
    private long id;
    @OneToOne
    private Child child;
    @OneToOne
    private User reporter;
    private LocalDate date;
    private String description;
}
