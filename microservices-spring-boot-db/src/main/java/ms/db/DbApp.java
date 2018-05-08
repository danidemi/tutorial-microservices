package ms.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class DbApp {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public static void main(String[] args)  {
        SpringApplication.run(DbApp.class, args);
    }


    @PostMapping(value = "/items")
    public void run(String... args) throws Exception {
        jdbcTemplate.execute("INSERT INTO PERSON(FIRST_NAME, LAST_NAME) VALUES ('Rita', 'Verdi')");
    }

    @GetMapping("/items")
    public String hello() {
        return "Hello";
    }



}
