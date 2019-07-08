package Controllers;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {
    public static Stage stage;
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../Views/login_view.fxml"));
        primaryStage.setScene(new Scene(root, 1280, 720));
        primaryStage.initStyle(StageStyle.UNDECORATED);
        stage = primaryStage;
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
