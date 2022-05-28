import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.stream.Stream;

public class ClientApp {
    static void start(HashSet<LabWork> labWorks, BufferedReader reader, Date ini_date){
        byte[] sending_data = new byte[2048];
        int timeout = 3000;
        DatagramPacket sending_dp;

        SocketAddress socketAddress;
        DatagramSocket ds = null;


        byte[] receiving_data = new byte[1024];
        DatagramPacket receiving_dp = new DatagramPacket(receiving_data, receiving_data.length);
        ServerResponse response = new ServerResponse();
        UserProperties user = new UserProperties();

        //AuthorizationRequest authRequest = new AuthorizationRequest();
        //AuthorizationResponse authResponse = new AuthorizationResponse();
        byte authBuf[] = new byte[100];

        boolean isAuthorized = false;
        System.out.println("Авторизируйтесь, если вы уже регистрировались в системе или зарегестрируйте нового пользователя 'register [yourLogin] [yourPassword]'");
        while (!isAuthorized){
            ClientRequest authRequest = new ClientRequest();
            authRequest.setAuthorized(false);
            ServerResponse authResponse = new ServerResponse();
            String authComand = null;
            while (true){
                try {
                    String s = reader.readLine();
                    authComand = s;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                String[] composite_command = authComand.split(" +");
                if (authComand == null || authComand == "" ){
                    System.out.println("Ошибка, поле не может быть пустым");
                } else if(!authComand.matches("^\\w+ +\\w+ +\\w+ *")){
                    System.out.println("Ошибка ввода команды");
                } else  if (!(composite_command[0].equals("authorize") || composite_command[0].equals("register"))){
                    System.out.println("Ошибка ввода команды");
                }
                else break;
            }
            String[] composite_command = authComand.split(" +");
            authRequest.setCommand(composite_command[0]);
            authRequest.setUserProperty(new UserProperties(composite_command[1], composite_command[2]));
            authBuf = Serializator.serialize_sending_data(authRequest);
            try {
                ds = new DatagramSocket();
                //ds.setSoTimeout(timeout);
                sending_dp = new DatagramPacket(authBuf, authBuf.length, InetAddress.getLocalHost(), 9254);
                ds.send(sending_dp);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                ds.receive(receiving_dp);
            } catch (SocketTimeoutException e){
                System.out.println("Сервер не отвечает, повторите попытку позже");
                return;
            } catch (IOException e) {
                e.printStackTrace();
            }
            authResponse = Serializator.serialize_receiving_data(receiving_dp.getData());
            isAuthorized = authResponse.isAuthorized();
            authRequest.setAuthorized(isAuthorized);
            user = authRequest.getUserProperty();
            System.out.println(authResponse.getReport());
        }

        while (true){
            boolean isCorrectCm = true;
            ClientRequest client_cm = new ClientRequest();

            client_cm.setUserProperty(user);
            System.out.println("Введите команду: ");

            String command = null;
            while (true){
                try {
                    String s = reader.readLine();
                    command = s;


                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (command == null || command == "" ){
                    System.out.println("Ошибка, поле не может быть пустым");
                }
                else break;
            }

            if (command.equals("add")){
                //client_cm = new ClientRequest();
                client_cm.setCommand("add");
                AppCommands.add(labWorks);
                Stream<LabWork> stream = labWorks.stream();
                client_cm.setLabWork(stream.findFirst().orElse(null));
                labWorks.clear();
            }
            else if(command.matches("update +[0-9]{6}")){
                //client_cm = new ClientRequest();
                client_cm.setCommand(command);
                String[] composite_command = command.split(" +");
                long id = Long.valueOf(composite_command[1]);
                AppCommands.add(labWorks);
                Stream<LabWork> stream = labWorks.stream();
                LabWork labWork = stream.findFirst().orElse(null);
                labWork.setId(id);
                client_cm.setLabWork(labWork);
                labWorks.clear();

            }
            else if (command.equals("exit")){
                //client_cm = new ClientRequest();
                client_cm.setCommand("exit");
                byte exitMessage[] = new byte[100];
                exitMessage = Serializator.serialize_sending_data(client_cm);
                try {
                    ds = new DatagramSocket();
                    sending_dp = new DatagramPacket(exitMessage, exitMessage.length, InetAddress.getLocalHost(), 9254);
                    ds.send(sending_dp);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.exit(0);
            }
            else if (command.equals("add_if_max")){
                //client_cm = new ClientRequest();
                client_cm.setCommand("add_if_max");
                AppCommands.add(labWorks);
                Stream<LabWork> stream = labWorks.stream();
                client_cm.setLabWork(stream.findFirst().orElse(null));
                labWorks.clear();
            }
            else if (command.equals("add_if_min")){
                //client_cm = new ClientRequest();
                client_cm.setCommand("add_if_min");
                AppCommands.add(labWorks);
                Stream<LabWork> stream = labWorks.stream();
                client_cm.setLabWork(stream.findFirst().orElse(null));
                labWorks.clear();

            }
            else if (command.equals("remove_lower")){
                //client_cm = new ClientRequest();
                client_cm.setCommand("remove_lower");
                AppCommands.add(labWorks);
                Stream<LabWork> stream = labWorks.stream();
                client_cm.setLabWork(stream.findFirst().orElse(null));
                labWorks.clear();
            }
            else if(command.matches("remove_by_id +[0-9]{6}")){
                //client_cm = new ClientRequest();
                client_cm.setCommand(command);
                labWorks.clear();
            }
            else if (command.equals("save")){
                System.out.println("У вас нет прав доступа к данной команде");
            }
            else if (command.equals("clear")){
                //client_cm = new ClientRequest();
                client_cm.setCommand(command);
            }
            else if (command.equals("min_by_creation_date")){
                //client_cm = new ClientRequest();
                client_cm.setCommand(command);
            }
            else if (command.equals("print_field_descending_average_point")){
                //client_cm = new ClientRequest();
                client_cm.setCommand(command);
            }
            else if(command.matches("filter_starts_with_name +[a-z,A-Z]+")){
                //client_cm = new ClientRequest();
                client_cm.setCommand(command);
            }
            else if(command.equals("show")){
                //client_cm = new ClientRequest();
                client_cm.setCommand(command);
            }
            else if (command.equals("info")){
                //client_cm = new ClientRequest();
                client_cm.setCommand(command);
            }
            else if(command.matches("execute_script +\\w+.txt")){
                String[] composite_command = command.split(" +");
                String scriptname = composite_command[1];
                try {
                    FileReader fileReader = new FileReader(scriptname);
                    fileReader.read();
                    fileReader.close();
                    AppCommands.execute_script(labWorks, scriptname, ini_date, ds, sending_data, receiving_dp, receiving_data, timeout);
                } catch (IOException e) {
                    System.out.println("Файл " + scriptname + " не удаётся открыть");
                }
            }
            else if(command.equals("help")){
                //client_cm = new ClientRequest();
                client_cm.setCommand(command);
            }
            else {
                isCorrectCm = false;
                System.out.println("Вы ввели неправильную команду");
            }
            if (isCorrectCm){
                sending_data = Serializator.serialize_sending_data(client_cm);

                try {
                    ds = new DatagramSocket();
                    ds.setSoTimeout(timeout);
                    sending_dp = new DatagramPacket(sending_data, sending_data.length, InetAddress.getLocalHost(), 9254);
                    ds.send(sending_dp);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                ArrayList<byte[]> arr = new ArrayList<>();
                while (true){
                    try {
                        ds.receive(receiving_dp);
                        String str = new String(receiving_dp.getData());
                        str = str.substring(0, 4);
                        if (str.equals("Done")) {
                            break;
                        }
                        else {
                            byte[] bytes = new byte[1024];
                            System.arraycopy(receiving_data, 0, bytes, 0, 1024);
                            arr.add(bytes);
                        }
                    } catch (SocketTimeoutException e){
                        System.out.println("Сервер не отвечает, повторите попытку позже");
                        return;
                    } catch (IOException e ) {
                        e.printStackTrace();
                    }
                }
                int i = 0;

                byte[] data = new byte[arr.size() * 1024];
                for (byte[] receiving_buf : arr) {
                    for (int j = 0; j < 1024; j++, i++) {
                        data[i] = receiving_buf[j];
                    }
                }

                response = Serializator.serialize_receiving_data(data);
                System.out.println(response.getReport());
                System.out.println(response.getContent());
            }
        }
    }
}
