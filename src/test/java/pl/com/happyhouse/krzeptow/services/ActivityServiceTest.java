package pl.com.happyhouse.krzeptow.services;

import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;
import pl.com.happyhouse.krzeptow.model.Activity;
import pl.com.happyhouse.krzeptow.repository.ActivityRepository;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ActivityServiceTest {

    @Test
    public void test() {
        //given
        ActivityRepository repository = mock(ActivityRepository.class);
        List<Activity> mockedActivities = createActivity();
        doReturn(mockedActivities).when(repository).findAll();
        //when
        ActivityService activityService = new ActivityService(repository);
        List<Activity> actualActivity = activityService.getAll();
        //then

        assertThat(actualActivity).containsAll(mockedActivities);


    }

    private List<Activity> createActivity() {
        return IntStream.range(0, 3).mapToObj(item -> Activity.builder().name("activity_" + item).build()).collect(Collectors.toList());
    }


}