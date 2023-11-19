package views.controller;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import models.media.Book;
import models.media.Media;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HomeController {

    @FXML
    private VBox vboxMedia1;

    @FXML
    private VBox vboxMedia2;

    @FXML
    private VBox vboxMedia3;
    @FXML
    private HBox hboxMedia;

    @FXML
    private SplitMenuButton splitMenuBtnSearch;

    private List<Media> lstMedia;


    public void setListMedia() {
        lstMedia = new ArrayList<>();
        //
        for(int i = 1; i <= 12; i++) {
            Media media = new Media(i, "Book " + i, 2.5, "/assets/images/book/book" + i + ".jpg" );
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


        // Vòng lặp để thêm media_home.fxml vào các cột theo từng hàng
        int i = 0;
        while (i < lstMedia.size()) {
           VBox vboxColumn = vboxColumns.get(i%3);
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/media_home.fxml"));
                    loader.load();
                    MediaHomeController mediaHomeController = loader.getController();
                    mediaHomeController.setData(lstMedia.get(i));
                    vboxColumn.getChildren().add(loader.getRoot());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            i++;
        }
    }
}

