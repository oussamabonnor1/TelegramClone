package Models;

public class MessageViewModel {
    public String message;
    public String time;
    public boolean isOutgoing;

    public MessageViewModel(String message, String time, boolean isOutgoing) {
        this.message = message;
        this.time = time;
        this.isOutgoing = isOutgoing;
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
}
