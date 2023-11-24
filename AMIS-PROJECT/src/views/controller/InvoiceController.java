package views.controller;

import controllers.PlaceOrderController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.DeliveryInfo;
import models.Order;
import models.cart.Cart;
import models.cart.CartMedia;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class InvoiceController  {
    @FXML
    private AnchorPane mainAnchorPane;
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
    private Button btnConfirmOrder;
    @FXML
    private VBox vboxItems;
    private Stage stage;

    private Scene scene;
    private Order order;

    public InvoiceController(Stage stage, Order order) throws IOException {
        this.stage = stage;
        this.order = order;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/invoice.fxml"));
        loader.setController(this);
        this.mainAnchorPane = (AnchorPane) loader.load();
        setInfo();
    }
    public void show() {
        if (this.scene == null) {
            this.scene = new Scene(this.mainAnchorPane);
        }
        this.stage.setScene(this.scene);
        this.stage.show();
    }

    public void setInfo() {
        DeliveryInfo deliveryInfo = this.order.getDeliveryInfo();
        name.setText(deliveryInfo.getName());
        phone.setText(deliveryInfo.getPhone());
        address.setText(deliveryInfo.getAddress());
        province.setText(deliveryInfo.getProvince());
        instructions.setText(deliveryInfo.getInstruction());

        List<CartMedia> lstCartMedia = Cart.getCart().getLstCartMedia();
        int i = 1;
        for (CartMedia cartMedia: lstCartMedia) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/media_invoice.fxml"));
                loader.load();
                MediaInvoiceController mediaInvoiceController = loader.getController();
                mediaInvoiceController.setData(cartMedia);
                mediaInvoiceController.setInvoiceController(this);
                vboxItems.getChildren().add(loader.getRoot());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        PlaceOrderController placeOrderController = new PlaceOrderController();
        double shippingFee = placeOrderController.calculateShippingFee(order);
        order.setShippingFee(shippingFee);
        subtotal.setText(order.subTotal()+"");
        shippingFees.setText(shippingFee + "");
        total.setText(order.totalAmount() + "");
    }

    @FXML
    void confirmInvoice(ActionEvent event) {
        Stage stage = (Stage) btnConfirmOrder.getScene().getWindow();
        VNPayController vnPayController;
        int totalAmount = (int) (order.totalAmount() * 1000);
        vnPayController = new VNPayController(stage, totalAmount);
    }

}

