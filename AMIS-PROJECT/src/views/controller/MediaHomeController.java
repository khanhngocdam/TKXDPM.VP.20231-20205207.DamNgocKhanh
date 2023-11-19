package views.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    protected Spinner<Integer> spinnerChangeNumber;

    @FXML
    protected Button addToCartBtn;

    private Media media;
    public void setData(Media media) {
        setMedia(media);
        // Thiết lập dữ liệu từ MediaItem vào các thành phần trong media_home.fxml
        mediaImage.setImage(new Image(media.getImageURL()));
        mediaTitle.setText(media.getTitle());
        mediaPrice.setText(String.valueOf(media.getPrice()));
        spinnerChangeNumber.setValueFactory(
                new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 1)
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

    @FXML
    public void handleAddToCart(ActionEvent event) {

    }
}
