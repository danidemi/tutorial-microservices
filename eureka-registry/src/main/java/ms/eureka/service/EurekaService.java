package ms.eureka.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.Bean;

import com.netflix.appinfo.ApplicationInfoManager;
import com.netflix.appinfo.ApplicationInfoManager.OptionalArgs;
import com.netflix.appinfo.EurekaInstanceConfig;

@EnableEurekaServer
@SpringBootApplication
public class EurekaService {
    
    public static void main(String[] args) {
        SpringApplication.run(EurekaService.class, args);
    }
    
}
