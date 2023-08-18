package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserLoginModel {
    private int userId;
    public boolean login(String email, String password) {
        try (
                Connection connection = 
//                        DriverManager.getConnection("jdbc:sqlserver://localhost\\SQLEXPRESS;database=InternetForum;"
//                        + "encrypt=true;trustServerCertificate=true;"
//                        + "integratedSecurity=false;user=sa;password=1234;");
                        DriverManager.getConnection("jdbc:sqlserver://localhost;database=InternetForum;"
                                + "encrypt=true;trustServerCertificate=true;"
                                + "integratedSecurity=false;user=sa;password=Password.1;");
                
                ){
            
            String loginSql = "SELECT * FROM [user] WHERE mail_address = ?";
            PreparedStatement statement = connection.prepareStatement(loginSql);
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                return false;
            }
            userId = resultSet.getInt("user_id");
            String hashPassword = resultSet.getString("hashed_password");
            HashPasswordUtil util = new HashPasswordUtil();
            if (util.checkHash(hashPassword, util.create(password))) {
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public int getUserId() {
        return userId;
    }
}
