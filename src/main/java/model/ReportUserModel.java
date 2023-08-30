package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class ReportUserModel {
    public int report(int userId) {
        try (
                Connection connection =
                        new CreateConnection().getConnection()
        ){
            // 返信の通報フラグを変更する
            String reportSql = 
                    "UPDATE [user] SET report = 1 WHERE user_id = ? ";
            PreparedStatement statement = connection.prepareStatement(reportSql);
            statement.setInt(1, userId);
            return statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

}
