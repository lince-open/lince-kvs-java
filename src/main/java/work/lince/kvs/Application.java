package work.lince.kvs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "work.lince.commons")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
