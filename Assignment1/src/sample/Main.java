package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;
import javafx.scene.control.Pagination;
import java.io.FileInputStream;


public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("worksheet1.fxml"));
        Parent root = loader.load();
        root.getStylesheets().add(getClass().getResource("style1.css").toExternalForm());
        //have to cast the return value of getController(), as it returns an object.
        Controller controller=(Controller) loader.getController();
        //now we can pass controller a reference to the stage.
        controller.init(stage);
        stage.setTitle("Image Viewing");


        //incorrect URL format
        //try file chooser

//        Image image = new Image("/Users/Olivia/OneDrive - Waterford Institute of Technology/Pictures/Camera Roll/background1.jpg");
//        ImageView imageView = new ImageView();
//        imageView.setImage(image);
//

        Scene scene = new Scene(root);
       // root.getStylesheets().add(getClass().getResource("style1.css").toExternalForm());
        stage.setScene(scene);
        stage.show();


//        FileInputStream stream = new FileInputStream("C:\\Users\\Olivia\\OneDrive - Waterford Institute of Technology\\Pictures\\Camera Roll\\background1.jpg");
//        Image image = new Image(stream);
//        ImageView imageview = new ImageView(image);
    }

    public static void main(String[] args) {
        launch(args);
    }

}
