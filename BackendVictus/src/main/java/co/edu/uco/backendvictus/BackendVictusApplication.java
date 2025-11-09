package co.edu.uco.backendvictus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "co.edu.uco.backendvictus")
public class BackendVictusApplication {

    public static void main(final String[] args) {
        SpringApplication.run(BackendVictusApplication.class, args);
    }
}
