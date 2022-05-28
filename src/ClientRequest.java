import java.io.Serializable;
import java.net.InetSocketAddress;
import java.net.SocketAddress;

public class ClientRequest implements Serializable {
    private String command;
    private LabWork labWork;
    private UserProperties userProperty;
    private boolean isAuthorized = true;
    private transient SocketAddress socketAddress;

    public SocketAddress getSocketAddress() {
        return socketAddress;
    }

    public void setSocketAddress(SocketAddress socketAddress) {
        this.socketAddress = socketAddress;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public LabWork getLabWork() {
        return labWork;
    }

    public void setLabWork(LabWork labWork) {
        this.labWork = labWork;
    }

    public UserProperties getUserProperty() {
        return userProperty;
    }

    public void setUserProperty(UserProperties userProperty) {
        this.userProperty = userProperty;
    }

    public boolean isAuthorized() {
        return isAuthorized;
    }

    public void setAuthorized(boolean authorized) {
        isAuthorized = authorized;
    }
}
