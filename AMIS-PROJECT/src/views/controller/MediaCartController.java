package views.controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import models.cart.Cart;
import models.cart.CartMedia;
import models.media.Media;

import java.util.List;

public class MediaCartController {

    @FXML
    private ImageView image;

    @FXML
    private Button btnDelete;

    @FXML
    private Label price;

    @FXML
    private Label quantity;

    @FXML
    private Label labelOutOfStock;

    @FXML
    private Label title;

    private CartMedia cardMedia;

    private CartController cartController;

    public CartController getCartController() {
        return cartController;
    }

    public void setCartController(CartController cartController) {
        this.cartController = cartController;
    }

    public CartMedia getCardMedia() {
        return cardMedia;
    }

    public void setCardMedia(CartMedia cardMedia) {
        this.cardMedia = cardMedia;
    }


    public void setData(CartMedia cartMedia) {
        setCardMedia(cartMedia);
        Media media = cartMedia.getMedia();
        // Thiết lập dữ liệu từ MediaItem vào các thành phần trong media_home.fxml
        image.setImage(new Image(media.getImageURL()));
        title.setText(media.getTitle());
        price.setText(String.valueOf(cartMedia.getPrice()));
        quantity.setText(cartMedia.getQuantity() + "");
    }

    public void deleteMediaInCart(ActionEvent event) {
        Cart.getCart().removeCartMedia(cardMedia);
        cartController.initialize();
    }
}

