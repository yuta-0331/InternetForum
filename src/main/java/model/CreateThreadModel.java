package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CreateThreadModel {
    private int threadId;
    
    public int getThreadId() {
        return threadId;
    }
    
    public int create(String title, String description, int genreId, int userId) {
        try (
                Connection connection =
                        DriverManager.getConnection("jdbc:sqlserver://localhost;database=InternetForum;"
                                + "encrypt=true;trustServerCertificate=true;"
                                + "integratedSecurity=false;user=sa;password=Password.1;");
        ){
            // スレッドの作成
            String createThreadSql = 
                    "INSERT INTO [thread] (title, description, genre_id, user_id, create_day, last_written_date) VALUES(?, ?, ?, ?, GETDATE(), GETDATE())";
            PreparedStatement statement = connection.prepareStatement(createThreadSql,  java.sql.Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, title);
            statement.setString(2, description);
            statement.setInt(3, genreId);
            statement.setInt(4, userId);
            int row = statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                threadId = resultSet.getInt(1);
            }
            return row;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
