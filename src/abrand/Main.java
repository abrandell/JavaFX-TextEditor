package abrand;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage myStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("View.fxml"));

        Parent root = fxmlLoader.load();
        Controller controller = fxmlLoader.getController();
        controller.init(myStage);

        myStage.setTitle("JavaFX Text Editor");
        myStage.setScene(new Scene(root, 700, 500));
        myStage.show();
    }
}
