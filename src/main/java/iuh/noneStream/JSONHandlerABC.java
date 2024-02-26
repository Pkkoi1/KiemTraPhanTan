/*
 * @(#)JSONHandlerABC.java      1.0        14
 *
 * Copyright (c) 2024 IUH
 * All rights reserved.
 */

package iuh.noneStream;/*
 * @description
 * @author:  Phạm Đăng Khôi
 * @date :   14
 * @version : 1.0
 */
import iuh.Entity.abc;
import iuh.Entity.address;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;


public class JSONHandlerABC {
    public static void main(String[] args) {
        JSONHandlerABC ej = new JSONHandlerABC();
        try{
            List<abc> abc = ej.fromFile("data\\abc.json");
            for (abc a : abc) {
                System.out.println(a);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static List<abc> fromFile(String json) throws FileNotFoundException {
        List<abc> abcList = new ArrayList<>();

        try(JsonReader reader = Json.createReader(new FileReader(json)))
        {
            JsonArray ja = reader.readArray();
            for (int i = 0; i < ja.size(); i++) {
                JsonObject jo = ja.getJsonObject(i);
                abc abc = new abc();
                address address = new address();

                abc.setFirstName(jo.getString("firstName"));
                abc.setAge(jo.getInt("age"));

                JsonObject joAddress = jo.getJsonObject("address");
//                address.setCity(joAddress.getString("city"));

                abc.setAddress(address);
                abcList.add(abc);
            }
        }
        return abcList;
    }
}
