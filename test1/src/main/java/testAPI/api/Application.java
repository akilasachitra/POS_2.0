package testAPI.api;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import testAPI.api.drug.PharmacyService;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        //ApplicationContext context = new AnnotationConfigApplicationContext(Application.class);
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner runner(PharmacyService pharmacyService) {
        return args -> {
//            enable this to create test data - 500 products
//            for (int i = 0; i < 500; i++) {
//                pharmacyService.saveProduct(new PharmacyDTO("itit" + i, "item 10" + i, "Item auto generated product " + i, 200.00 + i));
//            }
        };
    }

}
