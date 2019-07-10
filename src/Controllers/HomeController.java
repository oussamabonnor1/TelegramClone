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
    private ObservableList<MessageViewModel> messagesList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for (int i = 0; i < 10; i++) {
            cellsList.add(new CellViewModel("user " + i, "message " + i,
                    "0" + i + ":00", i + "", new Image("resources/img/smile.png")));
        }
        for (int i = 0; i < 5; i++) {
            messagesList.add(new MessageViewModel("message " + i, "0" + i + ":00", i % 2 == 1));
        }
        usersListView.setItems(cellsList);
        usersListView.setCellFactory(param -> new UserCustomCellController() {
            {
                prefWidthProperty().bind(usersListView.widthProperty().subtract(0)); // 1
            }
        });

        messagesListView.setItems(messagesList);
        messagesListView.setCellFactory(param -> new MessageCustomCellController() {
            {
                prefWidthProperty().bind(messagesListView.widthProperty().subtract(0)); // 1
            }
        });

        connection = new NetworkConnection(data -> Platform.runLater(() -> {
            messagesList.add(new MessageViewModel(data.toString(), "00:00", false));
        }), "127.0.0.1", false, 55555);
        connection.openConnection();

        chatRoomNameLabel.setText(connection.isServer ? "Oussama Bonnor" : "Amine Smahi");
    }

    @FXML
    void sendMessage(ActionEvent event) {
        try {
            System.out.println(connection.isServer);
            messagesList.add(new MessageViewModel(messageField.getText(), "00:00", true));
            connection.sendData(messageField.getText());
            messageField.clear();
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
