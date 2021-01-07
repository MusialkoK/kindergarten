package pl.com.happyhouse.krzeptow.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity(name = "children")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Child {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String surname;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date birthDate;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private User parent;

    @ManyToMany(mappedBy = "students")
    private List<User> teachers;

    @ManyToOne
    private MealPlan currentMealPlan;

    @ManyToOne
    private DayCareStrategy dayCareStrategy;

    @OneToOne(fetch = FetchType.EAGER)
    private WeeklyCarePlan weeklyCarePlan;

    public String getFullName(){
        return name + " " + surname;
    }

    @Override
    public String toString() {
        return name + " " + surname;
    }
}
