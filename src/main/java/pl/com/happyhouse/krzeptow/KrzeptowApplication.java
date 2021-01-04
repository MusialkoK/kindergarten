package pl.com.happyhouse.krzeptow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class KrzeptowApplication {

    public static void main(String[] args) {
        SpringApplication.run(KrzeptowApplication.class, args);
    }

}
