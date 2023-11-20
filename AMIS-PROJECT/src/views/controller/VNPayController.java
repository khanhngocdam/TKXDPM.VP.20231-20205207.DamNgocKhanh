package views.controller;

import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class VNPayController {
    private final Stage stage;
    public VNPayController(Stage stage) {
        this.stage = stage;

        WebView browser = new WebView();
        WebEngine webEngine = browser.getEngine();
        webEngine.load("http://sandbox.vnpayment.vn/tryitnow/Home/CreateOrder");
        VBox web = new VBox();
        web.getChildren().add(browser);

        Scene scene = new Scene(web);
        stage.setScene(scene);

        this.stage.show();
    }
}
