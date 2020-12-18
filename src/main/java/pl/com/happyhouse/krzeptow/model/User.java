package pl.com.happyhouse.krzeptow.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String surname;
    private String password;
    private String confirmPassword;
    private String email;
    private String phone;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @OneToMany(mappedBy = "parent")
    private List<Child> children;

    @ManyToMany
    @JoinColumn(name = "student_id")
    private List<Child> students;

    @Override
    public String toString() {
        return name + " " + surname;
    }
}
