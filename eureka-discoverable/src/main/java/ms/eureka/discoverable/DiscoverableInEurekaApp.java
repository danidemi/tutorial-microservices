package ms.eureka.discoverable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

/**
 * By having spring-cloud-starter-netflix-eureka-client on the classpath,
 * your application automatically registers with the Eureka Server.
 * Configuration is required to locate the Eureka server though.
 */
@SpringBootApplication
@RestController
public class DiscoverableInEurekaApp {

    @GetMapping("/planets")
    public List<String> planets() {
        return Arrays.asList(
                "Mercury", "Venus", "Earth", "Mars", "Ceres", "Jupiter", "Saturn", "Uranus", "Neptune"
        );
    }

    public static void main(String[] args) {
        SpringApplication.run(DiscoverableInEurekaApp.class, args);
    }

}
