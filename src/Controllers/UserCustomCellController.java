package Controllers;

import Models.UserViewModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class UserCustomCellController extends ListCell<UserViewModel> {

    @FXML
    private GridPane root;

    @FXML
    private ImageView avatarImage;

    @FXML
    private Label userNameLabel;

    @FXML
    private Label lastMessageLabel;

    @FXML
    private Label messageTimeLabel;

    @FXML
    private Label nombreMessageLabel;

    @FXML
    private StackPane notificationPanel;

    @Override
    protected void updateItem(UserViewModel item, boolean empty) {
        super.updateItem(item, empty);
        if (empty || item == null) {
            setText(null);
            setGraphic(null);
        } else {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../Views/user_custom_cell_view.fxml"));
            fxmlLoader.setController(this);
            try {
                fxmlLoader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            userNameLabel.setText(String.valueOf(item.getUserName()));
            lastMessageLabel.setText(String.valueOf(item.getLastMessage()));
            messageTimeLabel.textProperty().bind(item.time);
            if (!item.getNotificationsNumber().matches("0")) {
                nombreMessageLabel.textProperty().bind(item.notificationsNumberProperty());
                if (!notificationPanel.isVisible()) notificationPanel.setVisible(true);
            }
            setGraphic(root);
        }
    }
}