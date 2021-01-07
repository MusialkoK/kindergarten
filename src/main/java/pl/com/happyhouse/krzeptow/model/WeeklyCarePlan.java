package pl.com.happyhouse.krzeptow.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class WeeklyCarePlan {

    private WeeklyCarePlan(WeeklyCarePlanBuilder builder){
        this.child=builder.child;
        this.mondayStart=builder.mondayStart;
        this.mondayEnd=builder.mondayEnd;
        this.tuesdayStart=builder.tuesdayStart;
        this.tuesdayEnd=builder.tuesdayEnd;
        this.wednesdayStart=builder.wednesdayStart;
        this.wednesdayEnd=builder.wednesdayEnd;
        this.thursdayStart=builder.thursdayStart;
        this.thursdayEnd=builder.thursdayEnd;
        this.fridayStart=builder.fridayStart;
        this.fridayEnd=builder.fridayEnd;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    private Child child;

    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime mondayStart;
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime mondayEnd;
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime tuesdayStart;
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime tuesdayEnd;
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime wednesdayStart;
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime wednesdayEnd;
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime thursdayStart;
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime thursdayEnd;
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime fridayStart;
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime fridayEnd;

    public static WeeklyCarePlanBuilder builder(){
        return new WeeklyCarePlanBuilder();
    }

    @NoArgsConstructor
    @Accessors(chain = true)
    public static class  WeeklyCarePlanBuilder {
        private Child child;
        private LocalTime mondayStart;
        private LocalTime mondayEnd;
        private LocalTime tuesdayStart;
        private LocalTime tuesdayEnd;
        private LocalTime wednesdayStart;
        private LocalTime wednesdayEnd;
        private LocalTime thursdayStart;
        private LocalTime thursdayEnd;
        private LocalTime fridayStart;
        private LocalTime fridayEnd;

        public WeeklyCarePlanBuilder monday(LocalTime start, LocalTime end){
            this.mondayStart = start;
            this.mondayEnd=end;
            return this;
        }

        public WeeklyCarePlanBuilder tuesday(LocalTime start, LocalTime end){
            this.tuesdayStart = start;
            this.tuesdayEnd=end;
            return this;
        }

        public WeeklyCarePlanBuilder wednesday(LocalTime start, LocalTime end){
            this.wednesdayStart = start;
            this.wednesdayEnd=end;
            return this;
        }

        public WeeklyCarePlanBuilder thursday(LocalTime start, LocalTime end){
            this.thursdayStart = start;
            this.thursdayEnd=end;
            return this;
        }

        public WeeklyCarePlanBuilder friday(LocalTime start, LocalTime end){
            this.fridayStart = start;
            this.fridayEnd=end;
            return this;
        }

        public WeeklyCarePlanBuilder child(Child child){
            this.child=child;
            return this;
        }

        public WeeklyCarePlan build(){
            WeeklyCarePlan weeklyCarePlan = new WeeklyCarePlan(this);
            return weeklyCarePlan;
        }

    }
}
