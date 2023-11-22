package views.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import models.cart.CartMedia;
import models.media.Media;

public class MediaInvoiceController{
    @FXML
    private ImageView image;
    @FXML
    private Label price;
    @FXML
    private Label title;
    @FXML
    private Label numOfProd;
    private CartMedia cardMedia;
     private InvoiceController invoiceController;

    public void setCardMedia(CartMedia cardMedia) {
        this.cardMedia = cardMedia;
    }

    public void setInvoiceController(InvoiceController invoiceController) {
        this.invoiceController = invoiceController;
    }

    public void setData(CartMedia cartMedia) {
        setCardMedia(cartMedia);
        Media media = cartMedia.getMedia();
        // Thiết lập dữ liệu từ MediaItem vào các thành phần trong media_home.fxml
        image.setImage(new Image(media.getImageURL()));
        title.setText(media.getTitle());
        price.setText(String.valueOf(cartMedia.getPrice()));
        numOfProd.setText(cartMedia.getQuantity() + "");
    }


}
