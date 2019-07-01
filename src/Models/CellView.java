package Models;

public class CellView {
    public String userName;
    public String lastMessage;
    public String time;
    public String notificationsNumber;

    public CellView(String userName, String lastMessage, String time, String notificationsNumber) {
        this.userName = userName;
        this.lastMessage = lastMessage;
        this.time = time;
        this.notificationsNumber = notificationsNumber;
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

    //endregion
}
