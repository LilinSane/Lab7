import java.io.Serializable;
import java.net.SocketAddress;

public class ServerResponse implements Serializable {
    private String report;
    private String content = " ";
    private boolean isAuthorized = false;
    private SocketAddress clientSocketAddress;
    private UserProperties userProperties;

    public UserProperties getUserProperties() {
        return userProperties;
    }

    public void setUserProperties(UserProperties userProperties) {
        this.userProperties = userProperties;
    }

    public SocketAddress getClientSocketAddress() {
        return clientSocketAddress;
    }

    public void setClientSocketAddress(SocketAddress clientSocketAddress) {
        this.clientSocketAddress = clientSocketAddress;
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isAuthorized() {
        return isAuthorized;
    }

    public void setAuthorized(boolean authorized) {
        isAuthorized = authorized;
    }
}
