package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class PostResponseModel {
    public int postResponse(int userId, int threadId, String desc) {
        try (
                Connection connection =
                        DriverManager.getConnection("jdbc:sqlserver://localhost;database=InternetForum;"
                                + "encrypt=true;trustServerCertificate=true;"
                                + "integratedSecurity=false;user=sa;password=Password.1;");
        ){
            // スレッドへの返信し、スレッドの最終書き込み時間を更新する
            String postSql = 
                    "INSERT INTO [response] (user_id, thread_id, description, posted_date) VALUES(?, ?, ?, GETDATE()) "
                    + "UPDATE thread SET last_written_date = GETDATE() WHERE thread_id = ?";
            PreparedStatement statement = connection.prepareStatement(postSql);
            statement.setInt(1, userId);
            statement.setInt(2, threadId);
            statement.setString(3, desc);
            statement.setInt(4, threadId);
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
