import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import models.cart.Cart;
import models.cart.CartMedia;
import models.media.Media;
import org.junit.platform.engine.support.descriptor.FileSystemSource;
public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        // Load the FXML file
        Parent root = FXMLLoader.load(getClass().getResource("/views/home.fxml"));
        // Set the stage title
        primaryStage.setTitle("AIMS Shop");

        // Set the scene
        Scene scene = new Scene(root, 1326, 788); // Set width and height as per your requirement
        primaryStage.setScene(scene);

        // Show the stage
        primaryStage.show();
    }
    public static void main(String[] args) {
//        Cart myCart = Cart.getCart();
//        Media media1 = new Media(1, "MEDIA1", 3.4);
//        Media media2 = new Media(2, "MEDIA2", 4.3);
//        CartMedia cartMedia1 = new CartMedia(media1, 2);
//        CartMedia cartMedia2 = new CartMedia(media2, 3);
//        myCart.addCartMedia(cartMedia1);
//        myCart.addCartMedia(cartMedia2);
//        System.out.println(myCart.toString());
//        myCart.removeCartMedia(cartMedia1);
//        System.out.println(myCart.toString());
            launch(args);
    }
}