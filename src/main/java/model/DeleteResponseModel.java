package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class DeleteResponseModel {

    public int delete(int responseId) {
        try (
                Connection connection = 
                        DriverManager.getConnection("jdbc:sqlserver://localhost\\SQLEXPRESS;database=InternetForum;"
                        + "encrypt=true;trustServerCertificate=true;"
                        + "integratedSecurity=false;user=sa;password=1234;");
                
                ){
            // 返信を編集し、スレッドの最終書き込み時間を更新する
            String editSql = 
                    "UPDATE [response] SET delete_flag = 0 WHERE response_id = ? ";
            PreparedStatement statement = connection.prepareStatement(editSql);
            statement.setInt(1, responseId);
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
