package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class LogInController {

    @FXML
    void closeApp(MouseEvent event) {

    }

    @FXML
    void minimizeApp(MouseEvent event) {

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
