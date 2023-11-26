package views.handles;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import models.cart.CartMedia;
import models.media.Media;

public class MediaInvoiceHandle {
    @FXML
    private ImageView image;
    @FXML
    private Label price;
    @FXML
    private Label title;
    @FXML
    private Label numOfProd;
    private CartMedia cardMedia;
     private InvoiceHandle invoiceHandle;

    public void setCardMedia(CartMedia cardMedia) {
        this.cardMedia = cardMedia;
    }

    public void setInvoiceController(InvoiceHandle invoiceHandle) {
        this.invoiceHandle = invoiceHandle;
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
