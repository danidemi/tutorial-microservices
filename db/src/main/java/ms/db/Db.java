package ms.db;

import java.sql.SQLException;
import java.util.Arrays;

public class Db {
    public static void main(String[] args) throws SQLException {
        String[] params = {
                "-tcp", "-tcpAllowOthers", "-baseDir", System.getProperty("user.dir")
                //"-?"
        };

        org.h2.tools.Server.main(params);
    }
}
