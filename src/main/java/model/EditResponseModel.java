package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class EditResponseModel {
    public int editResponse(int threadId, int responseId, String desc) {
        try (
                Connection connection = 
                        DriverManager.getConnection("jdbc:sqlserver://localhost\\SQLEXPRESS;database=InternetForum;"
                        + "encrypt=true;trustServerCertificate=true;"
                        + "integratedSecurity=false;user=sa;password=1234;");
                
                ){
            // 返信を編集し、スレッドの最終書き込み時間を更新する
            String editSql = 
                    "UPDATE [response] SET description = ? WHERE response_id = ? "
                    + "UPDATE thread SET last_written_date = GETDATE() WHERE thread_id = ?";
            PreparedStatement statement = connection.prepareStatement(editSql);
            statement.setString(1, desc);
            statement.setInt(2, responseId);
            statement.setInt(3, threadId);
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
