package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.schema.Response;

public class FetchResponse {

    public Response fetch(int responseId) {
        try (
                Connection connection = 
                        DriverManager.getConnection("jdbc:sqlserver://localhost\\SQLEXPRESS;database=InternetForum;"
                        + "encrypt=true;trustServerCertificate=true;"
                        + "integratedSecurity=false;user=sa;password=1234;");
                
                ){
            // レスポンスidからresponseの取得
            String sql = "SELECT * FROM response WHERE response_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, responseId);
            ResultSet resultSet = statement.executeQuery();
            Response response = null;
            if (resultSet.next()) {
                // 取得したデータを変数に格納する。
                int userId = resultSet.getInt("user_id");
                int threadId = resultSet.getInt("thread_id");
                String description = resultSet.getString("description");
                String postedDate = resultSet.getString("posted_date");
                String update = resultSet.getString("update_day");
                boolean deleteFlag = resultSet.getBoolean("delete_flag");
                boolean report = resultSet.getBoolean("report");
                // 取得したデータでResponseインスタンスを作成
                response = new Response.Builder(responseId)
                        .with(arg -> {
                            arg.userId = userId;
                            arg.threadId = threadId;
                            arg.description = description;
                            arg.postedDate = postedDate;
                            arg.update = update;
                            arg.deleteFlag = deleteFlag;
                            arg.report = report;
                        }).build();
            }
            
            connection.close();
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
