package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class DeleteThreadModel {
    public int delete(int threadId) {
        try (
                Connection connection =
                        new CreateConnection().getConnection()
        ){
            // スレッドの削除フラグを変更し、非表示にする(同時に、通報フラグを切って管理者画面に表示されないようにする。)
            String deleteUserSql = 
                    "UPDATE [thread] SET delete_flag = 0 WHERE thread_id = ? "
                    + "UPDATE [thread] SET report = 0 WHERE thread_id = ? ";
            PreparedStatement statement = connection.prepareStatement(deleteUserSql);
            statement.setInt(1, threadId);
            statement.setInt(2, threadId);
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
