import java.net.SocketAddress;

public class SocketData {
    private byte[] data;
    private SocketAddress socketAddress;

    SocketData(byte[] data, SocketAddress socketAddress){
        this.data = data;
        this.socketAddress = socketAddress;
    }

    public byte[] getData() {
        return data;
    }

    public SocketAddress getSocketAddress() {
        return socketAddress;
    }
}
