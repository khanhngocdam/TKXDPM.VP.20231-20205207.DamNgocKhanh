package utils;

import models.media.Media;

import java.sql.*;
import java.util.ArrayList;

import java.util.List;

public class ConnectDB {
    private static final String JDBC_URL = "jdbc:sqlite:./src/assets/db/aims.db";
    private Connection connection = null;

    public List getAllListMedia() throws SQLException {
        List<Media> listMedia = null;
        try {
            Class.forName("org.sqlite.JDBC");
            listMedia = new ArrayList<>();
            connection = DriverManager.getConnection(JDBC_URL);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            ResultSet rs = statement.executeQuery("select * from media");
            while (rs.next()) {
                listMedia.add(new Media(rs.getInt("id"), rs.getString("title"), rs.getDouble("price"),
                        rs.getInt("quantity"), rs.getString("imageURL")));
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return listMedia;

    }
}
