package model;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class EditResponseModel {
    public int editResponse(int threadId, int responseId, String desc) {
        try (
                Connection connection =
                        new CreateConnection().getConnection()
        ){
            // 返信を編集する
            String editSql = 
                    "UPDATE [response] SET description = ?, update_day = GETDATE() WHERE response_id = ? ";
            PreparedStatement statement = connection.prepareStatement(editSql);
            statement.setString(1, desc);
            statement.setInt(2, responseId);
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
