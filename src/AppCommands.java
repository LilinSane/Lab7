import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;
import java.text.ParseException;
import java.text.SimpleDateFormat;;
import java.util.*;
import java.util.stream.Stream;


public class AppCommands {


    public static void Add_Foundation(LabWork lab){
        boolean isCorrect = false;

            while (!isCorrect){
                System.out.println("Введите имя проекта: ");
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                String s = null;
                String name = null;

                try {
                    s = reader.readLine();
                    if (s.matches("\\w+")){
                        name = s;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if (name == null) {
                    System.out.println("Вы ввели некоректные данные, повторите попытку");
                }
                else{
                    lab.setName(name);
                    isCorrect = true;
                }
            }
            isCorrect = false;



        //Coordinates
        Coordinates coordinates = new Coordinates();
            while (!isCorrect){
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
                }
                else{
                    coordinates.setX(x);
                    isCorrect = true;
                }
            }
            isCorrect = false;




        while (!isCorrect){
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
            }
            else{
                coordinates.setY(y);
                lab.setCoordinates(coordinates);
                isCorrect = true;
            }
        }
        isCorrect = false;

        //Minimal point

        while (!isCorrect){
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
            }
            else{
                lab.setMinimalPoint(min);
                isCorrect = true;
            }
        }
        isCorrect = false;

        // Maximum point

