package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.schema.ThreadWithResponseList;
import model.schema.Response;
import model.schema.Thread;

public class FetchThreadWithResponseList {
    
    public ThreadWithResponseList fetch(int threadId) {
        try (
                Connection connection =
                        DriverManager.getConnection("jdbc:sqlserver://localhost;database=InternetForum;"
                                + "encrypt=true;trustServerCertificate=true;"
                                + "integratedSecurity=false;user=sa;password=Password.1;");
        ){
            // 引数で与えたthreadIdからthread, user, genre情報の取得
            String queryThreadWithGenre = 
                    "SELECT "
                    + "    [user].user_id, user_name, create_day, title, description,"
                    + "    update_day, thread.delete_flag, genre.genre_id, genre_name, thread.report, last_written_date\n"
                    + "FROM thread \n"
                    + "    INNER JOIN genre \n"
                    + "        ON thread.genre_id = genre.genre_id \n"
                    + "    INNER JOIN [user]\n"
                    + "        ON [user].user_id = thread.user_id      \n"
                    + "WHERE thread_id = ?";
            PreparedStatement statement = connection.prepareStatement(queryThreadWithGenre);
            statement.setInt(1, threadId);
            ResultSet resultSet = statement.executeQuery();
            Thread thread = null;
            if (resultSet.next()) {
                // 取得したデータを変数に格納する
                int userId = resultSet.getInt("user_id");
                String userName = resultSet.getString("user_name");
                String createDay = resultSet.getString("create_day");
                String title = resultSet.getString("title");
                String desc = resultSet.getString("description");
                String update = resultSet.getString("update_day");
                boolean deleteFlag = resultSet.getBoolean("delete_flag");
                int genreId = resultSet.getInt("genre_id");
                String genreName = resultSet.getString("genre_name");
                boolean report = resultSet.getBoolean("report");
                String lastWrittenDate = resultSet.getString("last_written_date");
                // データをThreadのフィールドに設定する
                thread = new Thread.Builder(threadId)
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
            } else {
                return null;
            }
            
            // threadIdからresponseを取得
            String queryResponseByThreadId = 
                    "SELECT \n"
                    + "    [user].user_id, user_name, description, posted_date, update_day, "
                    + "    response.delete_flag, response.report, response_id "
                    + "FROM response\n"
                    + "    INNER JOIN [user]\n"
                    + "        ON response.user_id = [user].user_id\n"
                    + "WHERE thread_id = ?";
            statement = connection.prepareStatement(queryResponseByThreadId);
            statement.setInt(1, threadId);
            resultSet = statement.executeQuery();
            // 取得したデータでresponseインスタンスを作成してリストに格納する
            ArrayList<Response> responseList = new ArrayList<>();
            while (resultSet.next()) {
                // 取得したデータを変数に格納する
                int userId = resultSet.getInt("user_id");
                String userName = resultSet.getString("user_name");
                String  description = resultSet.getString("description");
                String postedDate = resultSet.getString("posted_date");
                String update = resultSet.getString("update_day");
                boolean deleteFlag = resultSet.getBoolean("delete_flag");
                boolean report = resultSet.getBoolean("report");
                Response response = new Response.Builder(resultSet.getInt("response_id"))
                       .with(arg -> {
                           arg.userId = userId;
                           arg.userName = userName;
                           arg.threadId = threadId;
                           arg.description = description;
                           arg.postedDate = postedDate;
                           arg.update = update;
                           arg.deleteFlag = deleteFlag;
                           arg.report = report;
                       })
                       .build();
                responseList.add(response);
            }
            return new ThreadWithResponseList(thread, responseList);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
