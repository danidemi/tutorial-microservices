package ms.eureka.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * By having spring-cloud-starter-netflix-eureka-client on the classpath,
 * your application automatically registers with the Eureka Server.
 * Configuration is required to locate the Eureka server though.
 */
@SpringBootApplication
@RestController
public class AppWithEurekaClient {

    @Autowired
    Environment env;

    @RequestMapping("/")
    public String home() {
        return "Hello world";
    }

    @GetMapping("/planets")
    public List<String> planets() {
        return Arrays.asList(
                "Mercury", "Venus", "Earth", "Mars", "Ceres", "Jupiter", "Saturn", "Uranus", "Neptune"
        );
    }

    @PostConstruct
    public void dumpConfig() {
        System.out.println("************************" + env.getProperty("logging.pattern.console"));
    }

    public static void main(String[] args) {
        //new SpringApplicationBuilder(AppWithEurekaClient.class).web(true).run(args);
        SpringApplication.run(AppWithEurekaClient.class, args);
    }

}