package ms.eureka.discoveringservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
@RestController
public class DiscoveringService {

    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping("/discovery/")
    public List<String> discovery() {
        return discoveryClient.getServices();
    }

    @GetMapping("/discovery/{serviceId}")
    public List<ServiceInstance> discoveryService(@PathVariable String serviceId) {
        return discoveryClient.getInstances(serviceId);
    }

    @GetMapping("/trips/")
    public String trips() {
        List<ServiceInstance> instances = discoveryClient.getInstances("planets-service");
        String urlGETList = instances.get(0).getUri() + "/planets";

        RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
        ResponseEntity<Object[]> responseEntity = restTemplate.getForEntity(urlGETList, Object[].class);
        MediaType contentType = responseEntity.getHeaders().getContentType();
        HttpStatus statusCode = responseEntity.getStatusCode();
        List<String> planets = Arrays.stream(responseEntity.getBody()).map(o -> (String) o).collect(Collectors.toList());

        return "You can organize your space trip on one of these planets: " + planets.stream().reduce("", (a,p)-> a.isEmpty() ? p : a + ", " + p);
    }

    public static void main(String[] args) {
        SpringApplication.run(DiscoveringService.class, args);
    }

}