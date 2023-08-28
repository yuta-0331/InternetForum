package model;

import java.sql.Connection;
import java.sql.DriverManager;

public class CreateConnection {
    private final Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public CreateConnection() throws Exception {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        connection =
                DriverManager.getConnection("jdbc:sqlserver://localhost;database=InternetForum;"
                        + "encrypt=true;trustServerCertificate=true;"
                        + "integratedSecurity=false;user=sa;password=Password.1;");
    }
}
