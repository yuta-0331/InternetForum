package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.schema.User;

public class FetchReportedUserList {
    public ArrayList<User> fetch() {
        try (
                Connection connection = 
                        DriverManager.getConnection("jdbc:sqlserver://localhost\\SQLEXPRESS;database=InternetForum;"
                        + "encrypt=true;trustServerCertificate=true;"
                        + "integratedSecurity=false;user=sa;password=1234;");
                
                ){
            // レスポンスidからresponseの取得
            String sql = "SELECT user_id, user_name, profile, registration_date, delete_flag, report FROM [user] WHERE report = 1";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            ArrayList<User> userList = new ArrayList<>();
            while (resultSet.next()) {
                // 取得したデータを変数に格納する。
                int userId = resultSet.getInt("user_id");
                String userName = resultSet.getString("user_name");
                String profile = resultSet.getString("profile");
                String registrationDate = resultSet.getString("registration_date");
                boolean deleteFlag = resultSet.getBoolean("delete_flag");
                boolean report = resultSet.getBoolean("report");
                // 取得したデータでResponseインスタンスを作成
                User user = new User.Builder(userId)
                        .with(arg -> {
                            arg.userName = userName;
                            arg.profile = profile;
                            arg.registrationDate = registrationDate;
                            arg.deleteFlag = deleteFlag;
                            arg.report = report;
                        })
                        .build();
                userList.add(user);
            }
            
            connection.close();
            return userList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
