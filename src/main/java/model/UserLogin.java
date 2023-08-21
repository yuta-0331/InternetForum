package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserLogin {
    private int userId;
    private String userName;
    private Integer adminId;
    private boolean deleteFlag;
    public boolean login(String email, String password) {
        try (
                Connection connection = 
                        DriverManager.getConnection("jdbc:sqlserver://localhost\\SQLEXPRESS;database=InternetForum;"
                        + "encrypt=true;trustServerCertificate=true;"
                        + "integratedSecurity=false;user=sa;password=1234;");
                ){
            
            String loginSql = "SELECT "
                    + "    [user].user_id, user_name, hashed_password, admin_id, delete_flag "
                    + "FROM "
                    + "[user] "
                    + "LEFT JOIN admin"
                    + "        ON [user].user_id = admin.user_id "
                    + "WHERE"
                    + "    mail_address = ?";
            PreparedStatement statement = connection.prepareStatement(loginSql);
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            // メールアドレスが未登録の場合、falseを返してログイン失敗処理
            if (!resultSet.next()) {
                return false;
            }
            // 取得したデータを変数に格納する
            userId = resultSet.getInt("user_id");
            userName = resultSet.getString("user_name");
            adminId = resultSet.getInt("admin_id");
            deleteFlag = resultSet.getBoolean("delete_flag");
            String hashPassword = resultSet.getString("hashed_password");
            // 引数のパスワードをハッシュ化したものが、ハッシュパスワードと一致するかチェック
            // 一致したらtrueを返してログイン成功
            HashPasswordUtil util = new HashPasswordUtil();
            if (util.checkHash(hashPassword, util.create(password))) {
                return true;
            }
            connection.close();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public int getUserId() {
        return userId;
    }
    public String getUserName() {
        return userName;
    }
    public Integer getAdminId() {
        return adminId;
    }
    public boolean isDeleteFlag() {
        return deleteFlag;
    }
}
