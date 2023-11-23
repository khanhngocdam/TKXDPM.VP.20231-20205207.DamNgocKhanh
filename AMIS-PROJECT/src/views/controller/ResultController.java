package views.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class ResultController {
    private Stage stage;
    private Scene scene;
    @FXML
    private AnchorPane mainAnchorPane;
    public ResultController(Stage stage) throws IOException {
        this.stage = stage;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/result.fxml"));
        loader.setController(this);
        this.mainAnchorPane = (AnchorPane) loader.load();
    }
    public void show() {
        if (this.scene == null) {
            this.scene = new Scene(this.mainAnchorPane);
        }
        this.stage.setScene(this.scene);
        this.stage.show();
    }
}
