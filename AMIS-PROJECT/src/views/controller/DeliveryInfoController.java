package views.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class DeliveryInfoController {

    @FXML
    private AnchorPane mainAnchorPane;

    @FXML
    private TextField instructions;

    @FXML
    private Label screenTitle;

    @FXML
    private TextField address;

    @FXML
    private ComboBox<String> province;

    @FXML
    private TextField phone;

    @FXML
    private Button btnConfirmDelivery;

    @FXML
    private TextField name;

    @FXML
    void submitDeliveryInfo(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/invoice.fxml"));
            AnchorPane homePane = loader.load();
            // Thay thế nội dung của mainAnchorPane bằng nội dung của màn hình Home
            mainAnchorPane.getChildren().setAll(homePane);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

