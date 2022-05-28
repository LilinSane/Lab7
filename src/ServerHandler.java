import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.net.*;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

public class ServerHandler {
    private ReentrantLock reentrantLock = new ReentrantLock();

    static void file_check(HashSet<LabWork> labWorks) {
        for (LabWork lab : labWorks) {
            boolean isCorrect = false;
            if (lab.getName() == null || lab.getName().equals("")) {
                System.out.println("Вы ввели некоректное имя, повторите попытку");
                while (!isCorrect) {
                    System.out.println("Введите имя: ");
                    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                    String s = null;
                    String name = null;

                    try {
                        s = reader.readLine();
                        if (s.matches("\\w+")) {
                            name = s;
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    if (name == null) {
                        System.out.println("Вы ввели некоректные данные, повторите попытку");
                    } else {
                        lab.setName(name);
                        isCorrect = true;
                    }
                }
                isCorrect = false;
            }

            if (lab.getCreationDate() == null) {
                while (!isCorrect) {
                    System.out.println("Введите дату создания в формате dd.MM.yyyy: ");
                    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                    String s = null;
                    Date date = null;
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

                    try {
                        s = reader.readLine();
                        if (s.matches("[0-3]{1}[0-9]{1}\\.[0-1]{1}[1-2]\\.[1-9]{1}[0-9]{3}")) {
                            date = dateFormat.parse(s);
                        }
                    } catch (IOException | ParseException e) {
                        e.printStackTrace();
                    }

                    if (date == null) {
                        System.out.println("Вы ввели некоректные данные, повторите попытку");
                    } else {
                        lab.setCreationDate(date);
                        isCorrect = true;
                    }
                }
                isCorrect = false;
            }

            if (lab.getCoordinates().getX() == null || lab.getCoordinates().getX() <= -28) {
                while (!isCorrect) {
                    System.out.println("Введите координату х: ");
                    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                    String s = null;
                    Double x = null;
                    try {
                        s = reader.readLine();
                        if (s.matches("-?[0-9]+\\.?[0-9]*")) {
                            x = Double.valueOf(s);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    if (x == null || x <= -28) {
                        System.out.println("Вы ввели некоректные данные, повторите попытку");
                    } else {
                        lab.getCoordinates().setX(x);
                        isCorrect = true;
                    }
                }
                isCorrect = false;
            }

            if (lab.getCoordinates().getY() <= -886) {
                while (!isCorrect) {
                    System.out.println("Введите координату y: ");
                    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                    String s = null;
                    float y = -886;
                    try {
                        s = reader.readLine();
                        if (s.matches("[0-9]+\\.?[0-9]*")) {
                            y = Float.valueOf(s);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    if (y <= -886) {
                        System.out.println("Вы ввели некоректные данные, повторите попытку");
                    } else {
                        lab.getCoordinates().setY(y);
                        isCorrect = true;
                    }
                }
                isCorrect = false;
            }

            if (lab.getMinimalPoint() <= 0) {
                while (!isCorrect) {
                    System.out.println("Введите минимальную оценку: ");
                    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                    String s = null;
                    int min = 0;
                    try {
                        s = reader.readLine();
                        if (s.matches("[0-9]+\\.?[0-9]*")) {
                            min = Integer.valueOf(s);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    if (min <= 0) {
                        System.out.println("Вы ввели некоректные данные, повторите попытку");
                    } else {
                        lab.setMinimalPoint(min);
                        isCorrect = true;
                    }
                }
                isCorrect = false;
            }

            if (lab.getMaximumPoint() <= 0) {
                while (!isCorrect) {
                    System.out.println("Введите максимальную оценку: ");
                    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                    String s = null;
                    double max = 0;
                    try {
                        s = reader.readLine();
                        if (s.matches("[0-9]+\\.?[0-9]*")) {
                            max = Double.valueOf(s);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    if (max <= 0) {
                        System.out.println("Вы ввели некоректные данные, повторите попытку");
                    } else {
                        lab.setMaximumPoint(max);
                        isCorrect = true;
                    }
                }
                isCorrect = false;
            }

            if (lab.getAveragePoint() == null || lab.getAveragePoint() <= 0) {
                while (!isCorrect) {
                    System.out.println("Введите среднюю оценку: ");
                    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                    String s = null;
                    Double average = null;
                    try {
                        s = reader.readLine();
                        if (s.matches("[0-9]+\\.?[0-9]*")) {
                            average = Double.valueOf(s);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    if (average == null || average <= 0) {
                        System.out.println("Вы ввели некоректные данные, повторите попытку");
                    } else {
                        lab.setAveragePoint(average);
                        isCorrect = true;
                    }
                }
                isCorrect = false;
            }

            if (lab.getDifficulty() == null) {
                while (!isCorrect) {
                    System.out.println("Введите сложность:\n EASY,\n VERY_HARD,\n HOPELESS");
                    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                    String s = null;
                    String diff = null;
                    try {
                        s = reader.readLine();
                        if (s.matches("[A-Z,a-z]+_?[A-Z,a-z]*")) {
                            diff = s;
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    if (diff == null) {
                        System.out.println("Вы ввели некоректные данные, повторите попытку");
                    } else if (diff.toUpperCase().equals("HOPELESS")) {
                        lab.setDifficulty(Difficulty.HOPELESS);
                        isCorrect = true;
                    } else if (diff.toUpperCase().equals("EASY")) {
                        lab.setDifficulty(Difficulty.EASY);
                        isCorrect = true;
                    } else if (diff.toUpperCase().equals("VERY_HARD")) {
                        lab.setDifficulty(Difficulty.VERY_HARD);
                        isCorrect = true;
                    }
                }
                isCorrect = false;
            }

            if (lab.getAuthor().getName() == null) {
                while (!isCorrect) {
                    System.out.println("Введите имя автора: ");
                    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                    String s = null;
                    String name = null;

                    try {
                        s = reader.readLine();
                        if (s.matches("[A-Z,a-z]+")) {
                            name = s;
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    if (name == null) {
                        System.out.println("Вы ввели некоректные данные, повторите попытку");
                    } else {
                        lab.getAuthor().setName(name);
                        isCorrect = true;
                    }
                }
                isCorrect = false;
            }

            if (lab.getAuthor().getBirthday() == null) {
                while (!isCorrect) {
                    System.out.println("Введите дату в формате dd.MM.yyyy: ");
                    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                    String s = null;
                    Date date = null;
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

                    try {
                        s = reader.readLine();
                        if (s.matches("[0-3]{1}[0-9]{1}\\.[0-1]{1}[0-9]\\.[1-9]{1}[0-9]{3}")) {
                            date = dateFormat.parse(s);
                        }
                    } catch (IOException | ParseException e) {
                        e.printStackTrace();
                    }

                    if (date == null) {
                        System.out.println("Вы ввели некоректные данные, повторите попытку");
                    } else {
                        lab.getAuthor().setBirthday(date);
                        isCorrect = true;
                    }
                }
                isCorrect = false;
            }

            if (lab.getAuthor().getHeight() <= 0) {
                while (!isCorrect) {
                    System.out.println("Введите высоту: ");
                    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                    String s = null;
                    float height = 0;
                    try {
                        s = reader.readLine();
                        if (s.matches("[0-9]+\\.?[0-9]*")) {
                            height = Float.valueOf(s);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    if (height <= 0) {
                        System.out.println("Вы ввели некоректные данные, повторите попытку");
                    } else {
                        lab.getAuthor().setHeight(height);
                        isCorrect = true;
                    }
                }
                isCorrect = false;
            }

            if (lab.getAuthor().getWeight() == null || lab.getAuthor().getWeight() <= 0) {
                while (!isCorrect) {
                    System.out.println("Введите вес: ");
                    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                    String s = null;
                    Long weight = null;
                    try {
                        s = reader.readLine();
                        if (s.matches("[0-9]+\\.?[0-9]*")) {
                            weight = Long.valueOf(s);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    if (weight == null || weight <= 0) {
                        System.out.println("Вы ввели некоректные данные, повторите попытку");
                    } else {
                        lab.getAuthor().setWeight(weight);
                        isCorrect = true;
                    }
                }
                isCorrect = false;
            }

            if (lab.getAuthor().getLocation().getX() == null) {
                while (!isCorrect) {
                    System.out.println("Введите координату локации х: ");
                    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                    String s = null;
                    Double x = null;
                    try {
                        s = reader.readLine();
                        if (s.matches("[0-9]+\\.?[0-9]*")) {
                            x = Double.valueOf(s);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    if (x == null) {
                        System.out.println("Вы ввели некоректные данные, повторите попытку");
                    } else {
                        lab.getAuthor().getLocation().setX(x);
                        isCorrect = true;
                    }
                }
                isCorrect = false;
            }

            if (lab.getAuthor().getLocation().getY() == 0) {
                while (!isCorrect) {
                    System.out.println("Введите координату локации у: ");
                    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                    String s = null;
                    long y = 0;
                    try {
                        s = reader.readLine();
                        if (s.matches("[0-9]+\\.?[0-9]*")) {
                            y = Long.valueOf(s);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    if (y == 0) {
                        System.out.println("Вы ввели некоректные данные, повторите попытку");
                    } else {
                        lab.getAuthor().getLocation().setY(y);
                        isCorrect = true;
                    }
                }
                isCorrect = false;
            }

            if (lab.getAuthor().getLocation().getZ() == null) {
                while (!isCorrect) {
                    System.out.println("Введите координату локации z: ");
                    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                    String s = null;
                    Integer z = null;
                    try {
                        s = reader.readLine();
                        if (s.matches("[0-9]+\\.?[0-9]*")) {
                            z = Integer.valueOf(s);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    if (z == null) {
                        System.out.println("Вы ввели некоректные данные, повторите попытку");
                    } else {
                        lab.getAuthor().getLocation().setZ(z);
                        isCorrect = true;
                    }
                }
            }
        }
    }

    private ClientRequest processListen(ServerModule serverModule, DatagramChannel dc, Selector selector, SocketAddress clientSocketAddress, Set<UserProperties> users, DBManager dbManager) {
        reentrantLock.lock();
        boolean isAutorized = false;
        SocketData socketData = null;
        ClientRequest clientRequest = new ClientRequest();
        //ServerResponse serverResponse = new ServerResponse();
        //byte authBuf[];
        //String success;
        try {
            socketData = serverModule.receive(dc, selector, clientSocketAddress);
        } catch (IOException e) {
            e.printStackTrace();
        }

        clientRequest = Serializator.serialize_receiving_data(socketData.getData());
        clientRequest.setSocketAddress(socketData.getSocketAddress());
       // System.out.println(clientRequest.getUserProperty().getLogin() + " " + clientRequest.getUserProperty().getPassword() + " 111");

        /*if (clientRequest.getCommand().equals("authorize")) {
            for (UserProperties userData : users) {
                if (user.getLogin().equals(userData.getLogin()) && user.getPassword().equals(userData.getPassword())) {
                    isAutorized = true;
                    success = "Добро пожаловать " + user.getLogin();
                    serverResponse.setReport(success);
                    serverResponse.setAuthorized(isAutorized);
                    clientRequest.setAuthorized(isAutorized);
                    authBuf = Serializator.serialize_sending_data(serverResponse);
                    serverModule.send(authBuf, dc, selector, socketData.getSocketAddress());
                    break;
                }
            }
            if (!isAutorized) {
                success = "Вы ввели неправильный логин или пароль, повторите попытку";
                serverResponse.setReport(success);
                serverResponse.setAuthorized(isAutorized);
                authBuf = Serializator.serialize_sending_data(serverResponse);
                serverModule.send(authBuf, dc, selector, socketData.getSocketAddress());
            }
        } else if (clientRequest.getCommand().equals("register")) {
            user = clientRequest.getUserProperty();
            users.add(user);
            try {
                dbManager.usrTableFiller(user);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            isAutorized = true;
            success = "Вы успешно зарегестрированы, добро пожаловать " + user.getLogin();
            serverResponse.setReport(success);
            serverResponse.setAuthorized(isAutorized);
            clientRequest.setAuthorized(isAutorized);
            authBuf = Serializator.serialize_sending_data(serverResponse);
            serverModule.send(authBuf, dc, selector, socketData.getSocketAddress());
            return clientRequest;
        }*/
        reentrantLock.unlock();
        return clientRequest;
        /*try {
            socketData = serverModule.receive(dc, selector, clientSocketAddress);
        } catch (IOException e) {
            e.printStackTrace();
        }

        clientRequest = Serializator.serialize_receiving_data(socketData.getData());
        */

    }

    private ServerResponse processData(ClientRequest client_cm, DBManager dbManager, Gson gson, Set<LabWork> labWorks, Date ini_date, Set<UserProperties> users, ServerModule serverModule, DatagramChannel dc, Selector selector){
        ServerResponse response = new ServerResponse();

        UserProperties user = client_cm.getUserProperty();
        boolean isAutorized = false;
        String success;
        byte[] sending_data;
       // System.out.println(client_cm.getCommand());
        if (client_cm.getCommand().equals("add")) {
            try {
                dbManager.createLabInsertQuery(client_cm.getLabWork(), user);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            labWorks.add(client_cm.getLabWork());
            response.setReport("Элемент успешно добавлен: ");
            response.setContent(gson.toJson(client_cm.getLabWork()));

        } else if (client_cm.getCommand().matches("update +[0-9]{6}")) {
            String[] composite_command = client_cm.getCommand().split(" +");
            long id = Long.valueOf(composite_command[1]);
            int isExecuted = 0;
            try {
                isExecuted = dbManager.createUpdateQuery(client_cm.getLabWork(), user);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if (isExecuted != 0){
                LabWork client_labwork = client_cm.getLabWork();
                response = AppCommands.update_id(labWorks, id, client_labwork);
            }else {
                response.setReport("Данный объект вам не принадлежит, запрос отклонён");
                response.setContent(" ");
            }
        } else if (client_cm.getCommand().equals("add_if_max")) {
            LabWork client_labwork = client_cm.getLabWork();
            response = AppCommands.add_if_max(labWorks, client_labwork);
            if (!response.getContent().equals(" ")){
                try {
                    dbManager.createLabInsertQuery(client_labwork, user);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } else if (client_cm.getCommand().equals("add_if_min")) {
            LabWork client_labwork = client_cm.getLabWork();
            response = AppCommands.add_if_min(labWorks, client_labwork);
            if (!response.getContent().equals(" ")){
                try {
                    dbManager.createLabInsertQuery(client_labwork, user);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } else if (client_cm.getCommand().equals("remove_lower")) {
            LabWork client_labwork = client_cm.getLabWork();
            response = AppCommands.remove_lower(labWorks, client_labwork);
        } else if (client_cm.getCommand().matches("remove_by_id +[0-9]{6}")) {
            String[] composite_command = client_cm.getCommand().split(" +");
            long id = Long.valueOf(composite_command[1]);
            int isExecuted = 0;
            try {
                isExecuted = dbManager.createDeleteQuery(client_cm.getLabWork().getId(), user);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if (isExecuted != 0){
                response = AppCommands.remove_by_id(labWorks, id);
            }else {
                response.setReport("Данный объект вам не принадлежит, запрос отклонён");
                response.setContent(" ");
            }
        } else if (client_cm.getCommand().equals("clear")) {
            response = AppCommands.clear(labWorks);
        } else if (client_cm.getCommand().equals("min_by_creation_date")) {
            response = AppCommands.min_by_creation_date(labWorks);
        } else if (client_cm.getCommand().equals("print_field_descending_average_point")) {
            response = AppCommands.print_field_descending_average_point(labWorks);
        } else if (client_cm.getCommand().matches("filter_starts_with_name +[a-z,A-Z]+")) {
            String[] composite_command = client_cm.getCommand().split(" +");
            String name = composite_command[1];
            response = AppCommands.filter_starts_with_name(labWorks, name);
        } else if (client_cm.getCommand().equals("show")) {
            response = AppCommands.show(labWorks);
        } else if (client_cm.getCommand().equals("info")) {
            response = AppCommands.info(labWorks, ini_date);
        } else if (client_cm.getCommand().equals("help")) {
            response = AppCommands.help();
        }else if (client_cm.getCommand().equals("authorize")) {
            for (UserProperties userData : users) {
                if (user.getLogin().equals(userData.getLogin()) && user.getPassword().equals(userData.getPassword())) {
                    isAutorized = true;
                    success = "Добро пожаловать " + user.getLogin();
                    response.setReport(success);
                    response.setAuthorized(isAutorized);
                    client_cm.setAuthorized(isAutorized);
                    //authBuf = Serializator.serialize_sending_data(serverResponse);
                    //serverModule.send(authBuf, dc, selector, socketData.getSocketAddress());
                    break;
                }
            }
            if (!isAutorized) {
                success = "Вы ввели неправильный логин или пароль, повторите попытку";
                response.setReport(success);
                response.setAuthorized(isAutorized);
                //authBuf = Serializator.serialize_sending_data(serverResponse);
                //serverModule.send(authBuf, dc, selector, socketData.getSocketAddress());
            }
        } else if (client_cm.getCommand().equals("register")) {
            user = client_cm.getUserProperty();
            users.add(user);
            try {
                dbManager.usrTableFiller(user);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            isAutorized = true;
            success = "Вы успешно зарегестрированы, добро пожаловать " + user.getLogin();
            response.setReport(success);
            response.setAuthorized(isAutorized);
            client_cm.setAuthorized(isAutorized);
            //authBuf = Serializator.serialize_sending_data(serverResponse);
            //serverModule.send(authBuf, dc, selector, socketData.getSocketAddress());
        }
       // System.out.println(user.getLogin() + " " + user.getPassword() + " 222");
        response.setClientSocketAddress(client_cm.getSocketAddress());
        //sending_data = Serializator.serialize_sending_data(response);
        return response;
    }

    private boolean processShipment(ServerResponse serverResponse, ServerModule serverModule, DatagramChannel dc, Selector selector){
        boolean res = false;
        byte[] sending_data = Serializator.serialize_sending_data(serverResponse);
        int offset = 1024;
        int i = 0;
        byte[] stopw = "Done".getBytes(StandardCharsets.UTF_8);
        while (offset * i < sending_data.length) {
            byte[] sending_buf = new byte[1024];
            if (sending_data.length - i * offset <= offset) {
                for (int j = 0; j < sending_data.length - i * offset; j++) {
                    sending_buf[j] = sending_data[i * offset + j];
                }
                serverModule.send(sending_buf, dc, selector, serverResponse.getClientSocketAddress());

                serverModule.send(stopw, dc, selector, serverResponse.getClientSocketAddress());
                res = true;
                break;
            } else {
                for (int j = 0; j < offset; j++) {
                    sending_buf[j] = sending_data[i * offset + j];
                }
                //System.out.println(serverResponse.getClientSocketAddress().toString());
                serverModule.send(sending_buf, dc, selector, serverResponse.getClientSocketAddress());
                i++;
            }
        }
        return res;
    }



    public void start(Set<LabWork> labWorks, Set<UserProperties> users, BufferedReader reader, Date ini_date, DBManager dbManager) {

        ServerModule serverModule = new ServerModule();
        ClientRequest client_cm = new ClientRequest();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        SocketData socketData = null;
        SocketAddress clientSocketAddress = null;
        DatagramChannel dc = null;
        Selector selector = null;
        SocketAddress socketAddress = null;
        byte[] sending_data = new byte[1024];
        UserProperties user = new UserProperties();


        try {
            socketAddress = new InetSocketAddress(InetAddress.getLocalHost(), 9254);
            dc = DatagramChannel.open();
            dc.configureBlocking(false);
            selector = Selector.open();
            dc.register(selector, SelectionKey.OP_WRITE);
            dc.bind(socketAddress);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scanner scanner = new Scanner(System.in);
        new Thread(() -> {
            String input;
            while (true) {
                input = scanner.nextLine().toLowerCase();
                if (input.equals("exit")) {
                    System.exit(0);
                }
            }
        }).start();

        ExecutorService listener = Executors.newCachedThreadPool();
        ExecutorService dataHandler = Executors.newFixedThreadPool(5);
        ExecutorService sender = Executors.newFixedThreadPool(1);
        DatagramChannel finalDc1 = dc;
        Selector finalSelector1 = selector;


        while (true) {
            Callable<ClientRequest> cTask = () -> processListen(serverModule, finalDc1, finalSelector1, clientSocketAddress, users, dbManager);
            //ClientRequest fRequest = processListen(serverModule, finalDc1, finalSelector1, clientSocketAddress, users, dbManager);
            Future<ClientRequest> fRequest = listener.submit(cTask);
            Callable<ServerResponse> cRespTask = () -> processData(fRequest.get(), dbManager, gson, labWorks, ini_date, users, serverModule, finalDc1, finalSelector1);

            try {
                System.out.println(fRequest.get());
            } catch (Exception e) {
                e.printStackTrace();
            }

            Future<ServerResponse> fResponse = dataHandler.submit(cRespTask);
            try {
                System.out.println(fResponse.get().getReport());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }

            Callable<Boolean> isShipedTask = () -> processShipment(fResponse.get(), serverModule, finalDc1, finalSelector1);
            Future<Boolean> isShiped = sender.submit(isShipedTask);
            try {
                System.out.println(isShiped.get().booleanValue());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }


            //Thread sender = new Thread(() -> processShipment(fResponse.get(), serverModule, finalDc1, finalSelector1));
            /*new Thread(() -> {
                try {
                    boolean isShiped = processShipment(fResponse.get(), serverModule, finalDc1, finalSelector1);
                    System.out.println(isShiped);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }).start();*/
        }




        //Future<ClientRequest> fRequest = listener.submit(() -> processListen(socketData, serverModule, finalDc1, finalSelector1, clientSocketAddress, clientRequest, user, users, authRespond, dbManager));


        /*Thread shipper = new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    processShipment(fResponse.get(), serverModule, finalDc1, finalSelector1, socketData);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        };
        shipper.start();*/



        /*
        DatagramChannel finalDc = dc;
        Selector finalSelector = selector;
        Callable listenCall = () -> processListen( socketData,  serverModule, finalDc, finalSelector,  clientSocketAddress,  clientRequest,  user,  users,  authRespond,  dbManager );
        FutureTask task = new FutureTask(listenCall);
        listener.execute(task);
       */


        //while (true) {


            /*
            boolean isAutorized = false;
            while (!isAutorized) {
                byte authBuf[] = new byte[100];
                String success;
                try {
                    socketData = serverModule.receive(dc, selector, clientSocketAddress);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                clientRequest = Serializator.serialize_receiving_data(socketData.getData());
                user = clientRequest.getUserProperty();
                if (clientRequest.getCommand().equals("authorize")) {
                    for (UserProperties userData : users) {
                        if (user.getLogin().equals(userData.getLogin()) && user.getPassword().equals(userData.getPassword())) {
                            isAutorized = true;
                            success = "Добро пожаловать " + user.getLogin();
                            authRespond.setResponse(success);
                            authRespond.setAuthorized(isAutorized);
                            authBuf = Serializator.serialize_sending_data(authRespond);
                            serverModule.send(authBuf, dc, selector, socketData.getSocketAddress());
                            break;
                        }
                    }
                    if (!isAutorized) {
                        success = "Вы ввели неправильный логин или пароль, повторите попытку";
                        ;
                        authRespond.setResponse(success);
                        authRespond.setAuthorized(isAutorized);
                        authBuf = Serializator.serialize_sending_data(authRespond);
                        serverModule.send(authBuf, dc, selector, socketData.getSocketAddress());
                    }
                } else if (clientRequest.getCommand().equals("register")) {
                    user = clientRequest.getUserProperty();
                    users.add(user);
                    try {
                        dbManager.usrTableFiller(user);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    isAutorized = true;
                    success = "Вы успешно зарегестрированы, добро пожаловать " + user.getLogin();
                    authRespond.setResponse(success);
                    authRespond.setAuthorized(isAutorized);
                    authBuf = Serializator.serialize_sending_data(authRespond);
                    serverModule.send(authBuf, dc, selector, socketData.getSocketAddress());
                }
            }
            */

            //while (true) {
                /*try {
                    socketData = serverModule.receive(dc, selector, clientSocketAddress);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                client_cm = Serializator.serialize_receiving_data(socketData.getData());
                System.out.println(client_cm.getCommand());*/

                /*if (client_cm.getCommand().equals("add")) {
                    try {
                        dbManager.createLabInsertQuery(client_cm.getLabWork(), user);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    labWorks.add(client_cm.getLabWork());
                    response.setReport("Элемент успешно добавлен: ");
                    response.setContent(gson.toJson(client_cm.getLabWork()));

                } else if (client_cm.getCommand().matches("update +[0-9]{6}")) {
                    String[] composite_command = client_cm.getCommand().split(" +");
                    long id = Long.valueOf(composite_command[1]);
                    int isExecuted = 0;
                    try {
                        isExecuted = dbManager.createUpdateQuery(client_cm.getLabWork(), user);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    if (isExecuted != 0){
                        LabWork client_labwork = client_cm.getLabWork();
                        response = AppCommands.update_id(labWorks, id, client_labwork);
                    }else {
                        response.setReport("Данный объект вам не принадлежит, запрос отклонён");
                        response.setContent(" ");
                    }
                } else if (client_cm.getCommand().equals("add_if_max")) {
                    LabWork client_labwork = client_cm.getLabWork();
                    response = AppCommands.add_if_max(labWorks, client_labwork);
                    if (!response.getContent().equals(" ")){
                        try {
                            dbManager.createLabInsertQuery(client_labwork, user);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                } else if (client_cm.getCommand().equals("add_if_min")) {
                    LabWork client_labwork = client_cm.getLabWork();
                    response = AppCommands.add_if_min(labWorks, client_labwork);
                    if (!response.getContent().equals(" ")){
                        try {
                            dbManager.createLabInsertQuery(client_labwork, user);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                } else if (client_cm.getCommand().equals("remove_lower")) {
                    LabWork client_labwork = client_cm.getLabWork();
                    response = AppCommands.remove_lower(labWorks, client_labwork);
                } else if (client_cm.getCommand().matches("remove_by_id +[0-9]{6}")) {
                    String[] composite_command = client_cm.getCommand().split(" +");
                    long id = Long.valueOf(composite_command[1]);
                    int isExecuted = 0;
                    try {
                        isExecuted = dbManager.createDeleteQuery(client_cm.getLabWork().getId(), user);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    if (isExecuted != 0){
                        response = AppCommands.remove_by_id(labWorks, id);
                    }else {
                        response.setReport("Данный объект вам не принадлежит, запрос отклонён");
                        response.setContent(" ");
                    }
                } else if (client_cm.getCommand().equals("clear")) {
                    response = AppCommands.clear(labWorks);
                } else if (client_cm.getCommand().equals("min_by_creation_date")) {
                    response = AppCommands.min_by_creation_date(labWorks);
                } else if (client_cm.getCommand().equals("print_field_descending_average_point")) {
                    response = AppCommands.print_field_descending_average_point(labWorks);
                } else if (client_cm.getCommand().matches("filter_starts_with_name +[a-z,A-Z]+")) {
                    String[] composite_command = client_cm.getCommand().split(" +");
                    String name = composite_command[1];
                    response = AppCommands.filter_starts_with_name(labWorks, name);
                } else if (client_cm.getCommand().equals("show")) {
                    response = AppCommands.show(labWorks);
                } else if (client_cm.getCommand().equals("info")) {
                    response = AppCommands.info(labWorks, ini_date);
                } else if (client_cm.getCommand().equals("help")) {
                    response = AppCommands.help();
                } else if (client_cm.getCommand().equals("exit")) {
                    break;
                }

                sending_data = Serializator.serialize_sending_data(response);
                */

                /*int offset = 1024;
                int i = 0;
                byte[] stopw = "Done".getBytes(StandardCharsets.UTF_8);
                while (offset * i < sending_data.length) {
                    byte[] sending_buf = new byte[1024];
                    if (sending_data.length - i * offset <= offset) {
                        for (int j = 0; j < sending_data.length - i * offset; j++) {
                            sending_buf[j] = sending_data[i * offset + j];
                        }
                        serverModule.send(sending_buf, dc, selector, socketData.getSocketAddress());

                        serverModule.send(stopw, dc, selector, socketData.getSocketAddress());
                        break;
                    } else {
                        for (int j = 0; j < offset; j++) {
                            sending_buf[j] = sending_data[i * offset + j];
                        }
                        serverModule.send(sending_buf, dc, selector, socketData.getSocketAddress());
                        i++;
                    }
                }*/
            //}
        //}
    }

}

