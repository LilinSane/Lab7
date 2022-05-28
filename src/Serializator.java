import java.io.*;


public class Serializator {
    public static <T> T serialize_receiving_data(byte[] receiving_data){
        ByteArrayInputStream receivingStream = new ByteArrayInputStream(receiving_data);
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(receivingStream);
            T t = (T) ois.readObject();
            return t;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> byte[] serialize_sending_data(T t){
        ByteArrayOutputStream sendingStream = new ByteArrayOutputStream();
        ObjectOutputStream oos;
        try {
            oos = new ObjectOutputStream(sendingStream);
            oos.writeObject(t);
            oos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sendingStream.toByteArray();
    }
}
