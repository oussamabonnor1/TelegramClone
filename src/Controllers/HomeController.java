package Controllers;

import Models.UserViewModel;
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

import static ToolBox.Utilities.getCurrentTime;

public class HomeController implements Initializable {

    @FXML
    private Label userNameLabel;
    @FXML
    private Label chatRoomNameLabel;
    @FXML
    private TextField messageField;
    @FXML
    private ListView<UserViewModel> usersListView;
    @FXML
    private ListView<MessageViewModel> messagesListView;

    NetworkConnection connection;
    private ObservableList<UserViewModel> usersList = FXCollections.observableArrayList();
    UserViewModel currentlySelectedUser, localUser;
    Image userImage = new Image("resources/img/smile.png");

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        usersList.add(new UserViewModel("Oussama", "message ",
                getCurrentTime(), 0 + "", userImage));
        usersList.add(new UserViewModel("Latifa", "message ",
                getCurrentTime(), 0 + "", userImage));
        for (int i = 2; i < 10; i++) {
            usersList.add(new UserViewModel("user " + i, "message " + i,
                    getCurrentTime(), 0 + "", userImage));
        }

        localUser = new UserViewModel(LogInController.userName, "message", getCurrentTime(), 0 + "", userImage);
        userNameLabel.setText(localUser.getUserName());

        usersListView.setItems(usersList);
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
                    messagesListView.scrollTo(currentlySelectedUser.messagesList.size());
                }
        );

        connection = new NetworkConnection(data -> Platform.runLater(() -> {
            String[] messageInfo = data.toString().split(">"); //Sender and Receiver and message
            if (messageInfo[1].matches(localUser.getUserName())) {

                int userSender = findUser(messageInfo[0]);
                usersList.get(userSender).time.setValue(getCurrentTime());
                usersList.get(userSender).lastMessage.setValue(messageInfo[2]);
                usersList.get(userSender).messagesList.add(new MessageViewModel(messageInfo[2], getCurrentTime(), false));
                messagesListView.scrollTo(currentlySelectedUser.messagesList.size());
                usersList.get(userSender).notificationsNumber.setValue((Integer.valueOf(currentlySelectedUser.notificationsNumber.getValue()) + 1) + "");
                System.out.println("Sender: " + usersList.get(userSender).userName
                        + "\n" + "Receiver: " + localUser.getUserName());
            }
        }), "127.0.0.1", true, 55555);
        connection.openConnection();

        usersListView.getSelectionModel().select(0);
    }


    @FXML
    void sendMessage(ActionEvent event) {
        try {
            currentlySelectedUser.messagesList.add(new MessageViewModel(messageField.getText(), getCurrentTime(), true));
            //sending message as: sender>receiver>data
            connection.sendData(localUser.getUserName() + ">" + currentlySelectedUser.getUserName() + ">" + messageField.getText());
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

    int findUser(String userName) {
        for (int i = 0; i < usersList.size(); i++) {
            if (usersList.get(i).getUserName().matches(userName)) {
                return i;
            }
        }
        return -1;
    }

}
