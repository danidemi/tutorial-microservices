package ms.eureka.discoveringservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class TripsController {

    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping("/trips/")
    public String trips() {

        // Gets the metadata of all services registered under the name 'planets-service'.
        List<ServiceInstance> instances = discoveryClient.getInstances("planets-service");

        // Build the url of the service to invoke, using the base URL retrieved by Eureka.
        int randomServiceIndex = (int) Math.floor(instances.size() * Math.random());
        ServiceInstance randomService = instances.get(randomServiceIndex);
        String baseUrl = randomService.getUri() + "/planets";

        // Gets the planets from the remote service.
        RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
        ResponseEntity<Object[]> responseEntity = restTemplate.getForEntity(baseUrl, Object[].class);
        List<String> planets = Arrays.stream(responseEntity.getBody()).map(o -> (String) o).collect(Collectors.toList());

        // Builds the message
        return "You can organize your space trip on one of these planets: " + planets.stream().reduce("", (a,p)-> a.isEmpty() ? p : a + ", " + p);
    }

}
