package ms.eureka.discoveringservice;

import org.springframework.cloud.client.ServiceInstance;

public class Utils {

    public static String describe(ServiceInstance s) {
        return String.format(
                "Host: %1$s\nMetadata: %2$s\nPort: %3$s\nScheme: %4$s\nServiceId: %5$s\nURI: %6$s\nIsSecure: %7$s\n",
                s.getHost(),
                s.getMetadata(),
                s.getPort(),
                s.getScheme(),
                s.getServiceId(),
                s.getUri(),
                s.isSecure());
    }

}
