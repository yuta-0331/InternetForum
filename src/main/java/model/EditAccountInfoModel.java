package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class EditAccountInfoModel {
    public int editAccount(int userId, String userName, String profile) {
        try (
                Connection connection = 
                        DriverManager.getConnection("jdbc:sqlserver://localhost\\SQLEXPRESS;database=InternetForum;"
                        + "encrypt=true;trustServerCertificate=true;"
                        + "integratedSecurity=false;user=sa;password=1234;");
                
                ){
            // account infoを更新する
            String editSql = 
                    "UPDATE [user] SET user_name = ?, profile = ? WHERE user_id = ? ";
            PreparedStatement statement = connection.prepareStatement(editSql);
            statement.setString(1, userName);
            statement.setString(2, profile);
            statement.setInt(3, userId);
            int row = statement.executeUpdate();
            if (row == 0) {
                return 0;
            }
            connection.close();
            return row;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }

    }


}
