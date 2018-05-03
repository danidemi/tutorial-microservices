package ms.eureka.discoveringservice;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.cloud.client.ServiceInstance;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;


@RunWith(MockitoJUnitRunner.class)
public class UtilsTest {

    @Test
    public void shouldDescribeAServiceCorrectly() {
        String description = Utils.describe(mock(ServiceInstance.class));
        assertThat(description).isEqualTo("Host: null\n" +
                "Metadata: {}\n" +
                "Port: 0\n" +
                "Scheme: null\n" +
                "ServiceId: null\n" +
                "URI: null\n" +
                "IsSecure: false\n");
    }

}
