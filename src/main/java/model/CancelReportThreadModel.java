package model;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class CancelReportThreadModel {
    public int report(int threadId) {
        try (
                Connection connection =
                        new CreateConnection().getConnection()
        ){
            // 返信の通報フラグを変更する
            String reportSql = 
                    "UPDATE [thread] SET report = 0 WHERE thread_id = ? ";
            PreparedStatement statement = connection.prepareStatement(reportSql);
            statement.setInt(1, threadId);
            return statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
