package Models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;


public class CellViewModel {
    public String userName;
    public String lastMessage;
    public SimpleStringProperty time;
    public SimpleStringProperty notificationsNumber;
    public Image avatarImage;
    public ObservableList<MessageViewModel> messagesList;

    public CellViewModel(String userName, String lastMessage, String time, String notificationsNumber, Image avatarImage) {
        this.userName = userName;
        this.lastMessage = lastMessage;
        this.time = new SimpleStringProperty(time);
        this.notificationsNumber = new SimpleStringProperty(notificationsNumber);
        this.avatarImage = avatarImage;
        messagesList = FXCollections.observableArrayList();
    }

    //region Getters & Setters

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    public SimpleStringProperty timeProperty() {
        return time;
    }

    public String getNotificationsNumber() {
        return notificationsNumber.get();
    }

    public SimpleStringProperty notificationsNumberProperty() {
        return notificationsNumber;
    }

    public void setNotificationsNumber(String notificationsNumber) {
        this.notificationsNumber.set(notificationsNumber);
    }

    public Image getAvatarImage() {
        return avatarImage;
    }

    public void setAvatarImage(Image avatarImage) {
        this.avatarImage = avatarImage;
    }

    //endregion
}
