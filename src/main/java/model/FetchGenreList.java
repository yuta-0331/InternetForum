package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.schema.Genre;

public class FetchGenreList {
    public ArrayList<Genre> fetch() {
        try (
                Connection connection =
                        DriverManager.getConnection("jdbc:sqlserver://localhost;database=InternetForum;"
                                + "encrypt=true;trustServerCertificate=true;"
                                + "integratedSecurity=false;user=sa;password=Password.1;");
        ){
            
            String sql = "SELECT * FROM [genre]";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            ArrayList<Genre> genreList = new ArrayList<>();
            while (resultSet.next()) {
                genreList.add(new Genre(resultSet.getInt("genre_id"), resultSet.getString("genre_name")));
            }
            connection.close();
            return genreList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}
