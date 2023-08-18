package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.schema.User;

public class FetchUserInfo {
    
    public User fetch(int userId) {
        try (
                Connection connection = 
                        DriverManager.getConnection("jdbc:sqlserver://localhost\\SQLEXPRESS;database=InternetForum;"
                        + "encrypt=true;trustServerCertificate=true;"
                        + "integratedSecurity=false;user=sa;password=1234;");
                
                ){
            String queryUser = "SELECT user_id, user_name, profile, registration_date, delete_flag, report "
                    + "FROM [user] "
                    + "WHERE user_id = ?";
            PreparedStatement statement = connection.prepareStatement(queryUser);
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            User user = null;
            if (resultSet.next()) {
                // 取得したデータを変数に格納する
                String userName = resultSet.getString("user_name");
                String profile = resultSet.getString("profile");
                String registrationDate = resultSet.getString("registration_date");
                boolean deleteFlag = resultSet.getBoolean("delete_flag");
                boolean report = resultSet.getBoolean("report");
                // Userインスタンスを作成
                user = new User.Builder(userId)
                        .with(arg -> {
                            arg.userName = userName;
                            arg.profile = profile;
                            arg.registrationDate = registrationDate;
                            arg.deleteFlag = deleteFlag;
                            arg.report = report;
                        })
                        .build();
            } else {
                return null;
            }
            connection.close();
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
}
