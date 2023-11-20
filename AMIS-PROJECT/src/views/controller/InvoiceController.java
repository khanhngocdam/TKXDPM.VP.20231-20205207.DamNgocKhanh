package views.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class InvoiceController {

    @FXML
    private Label instructions;

    @FXML
    private Label shippingFees;

    @FXML
    private Label total;

    @FXML
    private Label address;

    @FXML
    private Label province;

    @FXML
    private Label phone;

    @FXML
    private Label pageTitle;

    @FXML
    private Label subtotal;

    @FXML
    private Label name;

    @FXML
    private ScrollPane vboxIte;

    @FXML
    private Button btnConfirmOrder;

    @FXML
    void confirmInvoice(ActionEvent event) {
        Stage stage = (Stage) btnConfirmOrder.getScene().getWindow();
        VNPayController vnPayController = new VNPayController(stage);

    }

}

