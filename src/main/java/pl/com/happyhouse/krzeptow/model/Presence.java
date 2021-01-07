package pl.com.happyhouse.krzeptow.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"child_id", "date"})
})
@Accessors(chain = true)
public class Presence {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private long id;

    @OneToOne
    @JoinColumn(name = "child_id")
    private Child child;

    @OneToOne
    private User reporter;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate date;
    private String description;

    private int hours;

    @Override
    public String toString() {
        return "Presence:" + child.getFullName() + " " + hours + 'h';
    }
}
