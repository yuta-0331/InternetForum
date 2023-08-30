package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class DeleteAccountModel {
    public int delete(int userId) {
        try (
                Connection connection =
                        new CreateConnection().getConnection()
        ){
            // 返信の削除フラグを変更し、非表示にする(同時に、通報フラグを切って管理者画面に表示されないようにする。)
            String deleteUserSql = 
                    "UPDATE [user] SET delete_flag = 0 WHERE user_id = ? "
                    + "UPDATE [user] SET report = 0 WHERE user_id = ? ";
            PreparedStatement statement = connection.prepareStatement(deleteUserSql);
            statement.setInt(1, userId);
            statement.setInt(2, userId);
            return statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
