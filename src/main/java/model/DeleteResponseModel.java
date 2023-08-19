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
            // 返信の削除フラグを変更し、非表示にする(同時に、通報フラグを切って管理者画面に表示されないようにする。)
            String editSql = 
                    "UPDATE [response] SET delete_flag = 0 WHERE response_id = ? "
                    + "UPDATE [response] SET report = 0 WHERE response_id = ? ";
            PreparedStatement statement = connection.prepareStatement(editSql);
            statement.setInt(1, responseId);
            statement.setInt(2, responseId);
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
