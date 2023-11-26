package utils;

import models.Invoice;
import models.Order;
import models.cart.CartMedia;
import models.media.Media;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
                listMedia.add(new Media(rs.getInt("id"), rs.getString("title"), (int) rs.getDouble("price") * 1000,
                        rs.getInt("quantity"), rs.getString("imageURL")));
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return listMedia;

    }

    public void updateMediaHome(Order order, boolean execute) throws SQLException {
        List<CartMedia> listOrderMedia = order.getListOrderMedia();

        try (Connection connection = DriverManager.getConnection(JDBC_URL)) {
            connection.setAutoCommit(false);
            try (PreparedStatement preparedStatement = connection.prepareStatement("UPDATE media SET quantity = ? WHERE id = ?")) {
                for (CartMedia cartMedia : listOrderMedia) {
                    int newQuantity = execute ? (cartMedia.getMedia().getQuantity()) :
                            (cartMedia.getMedia().getQuantity() + cartMedia.getQuantity());
                    preparedStatement.setInt(1, newQuantity);
                    preparedStatement.setInt(2, cartMedia.getMedia().getId());
                    preparedStatement.addBatch();
                }

                preparedStatement.executeBatch();
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                throw e;
            }
        }

    }


    public int saveOrder(Order order) throws SQLException {
        connection = DriverManager.getConnection(JDBC_URL);
        Statement statement = connection.createStatement();
        statement.setQueryTimeout(30);
        String queryInsertOrder = "insert into `Order` (email, address, phone, userID, shipping_fee) values('" + "null" + "', '" +
                order.getDeliveryInfo().getAddress() + "', '" + order.getDeliveryInfo().getPhone() + "', " + "0" + ", " + order.getShippingFee() + ")";
        List<CartMedia> listOrderMedia = order.getListOrderMedia();
        statement.executeUpdate(queryInsertOrder);

        int orderID = statement.executeQuery("SELECT last_insert_rowid()").getInt(1);
        for (CartMedia cartMedia: listOrderMedia) {
            String queryInsertOrderMedia = "INSERT INTO `OrderMedia` (mediaID, orderID, price, quantity) VALUES (" +
                    "'" + cartMedia.getMedia().getId() + "', " +  // Assuming mediaID is a string, modify if it's an integer
                    "'" + orderID + "', " +
                    cartMedia.getPrice() + ", " +
                    cartMedia.getQuantity() + ")";
            statement.executeUpdate(queryInsertOrderMedia);
        }
        return orderID;
    }

    public void saveInvoice(Invoice invoice) throws SQLException {
        int orderID = saveOrder(invoice.getOrder());
        connection = DriverManager.getConnection(JDBC_URL);
        Statement statement = connection.createStatement();
        statement.setQueryTimeout(30);
        String query = "INSERT INTO `transaction` (orderID, createAt, content) VALUES (" +
                orderID + ", '" + invoice.getPaymentTransaction().getCreateAt() + "', '" +
                invoice.getPaymentTransaction().getTransactionContent() + "')";

        statement.executeUpdate(query);
    }
}
