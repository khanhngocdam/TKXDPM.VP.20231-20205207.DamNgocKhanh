package views.handles;

import Subsystem.VNPaySubsystem;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.DeliveryInfo;
import models.Order;
import models.cart.Cart;
import models.cart.CartMedia;
import utils.ConnectDB;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class InvoiceHandle {
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

    public InvoiceHandle(Stage stage, Order order) throws IOException {
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
                MediaInvoiceHandle mediaInvoiceHandle = loader.getController();
                mediaInvoiceHandle.setData(cartMedia);
                mediaInvoiceHandle.setInvoiceController(this);
                vboxItems.getChildren().add(loader.getRoot());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        order.setShippingFee(order.getShippingFee());
        subtotal.setText(order.subTotal()+"");
        shippingFees.setText(order.getShippingFee() + "");
        total.setText(order.totalAmount() + "");
    }

    @FXML
    void confirmInvoice(ActionEvent event) throws SQLException, ClassNotFoundException {
        Stage stage = (Stage) btnConfirmOrder.getScene().getWindow();
        VNPaySubsystem vnPaySubsystem;
        int totalAmount = (int) (order.totalAmount());
        ConnectDB connectDB = new ConnectDB();
        Cart.getCart().getLstCartMedia().clear();
        connectDB.updateMediaHome(order, true);
        vnPaySubsystem = new VNPaySubsystem(stage, totalAmount, order);
    }

    @FXML
    void returnPrevPage(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/delivery_info.fxml"));
            AnchorPane homePane = loader.load();
            // Thay thế nội dung của mainAnchorPane bằng nội dung của màn hình Home
            mainAnchorPane.getChildren().setAll(homePane);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

