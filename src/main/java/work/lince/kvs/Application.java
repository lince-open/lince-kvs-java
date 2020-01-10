package work.lince.kvs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import work.lince.commons.rest.provider.filter.ResponseHeaderFilter;

@SpringBootApplication(scanBasePackages = {"work.lince"})
@ServletComponentScan(basePackageClasses = {ResponseHeaderFilter.class})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
