package Controllers;

import Models.CellViewModel;
import Models.MessageViewModel;
import ToolBox.NetworkConnection;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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

        usersListView.onMouseClickedProperty().addListener(new ChangeListener<EventHandler<? super MouseEvent>>() {
            @Override
            public void changed(ObservableValue<? extends EventHandler<? super MouseEvent>> observable, EventHandler<? super MouseEvent> oldValue, EventHandler<? super MouseEvent> newValue) {

            }
        });

        connection = new NetworkConnection(data -> Platform.runLater(() -> {
            messagesList.add(new MessageViewModel(data.toString(), "00:00", false));
            messagesListView.scrollTo(messagesList.size());
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
            messagesListView.scrollTo(messagesList.size());
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
