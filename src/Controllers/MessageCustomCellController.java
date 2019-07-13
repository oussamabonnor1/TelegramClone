package Controllers;

import Models.MessageViewModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.GridPane;

import java.io.IOException;

public class MessageCustomCellController extends ListCell<MessageViewModel> {

    @FXML
    private GridPane root;

    @FXML
    private Label messageLabel;

    @FXML
    private Label messageTimeLabel;

    @Override
    protected void updateItem(MessageViewModel item, boolean empty) {
        super.updateItem(item, empty);
        if (empty || item == null) {
            setText(null);
            setGraphic(null);
        } else {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(item.isOutgoing ?
                    "../Views/outgoing_message_custom_cell_view.fxml"
                    : "../Views/incoming_message_custom_cell_view.fxml"));
            fxmlLoader.setController(this);
            try {
                fxmlLoader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            messageTimeLabel.setText(item.getTime());
            messageLabel.setText(item.getMessage());
            setGraphic(root);
        }
    }
}