package Controllers;

import Models.MessageViewModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.io.IOException;

public class MessageCustomCellController extends ListCell<MessageViewModel> {

    @FXML
    private GridPane root;

    @FXML
    private ImageView imageView;

    @FXML
    private Label messageLabel;

    @FXML
    private Label messageTimeLabel;

    @Override
    protected void updateItem(MessageViewModel item, boolean empty) {
        super.updateItem(item, empty);
        FXMLLoader fxmlLoader;
        if (empty || item == null) {
            setText(null);
            setGraphic(null);
        } else {
            if (item.isOutgoing) {
                if (item.isImage) {
                    fxmlLoader = new FXMLLoader(getClass().getResource("../Views/outgoing_image_custom_cell_view.fxml"));
                } else {
                    fxmlLoader = new FXMLLoader(getClass().getResource("../Views/outgoing_message_custom_cell_view.fxml"));
                }
            } else {
                if (item.isImage) {
                    fxmlLoader = new FXMLLoader(getClass().getResource("../Views/incoming_image_custom_cell_view.fxml"));
                } else {
                    fxmlLoader = new FXMLLoader(getClass().getResource("../Views/incoming_message_custom_cell_view.fxml"));
                }
            }
            fxmlLoader.setController(this);
            try {
                fxmlLoader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            messageTimeLabel.setText(item.getTime());
            if (item.isImage) imageView.setImage(item.getImage());
            else messageLabel.setText(item.getMessage());
            setGraphic(root);
        }
    }
}