package views.controller;

import javafx.concurrent.Worker;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

public class VNPayController {
    private final Stage stage;
    public VNPayController(Stage stage) {
        this.stage = stage;
        Label location = new Label();
        WebView browser = new WebView();
        WebEngine webEngine = browser.getEngine();
        webEngine.load(buildUrl(10000000+""));
        webEngine.getLoadWorker().stateProperty().addListener((observable, oldValue, newValue) -> {
            if(Worker.State.SUCCEEDED.equals(newValue)) {
                location.setText(webEngine.getLocation());
            }
        });
        location.textProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue.contains("vnp_ResponseCode=00")) {
                try {
                    ResultController resultController = new ResultController(stage);
                    resultController.show();
                } catch (IOException e) {
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
        System.out.println(vnp_CreateDate);
        String vnp_CurrCode = "vnp_CurrCode=VND&";
        String vnp_IpAddr = "vnp_IpAddr=222.252.10.226&";
        String vnp_Locale = "vnp_Locale=vn&";
        String vnp_OrderInfo = "vnp_OrderInfo=Thanh+toan+don+hang+%3A5&";
        String vnp_OrderType = "vnp_OrderType=other&";
        String vnp_ReturnUrl = "vnp_ReturnUrl=https%3A%2F%2Fsandbox.vnpayment.vn%2Ftryitnow%2FHome%2FVnPayReturn&";
        String vnp_TmnCode = "vnp_TmnCode=ORRJM9BG&";
        String vnp_TxnRef = "vnp_TxnRef=" + Config.getRandomNumber(8) + "&";
        System.out.println(vnp_TxnRef);
        String vnp_Version = "vnp_Version=2.1.0";

        String hashData = vnp_Amount + vnp_BankCode + vnp_Command  + vnp_CreateDate + vnp_CurrCode + vnp_IpAddr + vnp_Locale + vnp_OrderInfo + vnp_OrderType + vnp_ReturnUrl + vnp_TmnCode + vnp_TxnRef + vnp_Version;
        url += hashData;

        String vnp_SecureHash = Config.hmacSHA512(Config.secretKey, hashData);
        url += "&vnp_SecureHash=" + vnp_SecureHash;
        System.out.println(url);
        return url;
    }
}
