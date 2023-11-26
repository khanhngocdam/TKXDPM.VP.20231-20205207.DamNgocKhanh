package views.handles;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import models.cart.Cart;
import models.cart.CartMedia;
import models.media.Media;

public class MediaCartHandle {

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

    private CartHandle cartHandle;

    public CartHandle getCartController() {
        return cartHandle;
    }

    public void setCartController(CartHandle cartHandle) {
        this.cartHandle = cartHandle;
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
        cartHandle.initialize();
    }
}

