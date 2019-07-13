package Controllers;

import Models.CellViewModel;
import Models.MessageViewModel;
import ToolBox.NetworkConnection;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static ToolBox.Utilities.*;
import static ToolBox.Utilities.getCurrentTime;

public class HomeController implements Initializable {

    @FXML
    private Label chatRoomNameLabel;
    @FXML
    private TextField messageField;

    NetworkConnection connection;

    @FXML
    private ListView<CellViewModel> usersListView;
    @FXML
    private ListView<MessageViewModel> messagesListView;

    private ObservableList<CellViewModel> cellsList = FXCollections.observableArrayList();
    CellViewModel currentlySelectedUser;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for (int i = 0; i < 10; i++) {
            cellsList.add(new CellViewModel("user " + i, "message " + i,
                    getCurrentTime(), 0 + "", new Image("resources/img/smile.png")));
        }

        usersListView.setItems(cellsList);
        usersListView.setCellFactory(param -> new UserCustomCellController() {
            {
                prefWidthProperty().bind(usersListView.widthProperty().subtract(0)); // 1
            }
        });
        messagesListView.setCellFactory(param -> new MessageCustomCellController() {
            {
                prefWidthProperty().bind(messagesListView.widthProperty().subtract(0)); // 1
            }
        });
        usersListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                    currentlySelectedUser = usersListView.getSelectionModel().getSelectedItem();
                    messagesListView.setItems(currentlySelectedUser.messagesList);
                    chatRoomNameLabel.setText(currentlySelectedUser.userName);
                }
        );

        connection = new NetworkConnection(data -> Platform.runLater(() -> {
            currentlySelectedUser.time.setValue(getCurrentTime());
            currentlySelectedUser.messagesList.add(new MessageViewModel(data.toString(), getCurrentTime(), false));
            messagesListView.scrollTo(currentlySelectedUser.messagesList.size());
            currentlySelectedUser.notificationsNumber.setValue((Integer.valueOf(currentlySelectedUser.notificationsNumber.getValue()) + 1) + "");
        }), "127.0.0.1", false, 55555);
        connection.openConnection();

        usersListView.getSelectionModel().select(0);
    }

    @FXML
    void sendMessage(ActionEvent event) {
        try {
            currentlySelectedUser.messagesList.add(new MessageViewModel(messageField.getText(), getCurrentTime(), true));
            connection.sendData(messageField.getText());
            messageField.clear();
            messagesListView.scrollTo(currentlySelectedUser.messagesList.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void attachFile(MouseEvent event) {

    }

    @FXML
    void searchChatRoom(MouseEvent event) {

    }

    @FXML
    void settingsButtonClicked(MouseEvent event) {

    }

    @FXML
    void slideMenuClicked(MouseEvent event) {

    }

    @FXML
    void smileyButtonClicked(MouseEvent event) {

    }

    @FXML
    void vocalMessageClicked(MouseEvent event) {

    }

    @FXML
    void closeApp(MouseEvent event) {
        try {
            connection.closeConnection();
            Main.stage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void minimizeApp(MouseEvent event) {
        Main.stage.setIconified(true);
    }


}