        while (!isCorrect){
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
            }
            else{
                lab.setMaximumPoint(max);
                isCorrect = true;
            }
        }
        isCorrect = false;


        // Average point

        while (!isCorrect){
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

            if (average == null || average <= 0 ) {
                System.out.println("Вы ввели некоректные данные, повторите попытку");
            }
            else{
                lab.setAveragePoint(average);
                isCorrect = true;
            }
        }
        isCorrect = false;



        // Difficulty

        while (!isCorrect){
            System.out.println("Введите сложность:\n EASY,\n VERY_HARD,\n HOPELESS");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String s = null;
            String diff = null;
            try {
                s = reader.readLine();
                if (s.matches("[A-Z,a-z]+_?[A-Z,a-z]*")){
                    diff = s;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (diff == null){
                System.out.println("Вы ввели некоректные данные, повторите попытку");
            }
           else if (diff.toUpperCase().equals("HOPELESS")) {
                lab.setDifficulty(Difficulty.HOPELESS);
                isCorrect = true;
            }
            else if(diff.toUpperCase().equals("EASY")){
                lab.setDifficulty(Difficulty.EASY);
                isCorrect = true;
            }
            else if(diff.toUpperCase().equals("VERY_HARD")){
                lab.setDifficulty(Difficulty.VERY_HARD);
                isCorrect = true;
            }

        }
        isCorrect = false;

        // Author

        Person person = new Person();

        while (!isCorrect){
            System.out.println("Введите имя автора: ");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String s = null;
            String name = null;

            try {
                s = reader.readLine();
                if (s.matches("[A-Z,a-z]+")){
                    name = s;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (name == null) {
                System.out.println("Вы ввели некоректные данные, повторите попытку");
            }
            else{
                person.setName(name);
                isCorrect = true;
            }
        }
        isCorrect = false;


        while (!isCorrect){
            System.out.println("Введите дату в формате dd.MM.yyyy: ");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String s = null;
            Date date = null;
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

            try {
                s = reader.readLine();
                if (s.matches("[0-3]?[0-9]{1}\\.[0-1]{1}[0-9]\\.[1-9]{1}[0-9]{3}")){
                    date = dateFormat.parse(s);
                }
            } catch (IOException | ParseException e) {
                e.printStackTrace();
            }

            if (date == null) {
                System.out.println("Вы ввели некоректные данные, повторите попытку");
            }
            else{
                person.setBirthday(date);
                isCorrect = true;
            }
        }
        isCorrect = false;

        while (!isCorrect){
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
            }
            else{
                person.setHeight(height);
                isCorrect = true;
            }
        }
        isCorrect = false;

        while (!isCorrect){
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

            if (weight == null || weight <= 0 ) {
                System.out.println("Вы ввели некоректные данные, повторите попытку");
            }
            else{
                person.setWeight(weight);
                isCorrect = true;
            }
        }
        isCorrect = false;

        // Location

        Location location = new Location();

        while (!isCorrect){
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
            }
            else{
                location.setX(x);
                isCorrect = true;
            }
        }
        isCorrect = false;

        while (!isCorrect){
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
            }
            else{
                location.setY(y);
                isCorrect = true;
            }
        }
        isCorrect = false;

        while (!isCorrect){
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
            }
            else{
                location.setZ(z);
                person.setLocation(location);
                lab.setAuthor(person);
                isCorrect = true;
            }
        }
    }

    public static boolean Add_Foundation_non_interact(LabWork lab, String name, String x_cor, String y_cor, String min_s, String max_s, String average_s, String diff_s, String per_name_s, String date_s, String height_s, String weight_s, String x_loc, String y_loc, String z_loc){

        if (!name.matches("\\w+")){
            System.out.println("Некорректный праметр 'название проекта' ");
            return false;
        }
        else{
            lab.setName(name);
        }





        //Coordinates
        Coordinates coordinates = new Coordinates();
        Double x_coordinate = null;
        if (x_cor.matches("-?[0-9]+\\.?[0-9]*")) {
            x_coordinate = Double.valueOf(x_cor);
            if (x_coordinate == null || x_coordinate <= -28) {
                System.out.println("Некорректный праметр 'координата х' ");
                return false;
            }
            else{
                coordinates.setX(x_coordinate);
            }
        }
        else {
            System.out.println("Некорректный праметр 'координата х' ");
            return false;
        }



        float y = -886;
        if (y_cor.matches("[0-9]+\\.?[0-9]*")) {
            y = Float.valueOf(y_cor);
            if (y <= -886) {
                System.out.println("Некорректный праметр 'координата y' ");
                return false;
            }
            else{
                coordinates.setY(y);
                lab.setCoordinates(coordinates);
            }
        }
        else {
            System.out.println("Некорректный праметр 'координата y' ");
            return false;
        }







        //Minimal point

        int min = 0;
        if (min_s.matches("[0-9]+\\.?[0-9]*")) {
            min = Integer.valueOf(min_s);
            if (min <= 0) {
                System.out.println("Некорректный праметр 'минимальная оценка' ");
                return false;
            }
            else {
                lab.setMinimalPoint(min);
            }
        }
        else {
            System.out.println("Некорректный праметр 'минимальная оценка' ");
            return false;
        }




        double max = 0;
        if (max_s.matches("[0-9]+\\.?[0-9]*")) {
            max = Double.valueOf(max_s);
            if (max <= 0) {
                System.out.println("Некорректный праметр 'максимальная оценка' ");
                return false;
            } else {
                lab.setMaximumPoint(max);
            }
        }
        else{
            System.out.println("Некорректный праметр 'максимальная оценка' ");
            return false;
        }


        // Average point
        Double average = null;
        if (average_s.matches("[0-9]+\\.?[0-9]*")) {
            average = Double.valueOf(average_s);
            if (average == null || average <= 0 ) {
                System.out.println("Некорректный праметр 'средняя оценка' ");
                return false;
            }
            else{
                lab.setAveragePoint(average);
            }
        }
        else {
            System.out.println("Некорректный праметр 'средняя оценка' ");
            return false;
        }


        // Difficulty


        String diff = null;
        if (diff_s.matches("[A-Z,a-z]+_?[A-Z,a-z]*")){
            diff = diff_s;
            if (diff == null){
                System.out.println("Некорректный праметр 'сложность' ");
                return false;
            }
            else if (diff.toUpperCase().equals("HOPELESS")) {
                lab.setDifficulty(Difficulty.HOPELESS);

            }
            else if(diff.toUpperCase().equals("EASY")){
                lab.setDifficulty(Difficulty.EASY);

            }
            else if(diff.toUpperCase().equals("VERY_HARD")){
                lab.setDifficulty(Difficulty.VERY_HARD);
            }
        }
        else {
            System.out.println("Некорректный праметр 'сложность' ");
            return false;
        }

        // Author

        Person person = new Person();

        String per_name = null;

        if (per_name_s.matches("[A-Z,a-z]+")){
            per_name = per_name_s;
            if (name == null) {
                System.out.println("Некорректный праметр 'имя автора' ");
                return false;
            }
            else{
                person.setName(per_name);
            }
        }
        else {
            System.out.println("Некорректный праметр 'имя автора' ");
            return false;
        }


        Date date = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

        if (date_s.matches("[0-3]?[0-9]{1}\\.[0-1]?[0-2]\\.[1-9]{1}[0-9]{3}")){
            try {
                date = dateFormat.parse(date_s);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (date == null) {
                System.out.println("Некорректный праметр 'дата' ");
                return false;
            }
            else{
                person.setBirthday(date);
            }
        }
        else {
            System.out.println("Некорректный праметр 'дата' ");
            return false;
        }


        float height = 0;

        if (height_s.matches("[0-9]+\\.?[0-9]*")) {
            height = Float.valueOf(height_s);
            if (height <= 0) {
                System.out.println("Некорректный праметр 'высота' ");
                return false;
            } else {
                person.setHeight(height);
            }
        }
        else {
            System.out.println("Некорректный праметр 'высота' ");
            return false;
        }



        Long weight = null;

        if (weight_s.matches("[0-9]+\\.?[0-9]*")) {
            weight = Long.valueOf(weight_s);
            if (weight == null || weight <= 0 ) {
                System.out.println("Некорректный праметр 'вес'");
                return false;
            }
            else{
                person.setWeight(weight);
            }
        }
        else {
            System.out.println("Некорректный праметр 'вес'");
            return false;
        }


        // Location

        Location location = new Location();

        Double x_location = null;
        if (x_loc.matches("[0-9]+\\.?[0-9]*")) {
            x_location = Double.valueOf(x_loc);
            if (x_location == null) {
                System.out.println("Некорректный праметр 'координата локации х' ");
                return false;
            }
            else{
                location.setX(x_location);
            }
        }
        else {
            System.out.println("Некорректный праметр 'координата локации х' ");
            return false;
        }




        long y_location = 0;

        if (y_loc.matches("[0-9]+\\.?[0-9]*")) {
            y_location = Long.valueOf(y_loc);
            if (y == 0) {
                System.out.println("Некорректный праметр 'координата локации y' ");
                return false;
            }
            else{
                location.setY(y_location);
            }
        }
        else {
            System.out.println("Некорректный праметр 'координата локации y' ");
            return false;
        }


        Integer z_location = null;

        if (z_loc.matches("[0-9]+\\.?[0-9]*")) {
            z_location = Integer.valueOf(z_loc);
            if (z_location == null) {
                System.out.println("Некорректный праметр 'координата локации z' ");
                return false;
            }
            else{
                location.setZ(z_location);
                person.setLocation(location);
                lab.setAuthor(person);
            }
        }
        else {
            System.out.println("Некорректный праметр 'координата локации z' ");
            return false;
        }

        return true;
    }

    public static void remove_by_id_non_interact(HashSet<LabWork> hashSet, long id){
        for (LabWork lab : hashSet) {
            if (lab.getId() == id) {
                hashSet.remove(lab);
                break;
            }
        }
    }

    /**
     *
     * @param hashSet
     */
    public static void add( Set<LabWork> hashSet){

        boolean isCorrect = false;
        LabWork lab = new LabWork();

        Add_Foundation(lab);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String content = gson.toJson(lab);

        hashSet.add(lab);
    }

    /**
     *
     * @param hashSet
     * @param id
     */
    public static ServerResponse update_id(Set<LabWork> hashSet, long id, LabWork client_labWork){
        boolean flag = false;
        ServerResponse response = new ServerResponse();
        for(LabWork lab : hashSet){
            if (lab.getId() == id){
                hashSet.remove(lab);
                //lab.setId(id);
                lab = client_labWork;

                //Add_Foundation(lab);

                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                String content = gson.toJson(lab);

                hashSet.add(lab);
                flag = true;

                response.setReport("Элемент успешно обновлён: ");
                response.setContent(content);
                break;
            }

        }
        if (flag == false){
            response.setReport("Элемента с данным id не существует");
            response.setContent(" ");
        }
        return response;
    }

    /**
     *
     * @param hashSet
     * @param id
     */
    public static ServerResponse remove_by_id(Set<LabWork> hashSet, long id){
        ServerResponse response = new ServerResponse();
        boolean flag = false;
        for (LabWork lab : hashSet) {
            if (lab.getId() == id) {
                hashSet.remove(lab);
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                String content = gson.toJson(lab);

                flag = true;

                response.setReport("Элемент успешно удалён: ");
                response.setContent(content);
                break;
            }
        }
        if (flag == false) {
            response.setReport("Элемента с данным id не существует");
            response.setContent(" ");
        }
        return response;
    }

    /**
     *
     * @param hashSet
     */
    public static ServerResponse clear(Set<LabWork> hashSet){
        hashSet.clear();
        ServerResponse response = new ServerResponse();
        response.setReport("Элементы коллекции были удалены");
        response.setContent(" ");
        return response;
    }

    /**
     *
     * @return
     */
    public static ServerResponse help(){
        ServerResponse response = new ServerResponse();
        response.setReport("Доступные команды: \n add \n update 'id' \n remove_by_id 'id' \n clear \n add_if_max \n" +
                " add_if_min \n remove_lower \n min_by_creation_date \n filter_starts_with_name \n print_field_descending_average_point \n" +
                " show \n info \n execute_script");
        response.setContent(" ");
        return response;
    }

    /**
     *
     * @param hashSet
     * @param filename
     */
    /*public static void save(Set<LabWork> hashSet, String filename){
        JsonWriter.toDataFile(hashSet, filename);
    }*/

    /**
     *
     * @param hashSet
     */
    public static ServerResponse add_if_max(Set<LabWork> hashSet, LabWork client_labwork){
        ServerResponse response = new ServerResponse();
        double max = client_labwork.getMaximumPoint();

        for(LabWork lab : hashSet){
            if(lab.compareTo(client_labwork, true) > 0){
                max = lab.getMaximumPoint();
            }
        }
        if(client_labwork.getMaximumPoint() >= max){
            hashSet.add(client_labwork);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String content = gson.toJson(client_labwork);
            response.setReport("Элемент успешно добавлен: ");
            response.setContent(content);
        }
        else {
            response.setReport("Элемент не является максимальным");
            response.setContent(" ");
        }
        return response;
    }

    /**
     *
     * @param hashSet
     */
    public static ServerResponse add_if_min(Set<LabWork> hashSet, LabWork client_labwork){
        ServerResponse response = new ServerResponse();
        double min = client_labwork.getMinimalPoint();

        for(LabWork lab : hashSet){
            if(lab.compareTo(client_labwork, false) < 0){
                min = lab.getMinimalPoint();
            }
        }
        if(client_labwork.getMinimalPoint() <= min){
            hashSet.add(client_labwork);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String content = gson.toJson(client_labwork);
            response.setReport("Элемент успешно добавлен: ");
            response.setContent(content);
        }
        else {
            response.setReport("Элемент не является максимальным");
            response.setContent(" ");
        }
        return response;
    }

    /**
     *
     * @param hashSet
     */
    public static ServerResponse remove_lower(Set<LabWork> hashSet, LabWork client_labwork){
        ServerResponse response = new ServerResponse();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        long max = client_labwork.getAuthor().getWeight();
        String content_buf = " ";
        int counter1 = 0;
        int counter2 = 0;
        boolean isLower = false;
        boolean isDeleted = false;
        while (!isDeleted){
            for (LabWork lab : hashSet) {
                if (lab.getAuthor().getWeight() - max < 0) {
                    //remove_by_id(hashSet, lab.getId());
                    String content = gson.toJson(lab);
                    content_buf += "\n" + content;
                    response.setReport("Элементы удалены: ");
                    response.setContent(content_buf);
                    hashSet.remove(lab);
                    isLower = true;
                    counter1++;
                    break;
                }
            }
            if (counter1 != counter2) {
                counter2 = counter1;
            }
            else {
                isDeleted = true;
            }
        }

        if (!isLower){
            //System.out.println("Элементов меньше заданного не существует: ");
            response.setReport("Элементов меньше заданного не существует: ");
            response.setContent(content_buf);
        }
        return response;
    }

    /**
     *
     * @param hashSet
     */
    public static ServerResponse min_by_creation_date(Set<LabWork> hashSet){
        ServerResponse response = new ServerResponse();
        long min = 0;
        long max = 0;
        for (LabWork lab : hashSet){
            if (max < lab.getCreationDate().getTime()){
                max = lab.getCreationDate().getTime();
            }
        }
        min = max;

        for (LabWork lab : hashSet){
            if (min > lab.getCreationDate().getTime()){
                min = lab.getCreationDate().getTime();
            }
        }

        for (LabWork lab : hashSet){
            if (lab.getCreationDate().getTime() == min){
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                String content = gson.toJson(lab);
                response.setReport("Элемент с минимальной датой : ");
                response.setContent(content);
                break;
            }
        }
        return response;
    }

    /**
     *
     * @param hashSet
     * @param name
     */
    public static ServerResponse filter_starts_with_name(Set<LabWork> hashSet, String name){
        ServerResponse response = new ServerResponse();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String content = " ";
        boolean flag = true;
        for (LabWork lab : hashSet){
            if(lab.getAuthor().getName().toLowerCase().matches(name.toLowerCase() + ".*")){
                if (flag){
                    response.setReport("Элементы с совпадениями : ");
                    flag = false;
                }
                content += gson.toJson(lab) + "\n";
            }
        }
        response.setContent(content);
        if (flag){
            response.setReport("Совпадений не найдено");
        }
        return response;
    }

    /**
     *
     * @param hashSet
     */
    public static ServerResponse print_field_descending_average_point(Set<LabWork> hashSet){
        ServerResponse response = new ServerResponse();
        String content = " ";
        List<Double> array = new ArrayList();
        for (LabWork lab : hashSet){
            array.add(lab.getAveragePoint());
        }
        Collections.sort(array);
        Collections.reverse(array);
        response.setReport("Средние значения отсортированы по убыванию : ");
        for (Double average : array){
            //response.setContent("Average : " + average);
            content += "Average : " + average + "\n";
        }
        response.setContent(content);
        return response;
    }

    /**
     *
     * @param hashSet
     */
    public static ServerResponse show(Set<LabWork> hashSet){
        ServerResponse response = new ServerResponse();
        TreeSet treeSet = sort_collection(hashSet);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String content = gson.toJson(treeSet);
        response.setReport("Элементы, хранящиеся в коллекции: ");
        response.setContent(content);
        return response;
    }

    /**
     *
     * @param hashSet
     * @param ini_Date
     */
    public static ServerResponse info(Set<LabWork> hashSet, Date ini_Date){
        ServerResponse response = new ServerResponse();
        response.setReport("Информация о коллекции : ");
        response.setContent("Тип коллекции : " + hashSet.getClass() + "\n" +
                "Дата инициализации : " + ini_Date + "\n" +
                "Количество элементов : " + hashSet.size());
        return response;
    }

    /**
     *
     * @param labWorks
     * @param ini_date
     */

    static void execute_script(Set<LabWork> labWorks, String scriptname, Date ini_date, DatagramSocket ds, byte[] sending_data, DatagramPacket receiving_dp, byte[] receiving_data, int timeout){
        File file = new File(scriptname);
        FileReader fr = null;
        try {
            fr = new FileReader(file);
        } catch (FileNotFoundException e) {

        }
        BufferedReader reader = new BufferedReader(fr);
        ArrayList<String> arr = new ArrayList();
        String script_com = null;
        try {
            script_com = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (script_com != null){

            if (script_com.length() != 0 ){
                arr.add(script_com);
            }
            try {
                script_com = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        for(String command : arr){
            boolean isCorrect = true;
            ServerResponse response;
            ClientRequest client_cm = null;
            try {
                String[] cl = command.split(" ");
                if (cl[0].equals("add")){
                    if (cl.length != 15){
                        System.out.println("Некорректное кол-во аргументов функции 'add'");
                        return;
                    }
                    LabWork lab = new LabWork();
                    isCorrect = AppCommands.Add_Foundation_non_interact(lab, cl[1], cl[2], cl[3], cl[4], cl[5], cl[6], cl[7], cl[8],cl[9],cl[10],cl[11],cl[12],cl[13], cl[14]);
                    if (!isCorrect){
                        System.out.println("Аргументы введены некорректно");
                        return;
                    }
                    labWorks.add(lab);
                    client_cm = new ClientRequest();
                    client_cm.setCommand("add");
                    Stream<LabWork> stream = labWorks.stream();
                    client_cm.setLabWork(stream.findFirst().orElse(null));
                    labWorks.clear();
                }
                else if(cl[0].equals("show")){
                    client_cm = new ClientRequest();
                    client_cm.setCommand(cl[0]);
                }
                else if(cl[0].equals("update")){
                    if (cl.length != 16){
                        System.out.println("Некорректное кол-во аргументов функции 'update'");
                        return;
                    }
                    if (cl[1].matches("[1-9]{1}[0-9]{5}")) {
                        long id = Long.valueOf(cl[1]);
                        LabWork lab = new LabWork();
                        lab.setId(id);
                        isCorrect = AppCommands.Add_Foundation_non_interact(lab, cl[2], cl[3], cl[4], cl[5], cl[6], cl[7], cl[8], cl[9], cl[10], cl[11], cl[12], cl[13], cl[14], cl[15]);
                        if (!isCorrect) {
                            System.out.println("Аргументы введены некорректно");
                            return;
                        }
                        labWorks.add(lab);
                        client_cm = new ClientRequest();
                        client_cm.setCommand(cl[0] + " " + cl[1]);
                        Stream<LabWork> stream = labWorks.stream();
                        client_cm.setLabWork(stream.findFirst().orElse(null));
                        labWorks.clear();

                    }
                }
                else if (cl[0].equals("exit")){
                    if (cl.length == 1){
                        break;
                    }
                    else {
                        System.out.println("Некорректное кол-во аргументов функции 'exit'");
                        return;
                    }
                }
                else if (cl[0].equals("add_if_max")){
                    if (cl.length != 15){
                        System.out.println("Некорректное кол-во аргументов функции 'add_if_max'");
                        return;
                    }
                    LabWork labWork = new LabWork();
                    isCorrect = AppCommands.Add_Foundation_non_interact(labWork, cl[1], cl[2], cl[3], cl[4], cl[5], cl[6], cl[7], cl[8],cl[9],cl[10],cl[11],cl[12],cl[13], cl[14]);
                    if(!isCorrect){
                        System.out.println("Аргументы введены некорректно");
                        return;
                    }
                    client_cm = new ClientRequest();
                    client_cm.setCommand(cl[0]);
                    client_cm.setLabWork(labWork);
                }
                else if (cl[0].equals("add_if_min")){
                    if (cl.length != 15){
                        System.out.println("Некорректное кол-во аргументов функции 'add_if_min'");
                        return;
                    }
                    LabWork labWork = new LabWork();
                    isCorrect = AppCommands.Add_Foundation_non_interact(labWork, cl[1], cl[2], cl[3], cl[4], cl[5], cl[6], cl[7], cl[8],cl[9],cl[10],cl[11],cl[12],cl[13], cl[14]);
                    if(!isCorrect){
                        System.out.println("Аргументы введены некорректно");
                        return;
                    }
                    client_cm = new ClientRequest();
                    client_cm.setCommand(cl[0]);
                    client_cm.setLabWork(labWork);
                }

                else if (cl[0].equals("remove_lower")){
                    if (cl.length != 15){
                        System.out.println("Некорректное кол-во аргументов функции 'remove_lower'");
                        return;
                    }
                    LabWork labWork = new LabWork();
                    isCorrect = AppCommands.Add_Foundation_non_interact(labWork, cl[1], cl[2], cl[3], cl[4], cl[5], cl[6], cl[7], cl[8],cl[9],cl[10],cl[11],cl[12],cl[13], cl[14]);
                    if(!isCorrect){
                        System.out.println("Аргументы введены некорректно");
                        return;
                    }
                    client_cm = new ClientRequest();
                    client_cm.setCommand(cl[0]);
                    client_cm.setLabWork(labWork);
                }
                else if(cl[0].equals("remove_by_id")){
                    if (cl.length != 2){
                        System.out.println("Некорректное кол-во аргументов функции 'remove_by_id'");
                        return;
                    }
                    client_cm = new ClientRequest();
                    client_cm.setCommand(cl[0] + " " + cl[1]);
                }
                else if (cl[0].equals("clear")){
                    if (cl.length != 1){
                        System.out.println("Некорректное кол-во аргументов функции 'clear'");
                        return;
                    }
                    client_cm = new ClientRequest();
                    client_cm.setCommand(cl[0]);
                }
                else if (cl[0].equals("min_by_creation_date")){
                    if (cl.length != 1){
                        System.out.println("Некорректное кол-во аргументов функции 'min_by_creation_date'");
                        return;
                    }
                    client_cm = new ClientRequest();
                    client_cm.setCommand(cl[0]);
                }
                else if (cl[0].equals("print_field_descending_average_point")){
                    if (cl.length != 1){
                        System.out.println("Некорректное кол-во аргументов функции 'print_field_descending_average_point'");
                        return;
                    }
                    client_cm = new ClientRequest();
                    client_cm.setCommand(cl[0]);
                }
                else if(cl[0].equals("filter_starts_with_name")){
                    if (cl.length != 1){
                        System.out.println("Некорректное кол-во аргументов функции 'filter_starts_with_name'");
                        return;
                    }
                    client_cm = new ClientRequest();
                    client_cm.setCommand(cl[0] + " " + cl[1]);
                }

                else if (cl[0].equals("info")){
                    if (cl.length != 1){
                        System.out.println("Некорректное кол-во аргументов функции 'info'");
                        return;
                    }
                    client_cm = new ClientRequest();
                    client_cm.setCommand(cl[0]);
                }
                if (client_cm != null){

                    sending_data = Serializator.serialize_sending_data(client_cm);
                    try {
                        ds = new DatagramSocket();
                        ds.setSoTimeout(timeout);
                        DatagramPacket sending_dp = new DatagramPacket(sending_data, sending_data.length, InetAddress.getLocalHost(), 9254);
                        ds.send(sending_dp);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    ArrayList<byte[]> arr_bytes = new ArrayList<>();
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
                                arr_bytes.add(bytes);
                            }
                        }catch (SocketTimeoutException e){
                            System.out.println("Сервер не отвечает, повторите попытку позже");
                            System.exit(0);
                        }
                        catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    int i = 0;

                    byte[] data = new byte[arr_bytes.size() * 1024];
                    for (byte[] receiving_buf : arr_bytes) {
                        for (int j = 0; j < 1024; j++, i++) {
                            data[i] = receiving_buf[j];
                        }
                    }

                    response = Serializator.serialize_receiving_data(data);
                    System.out.println(response.getReport());
                    System.out.println(response.getContent());
                }

            } catch (Exception e) {
                System.out.println("Файл скрипта некорректный");
            }

        }
        System.out.println("Скрипт выполнен");

    }


    public static TreeSet sort_collection(Set<LabWork> hashSet){
        TreeSet myTreeSet = new TreeSet();
        myTreeSet.addAll(hashSet);
        return myTreeSet;
    }

}



