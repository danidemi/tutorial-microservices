package ms.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import java.sql.SQLException;
import java.util.Arrays;

@SpringBootApplication
public class Db implements CommandLineRunner {

    @Autowired
    Environment env;

    public static void main(String[] args) throws SQLException {
        SpringApplication.run(Db.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        String[] params = {
                "-tcp", "-tcpAllowOthers", "-baseDir", env.getProperty("ms.base.dir")
        };

        org.h2.tools.Server.main(params);
    }
}
