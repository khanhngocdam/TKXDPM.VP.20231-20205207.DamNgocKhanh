package views.controller;

import controllers.PlaceOrderController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import models.cart.Cart;
import models.cart.CartMedia;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.List;
import java.util.ResourceBundle;
import javafx.scene.control.Label;

public class CartController {
    @FXML
    private AnchorPane mainAnchorPane;
    @FXML
    private Label labelSubtotal;
    @FXML
    private Label labelVAT;
    @FXML
    private Label labelAmount;

    @FXML
    private VBox vboxCart;

    private HomeController homeController;
    private PlaceOrderController placeOrderController;

    public void requestToPlaceOrder(MouseEvent mouseEvent) {
    }
    public void initialize() {
        vboxCart.getChildren().clear();
        List<CartMedia> lstCartMedia = Cart.getCart().getLstCartMedia();
        int i = 1;
        for (CartMedia cartMedia: lstCartMedia) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/media_cart.fxml"));
                loader.load();
                MediaCartController mediaCartController = loader.getController();
                mediaCartController.setData(cartMedia);
                mediaCartController.setCartController(this);
                vboxCart.getChildren().add(loader.getRoot());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        double subTotal = Cart.getCart().getSubTotal();
        labelSubtotal.setText(subTotal+"");
        labelVAT.setText(subTotal*0.1 + "");
        labelAmount.setText(subTotal * 1.1 + "");
    }

    @FXML
    void backToHome(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/home.fxml"));
            AnchorPane homePane = loader.load();
            // Thay thế nội dung của mainAnchorPane bằng nội dung của màn hình Home
            mainAnchorPane.getChildren().setAll(homePane);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    void requestToPlaceOrder(ActionEvent event) {
        placeOrderController = new PlaceOrderController();
        placeOrderController.placeOrder();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/delivery_info.fxml"));
            AnchorPane homePane = loader.load();
            // Thay thế nội dung của mainAnchorPane bằng nội dung của màn hình Home
            mainAnchorPane.getChildren().setAll(homePane);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
