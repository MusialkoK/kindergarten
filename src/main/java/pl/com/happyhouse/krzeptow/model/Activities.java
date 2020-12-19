package pl.com.happyhouse.krzeptow.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Activities {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private long activitesId;

    private String name;

    private BigDecimal rate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private Child child;

}
