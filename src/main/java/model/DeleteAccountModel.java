package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class DeleteAccountModel {
    public int delete(int userId) {
        try (
                Connection connection =
                        DriverManager.getConnection("jdbc:sqlserver://localhost;database=InternetForum;"
                                + "encrypt=true;trustServerCertificate=true;"
                                + "integratedSecurity=false;user=sa;password=Password.1;");
        ){
            // 返信の削除フラグを変更し、非表示にする(同時に、通報フラグを切って管理者画面に表示されないようにする。)
            String deleteUserSql = 
                    "UPDATE [user] SET delete_flag = 0 WHERE user_id = ? "
                    + "UPDATE [user] SET report = 0 WHERE user_id = ? ";
            PreparedStatement statement = connection.prepareStatement(deleteUserSql);
            statement.setInt(1, userId);
            statement.setInt(2, userId);
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
