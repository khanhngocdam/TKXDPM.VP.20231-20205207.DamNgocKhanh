package views.controller;

import controllers.PlaceOrderController;
import exception.InvalidDeliveryInfoException;
import exception.MediaNotAvailableException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import models.DeliveryInfo;
import models.Order;

import java.io.IOException;

public class DeliveryInfoController {

    @FXML
    private AnchorPane mainAnchorPane;

    @FXML
    private TextField instructions;

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
    private PlaceOrderController placeOrderController;

    public void initialize() {
        // Khởi tạo danh sách các tỉnh/thành phố
        ObservableList<String> provincesList = FXCollections.observableArrayList(
                "Bắc Giang", "Bắc Kạn", "Cao Bằng", "Hà Giang", "Lạng Sơn", "Phú Thọ",
                "Quảng Ninh", "Thái Nguyên", "Tuyên Quang", "Yên Bái", "Điện Biên", "Hòa Bình", "Lai Châu", "Sơn La",
                "Bắc Ninh", "Hà Nam", "Hải Dương", "Hưng Yên", "Nam Định", "Ninh Bình", "Thái Bình", "Vĩnh Phúc", "Hà Nội",
                "Hải Phòng", "Hà Tĩnh", "Nghệ An", "Quảng Bình", "Quảng Trị", "Thanh Hóa", "Thừa Thiên-Huế", "Đắk Lắk",
                "Đắk Nông", "Gia Lai", "Kon Tum", "Lâm Đồng", "Bình Định", "Bình Thuận", "Khánh Hòa", "Ninh Thuận",
                "Phú Yên", "Quảng Nam", "Quảng Ngãi", "Đà Nẵng", "Bà Rịa-Vũng Tàu", "Bình Dương", "Bình Phước", "Đồng Nai",
                "Tây Ninh", "Hồ Chí Minh", "An Giang", "Bạc Liêu", "Bến Tre", "Cà Mau", "Đồng Tháp", "Hậu Giang",
                "Kiên Giang", "Long An", "Sóc Trăng", "Tiền Giang", "Trà Vinh", "Vĩnh Long", "Cần Thơ");

        // Đặt danh sách mục cho ComboBox province
        province.setItems(provincesList);

        // Nếu bạn muốn đặt một giá trị mặc định cho ComboBox, hãy sử dụng:
        province.setValue("Hà Nội");
    }
    @FXML
    void submitDeliveryInfo(MouseEvent event) throws IOException {
        placeOrderController = new PlaceOrderController();
        if (!placeOrderController.validateName(name.getText())) {
            throw new InvalidDeliveryInfoException("Name input invalid, please input name again !");
        }
        if (!placeOrderController.validateAddress(address.getText())) {
            throw new InvalidDeliveryInfoException("Address input invalid, please input address again !");
        }
        if (!placeOrderController.validatePhoneNumber(phone.getText())) {
            throw new InvalidDeliveryInfoException("Phone number input invalid, please input phone number again !");
        }
        DeliveryInfo deliveryInfo = new DeliveryInfo(name.getText(), phone.getText(), province.getValue(), address.getText(), instructions.getText());
        Order order = new Order(deliveryInfo);
        InvoiceController invoiceController = new InvoiceController((Stage)btnConfirmDelivery.getScene().getWindow(), order);
        invoiceController.show();
    }

}

