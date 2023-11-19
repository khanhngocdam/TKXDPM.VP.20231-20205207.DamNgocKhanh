package views.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
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
    private Label labelSubtotal;
    @FXML
    private Label labelVAT;
    @FXML
    private Label labelAmount;

    @FXML
    private VBox vboxCart;

    public void requestToPlaceOrder(MouseEvent mouseEvent) {
    }
    public void initialize() {
        vboxCart.getChildren().clear();
        List<CartMedia> lstCartMedia = Cart.getCart().getLstCartMedia();
        int i = 1;
        double subTotal = 0.0;
        for (CartMedia cartMedia: lstCartMedia) {
            subTotal += cartMedia.getPrice();
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
        labelSubtotal.setText(subTotal+"");
        labelVAT.setText(subTotal*0.1 + "");
        labelAmount.setText(subTotal * 1.1 + "");
    }



}
