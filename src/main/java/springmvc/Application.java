package springmvc;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    @Value("${andersen.course}")
    private String course;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}