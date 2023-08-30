package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class EditAccountInfoModel {
    public int editAccount(int userId, String userName, String profile) {
        try (
                Connection connection =
                        new CreateConnection().getConnection()
        ){
            // account infoを更新する
            String editSql = 
                    "UPDATE [user] SET user_name = ?, profile = ? WHERE user_id = ? ";
            PreparedStatement statement = connection.prepareStatement(editSql);
            statement.setString(1, userName);
            statement.setString(2, profile);
            statement.setInt(3, userId);
            return statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }

    }


}
