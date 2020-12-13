package pl.com.happyhouse.krzeptow.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor
public class User {

    @Id
    private long id;
    private String name;
    private String surname;
    private String login;
    private String password;
    private String confirmPassword;
    private String email;
    private String phone;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @ManyToMany
    @JoinColumn(name = "child_id")
    private List<Child> children;

    @ManyToMany
    @JoinColumn(name = "student_id")
    private List<Child> students;


}
