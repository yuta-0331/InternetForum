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
                        new CreateConnection().getConnection()
        ){
            
            String sql = "SELECT * FROM [genre]";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            ArrayList<Genre> genreList = new ArrayList<>();
            while (resultSet.next()) {
                genreList.add(new Genre(resultSet.getInt("genre_id"), resultSet.getString("genre_name")));
            }
            return genreList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}
