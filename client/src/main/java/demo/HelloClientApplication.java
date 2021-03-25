package demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;


/**
 * @author Spencer Gibb
 */
@SpringBootApplication
@RestController
@EnableFeignClients
public class HelloClientApplication {
    @Autowired
    HelloClient client;

    @RequestMapping("/")
    public String hello() {
        UUID uuid = UUID.randomUUID();
        return client.hello(uuid.toString());
    }

    public static void main(String[] args) {
        SpringApplication.run(HelloClientApplication.class, args);
    }

    @FeignClient(name = "opt-quotation-order-service", url = "http://127.0.0.1:7111")
    interface HelloClient {
        @RequestMapping("/{name}")
        String hello(@PathVariable(value = "name") String name);
    }
}
