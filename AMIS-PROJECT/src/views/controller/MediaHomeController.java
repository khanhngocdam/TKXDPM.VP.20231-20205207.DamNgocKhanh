package views.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import models.cart.Cart;
import models.cart.CartMedia;
import models.media.Media;

import java.util.List;

public class MediaHomeController {
    @FXML
    private ImageView mediaImage;

    @FXML
    private Label mediaTitle;

    @FXML
    private Label mediaPrice;
    @FXML
    private Label mediaAvail;
    @FXML
    protected Spinner<Integer> spinnerChangeNumber;

    @FXML
    protected Button addToCartBtn;
    @FXML
    private Label soldOut;

    private Media media;

    private HomeController homeController;
    public void setData(Media media) {
        setMedia(media);
        // Thiết lập dữ liệu từ MediaItem vào các thành phần trong media_home.fxml
        mediaImage.setImage(new Image(media.getImageURL()));
        mediaTitle.setText(media.getTitle());
        mediaPrice.setText(String.valueOf(media.getPrice()));
        mediaAvail.setText(media.getQuantity() + "");
        spinnerChangeNumber.setValueFactory(
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1, media.getQuantity(), 1)
        );
    }

    public Media getMedia(){
        return media;
    }

    public void setMedia(Media media){
        this.media = media;
    }

    public Button getAddToCartBtn() {
        return addToCartBtn;
    }

    // Setter để đặt giá trị cho biến tham chiếu đến HomeController
    public void setHomeController(HomeController homeController) {
        this.homeController = homeController;
    }

    @FXML
    public void handleAddToCart(ActionEvent event) {
        int updateQuantity = media.getQuantity()  - spinnerChangeNumber.getValue();
        Cart.getCart().addCartMedia(new CartMedia(media, spinnerChangeNumber.getValue()));
        media.setQuantity(updateQuantity);
        if(updateQuantity>0) {
            spinnerChangeNumber.setValueFactory(
                    new SpinnerValueFactory.IntegerSpinnerValueFactory(1,updateQuantity, 1)
            );
        } else {
            spinnerChangeNumber.setVisible(false);
            addToCartBtn.setVisible(false);
            soldOut.setVisible(true);
        }
        mediaAvail.setText(updateQuantity+"");


        // Gọi phương thức updateNumMediaInCart() từ HomeController
        if (homeController != null) {
            homeController.updateNumMediaInCart();
        }
    }
}
