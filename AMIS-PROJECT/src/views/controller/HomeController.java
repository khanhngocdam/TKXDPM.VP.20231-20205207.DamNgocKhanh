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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.cart.Cart;
import models.media.Book;
import models.media.Media;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HomeController {

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

    private List<Media> lstMedia;


    public void setListMedia() {
        // Khởi tạo một đối tượng Random
        Random random = new Random();

        lstMedia = new ArrayList<>();
        //
        for(int i = 1; i <= 12; i++) {
            int avaiNumMedia = random.nextInt(21) + 30;
            Media media = new Media(i, "Book " + i, 2.5, avaiNumMedia, "/assets/images/book/book" + i + ".jpg" );
            lstMedia.add(media);
        }
    }
    public void initialize() {

        setListMedia();
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

    @FXML
    public void viewCartHandle(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/cart.fxml"));
            Parent cartRoot = loader.load();

            // Access the CartController to perform any initialization or pass data
            CartController cartController = loader.getController();
            Stage cartStage = new Stage();
            cartStage.setTitle("Shopping Cart");
            cartStage.setScene(new Scene(cartRoot));
            cartStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

