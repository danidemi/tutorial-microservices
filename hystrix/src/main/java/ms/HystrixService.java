package ms;

import ms.commands.PlanetsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableCircuitBreaker
@RestController
public class HystrixService {

    public static void main(String[] args) {
        new SpringApplicationBuilder(HystrixService.class).run(args);
    }

    @Autowired
    private PlanetsService planetsService;

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate(new HttpComponentsClientHttpRequestFactory());
    }

    @GetMapping("/trips")
    public String trips() {
        return "You can fly to:" + planetsService.getPlanets();
    }

}
