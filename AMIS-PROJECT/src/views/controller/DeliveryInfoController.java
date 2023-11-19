package views.controller;

import javafx.fxml.FXML;
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

    }

}

