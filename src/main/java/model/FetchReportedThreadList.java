package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.schema.Thread;

public class FetchReportedThreadList {
    public ArrayList<Thread> fetch() {
        try (
                Connection connection =
                        DriverManager.getConnection("jdbc:sqlserver://localhost;database=InternetForum;"
                                + "encrypt=true;trustServerCertificate=true;"
                                + "integratedSecurity=false;user=sa;password=Password.1;");
        ){
            // 指定のジャンルのthreadを取得するクエリ
            String queryThreadList =
                    "SELECT "
                    + "    [user].user_id, user_name, create_day, title, description, thread.thread_id,"
                    + "    update_day, thread.delete_flag, genre.genre_id, genre_name, thread.report, last_written_date "
                    + "FROM thread "
                    + "    INNER JOIN genre "
                    + "        ON thread.genre_id = genre.genre_id "
                    + "    INNER JOIN [user] "
                    + "        ON [user].user_id = thread.user_id "
                    + "WHERE thread.report = 1";
            PreparedStatement statement = connection.prepareStatement(queryThreadList);
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
                int genreId = resultSet.getInt("user_id");
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
            return threadList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
