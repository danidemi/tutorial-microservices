package ms.commands;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class PlanetsService {

    private RestTemplate restTemplate;

    public PlanetsService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @HystrixCommand(fallbackMethod = "getPlanetsFallback")
    public List<String> getPlanets() {

        return restTemplate.exchange("http://localhost:44444/planets",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<String>>() {
                }).getBody();



    }

    public List<String> getPlanetsFallback() {
        return Arrays.asList("Earth");
    }
}
