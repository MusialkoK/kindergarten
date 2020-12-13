package pl.com.happyhouse.krzeptow.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Child {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String surname;
    private Date birthDate;
    @ManyToMany(mappedBy = "children")
    private List<User> guardians;

    @ManyToMany(mappedBy = "students")
    private List<User> teachers;
}
