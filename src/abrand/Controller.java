package abrand;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {


    @FXML
    private TextArea textArea;
    private Stage stage;
    private final FileChooser fileChooser = new FileChooser();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        fileChooser
                .getExtensionFilters()
                .addAll(
                        new FileChooser.ExtensionFilter("Text", "*.txt"),
                        new FileChooser.ExtensionFilter("All Files", "*.*"));
    }

    public void init(Stage myStage) {
        this.stage = myStage;
    }

    @FXML
    public void exit() {
        if (textArea.getLength() > 0) {
            Alert alert =
                    new Alert(
                            Alert.AlertType.NONE,
                            "Exit without saving?",
                            ButtonType.YES,
                            ButtonType.NO,
                            ButtonType.CANCEL);

            alert.setTitle("Confirm");
            alert.showAndWait();

            if (alert.getResult() == ButtonType.NO) {
                save();
            } else if (alert.getResult() == ButtonType.YES) {
                Platform.exit();
            }
        } else {
            Platform.exit();
        }
    }

    @FXML
    private void save() {
        try {
            fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
            fileChooser.setTitle("Save As");
            File file = fileChooser.showSaveDialog(stage);

            if (file != null) {
                PrintWriter savedText = new PrintWriter(file);
                BufferedWriter out = new BufferedWriter(savedText);
                out.write(textArea.getText());
                out.close();
            }
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
    }

    @FXML
    public void openFile() {
        fileChooser.setTitle("Open File");
        File file = fileChooser.showOpenDialog(stage);

        if (file != null) {
            textArea.clear();
            readText(file);
        }
    }

    private void readText(File file) {
        String text;

        try {
            BufferedReader buffReader = new BufferedReader(new FileReader(file));
            while ((text = buffReader.readLine()) != null) {
                textArea.appendText(text + "\n");
            }
            buffReader.close();
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
    }

    @FXML
    public void newFile() {
        textArea.clear();
    }

    @FXML
    public void about() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle("About");
        alert.setHeaderText("A project just for fun written by abrand.");
        alert.setContentText("github.com/abrandell");

        alert.showAndWait();
    }

    @FXML
    public void fontSize(ActionEvent e) {
        String choice = ((CheckMenuItem) e.getSource()).getId();

        switch (choice) {
            case "small":
                textArea.setStyle("-fx-font-size: 14px");
                break;
            case "average":
                textArea.setStyle("-fx-font-size: 22px");
                break;
            case "large":
                textArea.setStyle("-fx-font-size: 30px");
                break;
            default:
                textArea.setStyle("-fx-font-size: 22px");
        }
    }
}
