package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class ReportResponseModel {
    public int report(int responseId) {
        try (
                Connection connection =
                        new CreateConnection().getConnection()
        ){
            // 返信の通報フラグを変更する
            String reportSql = 
                    "UPDATE [response] SET report = 1 WHERE response_id = ? ";
            PreparedStatement statement = connection.prepareStatement(reportSql);
            statement.setInt(1, responseId);
            int row = statement.executeUpdate();
            if (row == 0) {
                return 0;
            }
            return row;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
