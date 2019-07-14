package Models;

import javafx.scene.image.Image;

public class MessageViewModel {
    Image image;
    String message;
    String time;
    public boolean isOutgoing;
    public boolean isImage;

    public MessageViewModel(String message, String time, boolean isOutgoing, boolean isImage, Image image) {
        this.message = message;
        this.time = time;
        this.isOutgoing = isOutgoing;
        this.isImage = isImage;
        this.image = image;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean isOutgoing() {
        return isOutgoing;
    }

    public void setOutgoing(boolean outgoing) {
        isOutgoing = outgoing;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
