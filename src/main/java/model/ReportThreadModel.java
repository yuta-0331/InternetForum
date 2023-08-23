package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class ReportThreadModel {
    public int report(int threadId) {
        try (
                Connection connection = 
                        DriverManager.getConnection("jdbc:sqlserver://localhost\\SQLEXPRESS;database=InternetForum;"
                        + "encrypt=true;trustServerCertificate=true;"
                        + "integratedSecurity=false;user=sa;password=1234;");
                
                ){
            // 返信の通報フラグを変更する
            String reportSql = 
                    "UPDATE [thread] SET report = 1 WHERE thread_id = ? ";
            PreparedStatement statement = connection.prepareStatement(reportSql);
            statement.setInt(1, threadId);
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
