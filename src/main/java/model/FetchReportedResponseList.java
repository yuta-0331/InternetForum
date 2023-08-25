package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.schema.Response;

public class FetchReportedResponseList {
    public ArrayList<Response> fetch() {
        try (
                Connection connection =
                        DriverManager.getConnection("jdbc:sqlserver://localhost;database=InternetForum;"
                                + "encrypt=true;trustServerCertificate=true;"
                                + "integratedSecurity=false;user=sa;password=Password.1;");
        ){
            // レスポンスidからresponseの取得
            String sql = "SELECT * FROM response WHERE report = 1";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            ArrayList<Response> responseList = new ArrayList<>();
            while (resultSet.next()) {
                // 取得したデータを変数に格納する。
                int responseId = resultSet.getInt("response_id");
                int userId = resultSet.getInt("user_id");
                int threadId = resultSet.getInt("thread_id");
                String description = resultSet.getString("description");
                String postedDate = resultSet.getString("posted_date");
                String update = resultSet.getString("update_day");
                boolean deleteFlag = resultSet.getBoolean("delete_flag");
                boolean report = resultSet.getBoolean("report");
                // 取得したデータでResponseインスタンスを作成
                Response response = new Response.Builder(responseId)
                        .with(arg -> {
                            arg.userId = userId;
                            arg.threadId = threadId;
                            arg.description = description;
                            arg.postedDate = postedDate;
                            arg.update = update;
                            arg.deleteFlag = deleteFlag;
                            arg.report = report;
                        }).build();
                responseList.add(response);
            }
            
            connection.close();
            return responseList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
