package pl.com.happyhouse.krzeptow.model;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String roleName;
    @OneToMany(mappedBy = "role")
    private List<User> users;
    private String description;

    @Override
    public String toString() {
        return "Role{" +
                "description='" + description + '\'' +
                '}';
    }
}
