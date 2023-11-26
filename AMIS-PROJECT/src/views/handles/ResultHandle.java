package views.handles;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class ResultHandle {
    private Stage stage;
    private Scene scene;
    @FXML
    private Button okButton;
    @FXML
    private AnchorPane mainAnchorPane;
    public ResultHandle(Stage stage) throws IOException {
        this.stage = stage;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/result.fxml"));
        loader.setController(this);
        this.mainAnchorPane = (AnchorPane) loader.load();
        okButton.setOnAction(event -> {
            try {
                backToHome();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
    public void show() {
        if (this.scene == null) {
            this.scene = new Scene(this.mainAnchorPane);
        }
        this.stage.setScene(this.scene);
        this.stage.show();
    }

    public void backToHome() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/home.fxml"));
        AnchorPane homePane = loader.load();

        // Thay thế nội dung của mainAnchorPane bằng nội dung của màn hình cart
        mainAnchorPane.getChildren().setAll(homePane);
    }
}
