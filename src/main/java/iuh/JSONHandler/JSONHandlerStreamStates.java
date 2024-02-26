/*
 * @(#)JSONHandlerStreamStates.java      1.0        19
 *
 * Copyright (c) 2024 IUH
 * All rights reserved.
 */

package iuh.JSONHandler;/*
 * @description
 * @author:  Phạm Đăng Khôi
 * @date :   19
 * @version : 1.0
 */

import iuh.Entity.States;

import javax.json.Json;
import javax.json.stream.JsonParser;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class JSONHandlerStreamStates {
    public static void main(String[] args) {
        JSONHandlerStreamStates ej = new JSONHandlerStreamStates();
        Scanner sc = new Scanner(System.in);

        int choice;
        Thread time = null;
        do {
            System.out.println("Chọn hoạt động: \n" +
                    "1. Đọc file json\n" +
                    "2. Tìm bang có Abbreviation cụ thể\n" +
                    "3. Tìm bang có tên cụ thể\n" +
                    "4. Tìm bang có Capital cụ thể\n" +
                    "5 Keết thúc");
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    try {
                        List<States> list = ej.fromFile("data\\states.json");
                        for (States s : list) {
                            System.out.println(s);
                        }
                        time.sleep(1000);
                    } catch (FileNotFoundException | InterruptedException e) {
                        e.printStackTrace();
                    }
                    break;
                case 2:
                    System.out.println("Nhập Abbreviation cần tìm: ");
                    String ab = sc.next();
                    try {
                        List<States> list = ej.findAbb("data\\states.json", ab);
                        for (States s : list) {
                            System.out.println(s);
                        }
                        time.sleep(1000);
                    } catch (FileNotFoundException | InterruptedException e) {
                        e.printStackTrace();
                    }
                    break;
                case 3:
                    Scanner sc1 = new Scanner(System.in);
                    System.out.println("Nhập tên bang cần tìm: ");
                    String name = sc1.nextLine();;
                    try {
                        List<States> list = ej.findName("data\\states.json", name);
                        for (States s : list) {
                            System.out.println(s);
                        }
                        time.sleep(1000);
                    } catch (FileNotFoundException | InterruptedException e) {
                        e.printStackTrace();
                    }
                    break;
                case 4:
                    Scanner sc2 = new Scanner(System.in);
                    System.out.println("Nhập tên thủ đô cần tìm: ");
                    String cap = sc2.nextLine();
                    try {
                        List<States> list = ej.findCap("data\\states.json", cap);
                        for (States s : list) {
                            System.out.println(s);
                        }
                        time.sleep(1000);
                    } catch (FileNotFoundException | InterruptedException e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    System.exit(0);
                    break;
            }
        } while (choice <= 5);
    }
    public List<States> fromFile(String path) throws FileNotFoundException {
        List<States> states = new ArrayList<>();
        States state = null;
        String keyName = "";

        try(JsonParser jP = Json.createParser(new FileReader(path)))
        {
            while(jP.hasNext())
            {
                JsonParser.Event e = jP.next();
                switch(e)
                {
                    case START_OBJECT:
                        state = new States();
                        break;
                    case KEY_NAME:
                        keyName = jP.getString();
                        break;
                    case VALUE_STRING:
                       if(keyName.equals("StateName"))
                           state.setStatName(jP.getString());
                       else if(keyName.equals("Abbreviation"))
                           state.setAbbreviation(jP.getString());
                       else if(keyName.equals("Capital"))
                           state.setCapital(jP.getString());
                        break;
                    case VALUE_NUMBER:
                        if (keyName.equals("Statehood"))
                            state.setStateHood(jP.getInt());
                        else if (keyName.equals("ID"))
                            state.setID(jP.getInt());
                        break;
                    case END_OBJECT:
                        states.add(state);
                        break;
                }
            }
        }
        return states;
    };

    public List<States> findAbb(String path, String key) throws FileNotFoundException {
        List<States> states = new ArrayList<>();
        States state = null;
        String keyName = "";

        try(JsonParser jP = Json.createParser(new FileReader(path)))
        {
            while(jP.hasNext())
            {
                JsonParser.Event e = jP.next();
                switch(e)
                {
                    case START_OBJECT:
                        state = new States();
                        break;
                    case KEY_NAME:
                        keyName = jP.getString();
                        break;
                    case VALUE_STRING:
                        if(keyName.equals("StateName"))
                            state.setStatName(jP.getString());
                        else if(keyName.equals("Abbreviation") && jP.getString().trim().equals(key))
                            state.setAbbreviation(jP.getString());
                        else if(keyName.equals("Capital"))
                            state.setCapital(jP.getString());
                        break;
                    case VALUE_NUMBER:
                        if (keyName.equals("Statehood"))
                            state.setStateHood(jP.getInt());
                        else if (keyName.equals("ID"))
                            state.setID(jP.getInt());
                        break;
                    case END_OBJECT:
                        if (state.getAbbreviation() != null && state.getStatName() != null && state.getCapital() != null) {
                            states.add(state);
                        }
                        break;
                }
            }
        }
        return states;
    };
    public List<States> findName(String path, String key) throws FileNotFoundException {
        List<States> states = new ArrayList<>();
        States state = null;
        String keyName = "";

        try(JsonParser jP = Json.createParser(new FileReader(path)))
        {
            while(jP.hasNext())
            {
                JsonParser.Event e = jP.next();
                switch(e)
                {
                    case START_OBJECT:
                        state = new States();
                        break;
                    case KEY_NAME:
                        keyName = jP.getString();
                        break;
                    case VALUE_STRING:
                        if(keyName.equals("StateName") && jP.getString().equals(key))
                            state.setStatName(jP.getString());
                        else if(keyName.equals("Abbreviation"))
                            state.setAbbreviation(jP.getString());
                        else if(keyName.equals("Capital"))
                            state.setCapital(jP.getString());
                        break;
                    case VALUE_NUMBER:
                        if (keyName.equals("Statehood"))
                            state.setStateHood(jP.getInt());
                        else if (keyName.equals("ID"))
                            state.setID(jP.getInt());
                        break;
                    case END_OBJECT:
                        if (state != null && state.getStatName() != null) {
                            states.add(state);
                        }
                        break;
                }
            }
        }
        return states;
    };
    public List<States> findCap(String path, String key) throws FileNotFoundException {
        List<States> states = new ArrayList<>();
        States state = null;
        String keyName = "";

        try(JsonParser jP = Json.createParser(new FileReader(path)))
        {
            while(jP.hasNext())
            {
                JsonParser.Event e = jP.next();
                switch(e)
                {
                    case START_OBJECT:
                        state = new States();
                        break;
                    case KEY_NAME:
                        keyName = jP.getString();
                        break;
                    case VALUE_STRING:
                        if(keyName.equals("StateName"))
                            state.setStatName(jP.getString());
                        else if(keyName.equals("Abbreviation"))
                            state.setAbbreviation(jP.getString());
                        else if(keyName.equals("Capital") && jP.getString().equals(key))
                            state.setCapital(jP.getString());
                        break;
                    case VALUE_NUMBER:
                        if (keyName.equals("Statehood"))
                            state.setStateHood(jP.getInt());
                        else if (keyName.equals("ID"))
                            state.setID(jP.getInt());
                        break;
                    case END_OBJECT:
                        if (state != null && state.getCapital() != null) {
                            states.add(state);
                        }
                        break;
                }
            }
        }
        return states;
    };
}
