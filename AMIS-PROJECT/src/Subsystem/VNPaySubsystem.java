package Subsystem;

import javafx.concurrent.Worker;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import models.Invoice;
import models.Order;
import models.payment.PaymentTransaction;
import utils.ConnectDB;
import views.handles.Config;
import views.handles.ResultHandle;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.TimeZone;

public class VNPaySubsystem {
    private final Stage stage;
    private int successfulLoadCount = 0;
    public VNPaySubsystem(Stage stage, int totalAmount, Order order) {
        this.stage = stage;
        Label location = new Label();
        WebView browser = new WebView();
        WebEngine webEngine = browser.getEngine();
        System.out.println(totalAmount);
        webEngine.load(buildUrl(totalAmount+""));


        webEngine.getLoadWorker().stateProperty().addListener((observable, oldValue, newValue) -> {
            if(Worker.State.SUCCEEDED.equals(newValue)) {
                successfulLoadCount++;
                System.out.println(successfulLoadCount);
                location.setText(webEngine.getLocation());
            }
        });
        location.textProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue.contains("vnp_ResponseCode=00")) {
                try {
                    ResultHandle resultHandle = new ResultHandle(stage);
                    resultHandle.show();
                    // Lấy thời gian hiện tại
                    LocalDateTime currentTime = LocalDateTime.now();

                    // Định dạng thời gian thành chuỗi
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    String formattedTime = currentTime.format(formatter);
                    PaymentTransaction paymentTransaction = new PaymentTransaction("Successfull - Total amount : " +  totalAmount,formattedTime);
                    Invoice invoice = new Invoice(order, paymentTransaction, totalAmount);
                    ConnectDB connectDB = new ConnectDB();
                    connectDB.saveInvoice(invoice);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        VBox web = new VBox();
        web.getChildren().add(browser);

        Scene scene = new Scene(web);
        stage.setScene(scene);
        this.stage.show();
    }
    public static String buildUrl(String amount) {
        String url = "https://sandbox.vnpayment.vn/paymentv2/vpcpay.html?";
        String vnp_Amount = "vnp_Amount=" + amount + "00&";
        String vnp_BankCode = "vnp_BankCode=NCB&";
        String vnp_Command = "vnp_Command=pay&";
        Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));

        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String vnp_CreateDate = "vnp_CreateDate=" + formatter.format(cld.getTime()) + "&";

        String vnp_CurrCode = "vnp_CurrCode=VND&";
        String vnp_IpAddr = "vnp_IpAddr=222.252.10.226&";
        String vnp_Locale = "vnp_Locale=vn&";
        String vnp_OrderInfo = "vnp_OrderInfo=Thanh+toan+don+hang+%3A5&";
        String vnp_OrderType = "vnp_OrderType=other&";
        String vnp_ReturnUrl = "vnp_ReturnUrl=https%3A%2F%2Fsandbox.vnpayment.vn%2Ftryitnow%2FHome%2FVnPayReturn&";
        String vnp_TmnCode = "vnp_TmnCode=ORRJM9BG&";
        String vnp_TxnRef = "vnp_TxnRef=" + views.handles.Config.getRandomNumber(8) + "&";
        String vnp_Version = "vnp_Version=2.1.0";

        String hashData = vnp_Amount + vnp_BankCode + vnp_Command  + vnp_CreateDate + vnp_CurrCode + vnp_IpAddr + vnp_Locale + vnp_OrderInfo + vnp_OrderType + vnp_ReturnUrl + vnp_TmnCode + vnp_TxnRef + vnp_Version;
        url += hashData;
        String vnp_SecureHash = views.handles.Config.hmacSHA512(Config.secretKey, hashData);
        url += "&vnp_SecureHash=" + vnp_SecureHash;

        return url;
    }
}
