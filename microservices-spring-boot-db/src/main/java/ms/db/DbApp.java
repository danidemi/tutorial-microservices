package ms.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class DbApp implements CommandLineRunner {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public static void main(String[] args)  {

        SpringApplication application = new SpringApplication(DbApp.class);
        //application.setWebApplicationType(WebApplicationType.NONE);
        application.run(args);
    }


    @Override
    public void run(String... args) throws Exception {

        jdbcTemplate.execute("INSERT INTO PERSON(FIRST_NAME, LAST_NAME) VALUES ('Rita', 'Verdi')");

    }

}
