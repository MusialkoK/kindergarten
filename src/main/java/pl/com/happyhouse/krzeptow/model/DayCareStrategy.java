package pl.com.happyhouse.krzeptow.model;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DayCareStrategy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    private String name;

    private String description;

    @NotNull
    private BigDecimal price;
    @Enumerated(EnumType.STRING)
    @NotNull
    private DayCareStrategyType type;

    public static DayCareStrategy emptyStrategy() {
        return DayCareStrategy.builder()
                .description("Empty strategy")
                .name("Empty strategy")
                .build();
    }
}
