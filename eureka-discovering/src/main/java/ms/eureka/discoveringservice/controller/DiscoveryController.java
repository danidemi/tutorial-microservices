package ms.eureka.discoveringservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DiscoveryController {

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

}
