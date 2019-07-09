package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LogInController implements Initializable {


    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    @FXML
    void closeApp(MouseEvent event) {
        Main.stage.close();
    }

    @FXML
    void minimizeApp(MouseEvent event) {
        Main.stage.setIconified(true);
    }

    @FXML
    void signUp(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../Views/home_view.fxml"));
            Main.stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
