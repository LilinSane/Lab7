import java.io.IOException;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Set;

public class ServerModule {

    public SocketData receive(DatagramChannel dc, Selector selector, SocketAddress clientSocketAddress) throws IOException {

        byte[] receiving_data = new byte[2048];
        ByteBuffer buffer = ByteBuffer.allocate(receiving_data.length);


        while (true) {
            try {
                dc.register(selector, SelectionKey.OP_READ);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                selector.select();
                Set<SelectionKey> keys = selector.selectedKeys();
                for (SelectionKey key : keys) {
                    if (key.isReadable()) {
                        buffer.clear();
                        clientSocketAddress = dc.receive(buffer);
                        buffer.flip();
                        int limit = buffer.limit();
                        receiving_data = new byte[limit];
                        buffer.get(receiving_data, 0, limit);
                        SocketData socketData = new SocketData(receiving_data, clientSocketAddress);
                        return socketData;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void send(byte[] sending_data, DatagramChannel dc, Selector selector, SocketAddress clientSocketAddress) {
        ByteBuffer buffer = ByteBuffer.allocate(sending_data.length);

        while (true) {
            try {
                dc.register(selector, SelectionKey.OP_WRITE);
                selector.select();
                Set<SelectionKey> keys = selector.selectedKeys();
                for (SelectionKey key : keys) {
                    if (key.isWritable()) {
                        buffer.clear();
                        buffer = ByteBuffer.wrap(sending_data);
                        dc.send(buffer, clientSocketAddress);
                        return;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}

