package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.schema.Thread;

public class FetchThreadListByGenre {
    public ArrayList<Thread> fetch(int genreId) {
        try (
                Connection connection = 
                        DriverManager.getConnection("jdbc:sqlserver://localhost\\SQLEXPRESS;database=InternetForum;"
                        + "encrypt=true;trustServerCertificate=true;"
                        + "integratedSecurity=false;user=sa;password=1234;");
                
                ){
            // 指定のジャンルのthreadを取得するクエリ
            String queryThreadList =
                    "SELECT "
                    + "    [user].user_id, user_name, create_day, title, description, thread.thread_id,"
                    + "    update_day, thread.delete_flag, genre.genre_id, genre_name, thread.report, last_written_date\n"
                    + "FROM thread \n"
                    + "    INNER JOIN genre \n"
                    + "        ON thread.genre_id = genre.genre_id \n"
                    + "    INNER JOIN [user]\n"
                    + "        ON [user].user_id = thread.user_id      \n"
                    + "WHERE genre.genre_id = ?";
            PreparedStatement statement = connection.prepareStatement(queryThreadList);
            statement.setInt(1, genreId);
            ResultSet resultSet = statement.executeQuery();
            ArrayList<Thread> threadList = new ArrayList<>();
            while (resultSet.next()) {
                // 取得したデータを変数に格納する
                int userId = resultSet.getInt("user_id");
                int threadId = resultSet.getInt("thread_id");
                String userName = resultSet.getString("user_name");
                String createDay = resultSet.getString("create_day");
                String title = resultSet.getString("title");
                String desc = resultSet.getString("description");
                String update = resultSet.getString("update_day");
                boolean deleteFlag = resultSet.getBoolean("delete_flag");
                String genreName = resultSet.getString("genre_name");
                boolean report = resultSet.getBoolean("report");
                String lastWrittenDate = resultSet.getString("last_written_date");
                // Threadインスタンスを作成する
                Thread thread = new Thread.Builder(threadId)
                        .with(arg -> {
                            arg.userId = userId;
                            arg.userName = userName;
                            arg.createDay = createDay;
                            arg.title = title;
                            arg.desc = desc;
                            arg.update = update;
                            arg.deleteFlag = deleteFlag;
                            arg.genreId = genreId;
                            arg.genreName = genreName;
                            arg.report = report;
                            arg.lastWrittenDate = lastWrittenDate;
                        })
                        .build();
                threadList.add(thread);
            }
            connection.close();
            return threadList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
