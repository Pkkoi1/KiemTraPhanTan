/*
 * @(#)JSONHandlerStream.java      1.0        13
 *
 * Copyright (c) 2024 IUH
 * All rights reserved.
 */

package iuh.JSONHandler;/*
 * @description
 * @author:  Phạm Đăng Khôi
 * @date :   13
 * @version : 1.0
 */
import iuh.Entity.abc;
import iuh.Entity.address;

import javax.json.Json;
import javax.json.stream.JsonParser;
import javax.json.stream.JsonParser.Event;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class JSONHandlerStreamABC {
    public static void main(String[] args) {
        JSONHandlerStreamABC ej = new JSONHandlerStreamABC();
        try{
            List<abc> list = ej.readJson("data\\abc.json");
            System.out.println(list);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static List<abc> readJson(String json) throws FileNotFoundException {
        List<abc> list = new ArrayList<>();
        String keyName = "";
        address address = null;
        abc abc = null;
        boolean isAddress = false;

        try (JsonParser parser = Json.createParser(new FileReader(json))) {
            while (parser.hasNext()) {
                Event event = parser.next();
                switch (event) {
                    case START_OBJECT:
                        if (keyName.equals("address")) {
                            address = new address();
                            isAddress = true;
                        } else
                            abc = new abc();
                        break;
                    case START_ARRAY:
                        break;
                    case KEY_NAME:
                        keyName = parser.getString();
                        break;
                    case VALUE_STRING:
//                        if (isAddress) {
//                            address.setCity(parser.getString());
//                        } else {
                            if (keyName.equals("firstName")) {
                                abc.setFirstName(parser.getString());
//                            }
                        }
                        break;
                    case VALUE_NUMBER:
                        if (!isAddress && keyName.equals("age")) {
                            abc.setAge(parser.getInt());
                        }
                        break;
                    case END_OBJECT:
                        if (isAddress) {
                            abc.setAddress(address);
                            isAddress = false;
                        } else {
                            list.add(abc);
                        }
                        break;
                    default:
                        break;
                }
            }
        }
        return list;
    }
}
