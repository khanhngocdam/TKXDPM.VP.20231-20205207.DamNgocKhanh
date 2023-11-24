package views.controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.cart.Cart;
import models.cart.CartMedia;
import models.media.Book;
import models.media.Media;
import utils.ConnectDB;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HomeController {
    @FXML
    private AnchorPane mainAnchorPane;

    @FXML
    private Label numMediaInCart;
    @FXML
    private VBox vboxMedia1;

    @FXML
    private VBox vboxMedia2;

    @FXML
    private VBox vboxMedia3;
    @FXML
    private VBox vboxMedia4;
    @FXML
    private HBox hboxMedia;

    @FXML
    private SplitMenuButton splitMenuBtnSearch;

    private static List<Media> lstMedia = null;


    public void initialize() throws SQLException {

            lstMedia = new  ConnectDB().getAllListMedia();
            if (Cart.getCart().getLstCartMedia().size() != 0) {
                updateMedia(Cart.getCart().getLstCartMedia());
            }
        // Tạo danh sách VBox để lưu trữ các cột
        List<VBox> vboxColumns = new ArrayList<>();
        vboxColumns.add(vboxMedia1);
        vboxColumns.add(vboxMedia2);
        vboxColumns.add(vboxMedia3);
        vboxColumns.add(vboxMedia4); // Thêm vào danh sách


        // Vòng lặp để thêm media_home.fxml vào các cột theo từng hàng
        int i = 0;
        while (i < lstMedia.size()) {
            VBox vboxColumn = vboxColumns.get(i%4);
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/media_home.fxml"));
                loader.load();
                MediaHomeController mediaHomeController = loader.getController();
                mediaHomeController.setData(lstMedia.get(i));
                mediaHomeController.setHomeController(this);
                vboxColumn.getChildren().add(loader.getRoot());
            } catch (IOException e) {
                e.printStackTrace();
            }
            i++;
        }
        numMediaInCart.setText(Cart.getCart().getLstCartMedia().size() + " media");
        }


    public void updateNumMediaInCart() {
        this.numMediaInCart.setText(Cart.getCart().getLstCartMedia().size() + " media");
    }
    public void updateMedia(List<CartMedia> listCartMedia) {
        for (Media m: lstMedia) {
            for (CartMedia cartMedia : listCartMedia) {
                if (cartMedia.getMedia().getId() == m.getId()) {
                    m.setQuantity(m.getQuantity() - cartMedia.getQuantity());
                }
            }
        }
    }
    @FXML
    public void viewCartHandle(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/cart.fxml"));
            AnchorPane cartPane = loader.load();

            // Thay thế nội dung của mainAnchorPane bằng nội dung của màn hình cart
            mainAnchorPane.getChildren().setAll(cartPane);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

