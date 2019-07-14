package Controllers;

import Models.UserViewModel;
import Models.MessageViewModel;
import ToolBox.NetworkConnection;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
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
            Image image = null;
            String[] messageInfo = data.toString().split(">"); //Data type > Sender and Receiver and data
            if (messageInfo[2].matches(localUser.getUserName())) {
                if (messageInfo[0].matches("image")) {
                    image = new Image((InputStream) data);
                }
                int userSender = findUser(messageInfo[1]);
                usersList.get(userSender).time.setValue(getCurrentTime());
                if (messageInfo[4].matches("null")) {
                    usersList.get(userSender).lastMessage.setValue(messageInfo[3]);
                }
                usersList.get(userSender).messagesList.add(new MessageViewModel(messageInfo[3], getCurrentTime(), false, image == null, image));
                messagesListView.scrollTo(currentlySelectedUser.messagesList.size());
                usersList.get(userSender).notificationsNumber.setValue((Integer.valueOf(currentlySelectedUser.notificationsNumber.getValue()) + 1) + "");
                System.out.println("Sender: " + usersList.get(userSender).userName
                        + "\n" + "Receiver: " + localUser.getUserName());
            }
        }), "127.0.0.1", false, 55555);
        connection.openConnection();

        usersListView.getSelectionModel().select(0);
    }


    @FXML
    void sendMessage(ActionEvent event) {
        try {
            currentlySelectedUser.messagesList.add(new MessageViewModel(messageField.getText(), getCurrentTime(), true, false, null));
            //sending message as: data type > sender > receiver > data
            connection.sendData("text>" + localUser.getUserName() + ">" + currentlySelectedUser.getUserName() + ">" + messageField.getText());
            messageField.clear();
            messagesListView.scrollTo(currentlySelectedUser.messagesList.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void attachFile(MouseEvent event) {
        try {
            FileChooser fileChooser = new FileChooser();
            File imageFile = fileChooser.showOpenDialog(new Stage());
            BufferedImage bufferedImage = ImageIO.read(imageFile);
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            connection.sendImage(image);
        } catch (IOException e) {
            e.printStackTrace();
        }

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
