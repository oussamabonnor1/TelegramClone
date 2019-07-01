package Models;

import javafx.scene.image.Image;


public class CellViewModel {
    public String userName;
    public String lastMessage;
    public String time;
    public String notificationsNumber;
    public Image avatarImage;

    public CellViewModel(String userName, String lastMessage, String time, String notificationsNumber, Image avatarImage) {
        this.userName = userName;
        this.lastMessage = lastMessage;
        this.time = time;
        this.notificationsNumber = notificationsNumber;
        this.avatarImage = avatarImage;
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getNotificationsNumber() {
        return notificationsNumber;
    }

    public void setNotificationsNumber(String notificationsNumber) {
        this.notificationsNumber = notificationsNumber;
    }

    public Image getAvatarImage() {
        return avatarImage;
    }

    public void setAvatarImage(Image avatarImage) {
        this.avatarImage = avatarImage;
    }

    //endregion
}
