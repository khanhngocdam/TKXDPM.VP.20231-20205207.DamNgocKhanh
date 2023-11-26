package views.handles;

import controllers.PlaceOrderController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import models.cart.Cart;
import models.cart.CartMedia;

import java.io.IOException;
import java.util.List;

import javafx.scene.control.Label;

public class CartHandle {
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

    private HomeHandle homeHandle;
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
                MediaCartHandle mediaCartHandle = loader.getController();
                mediaCartHandle.setData(cartMedia);
                mediaCartHandle.setCartController(this);
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
