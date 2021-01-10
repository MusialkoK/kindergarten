package pl.com.happyhouse.krzeptow.repository;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import pl.com.happyhouse.krzeptow.KrzeptowApplication;
import pl.com.happyhouse.krzeptow.model.Activity;
import pl.com.happyhouse.krzeptow.model.Child;
import pl.com.happyhouse.krzeptow.model.User;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = KrzeptowApplication.class)
@ActiveProfiles("test")
public class ActivityRepositoryIT {

    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ChildRepository childRepository;

    @Test
    public void test() {

        //given

        User teacher = User.builder().name("Jan").surname("Kowalski").build();
        Child firstChild = Child.builder().name("Janek").surname("Nowak").build();
        userRepository.save(teacher);
        childRepository.save(firstChild);

        Activity activity = Activity.builder()
                .name("szachy")
                .build();
        activityRepository.save(activity);

//when
        Activity activityById = activityRepository.findById(1L).get();
        User teacherById = userRepository.findById(1L).get();
        Child childById = childRepository.findById(1L).get();
        activityById.setUser(teacherById);
        activityById.setChild(childById);
        activityRepository.saveAndFlush(activityById);
        //then

        Activity actualActivity = activityRepository.findById(1L).get();


        assertThat(actualActivity.getUser().getName()).isEqualTo("Jan");
        assertThat(actualActivity.getChilds().get(0).getFullName()).isEqualTo("Janek Nowak");

    }


}