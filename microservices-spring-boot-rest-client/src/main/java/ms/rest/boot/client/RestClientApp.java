package ms.rest.boot.client;

import ms.rest.boot.client.resources.Item;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class RestClientApp implements CommandLineRunner {
	
    public static void main(String[] args) {
    	
    	// It's maybe overkill to use Spring Boot to run a simple
    	// REST client, but the goal of this example is to show how to 
    	// invoke an external REST service from within a Spring Boot app.
    	
    	SpringApplication application = new SpringApplication(RestClientApp.class);
    	application.setWebApplicationType(WebApplicationType.NONE);
    	application.run(args);
    }

	@Override
	public void run(String... args) throws Exception {
		
		// This rest template based on Apache's HTTP Components
		// allows to have a full log of the HTTP traffic.
        RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());

        // post with RestTemplate
        HttpEntity<Item> request = new HttpEntity<>(new Item("bar"));
        // double check that the URL used here is the one actually exposed by the server.
        Item foo = restTemplate.postForObject("http://localhost:8080/items", request, Item.class);
        System.out.println(foo);
        
	}

}
