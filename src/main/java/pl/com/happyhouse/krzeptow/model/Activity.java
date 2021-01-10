package pl.com.happyhouse.krzeptow.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private long id;

    private String name;

    private BigDecimal rate;

    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "conductor_id")
    private User user;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "id")
    private List<Child> childs;

    public void setChild(Child child){
        if(childs == null){
            childs=new ArrayList<>();
        }
        childs.add(child);

    }





}
