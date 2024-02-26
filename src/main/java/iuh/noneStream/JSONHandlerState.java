/*
 * @(#)JSONHandlerState.java      1.0        19
 *
 * Copyright (c) 2024 IUH
 * All rights reserved.
 */

package iuh.noneStream;/*
 * @description
 * @author:  Phạm Đăng Khôi
 * @date :   19
 * @version : 1.0
 */

import javax.json.*;
import iuh.Entity.States;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class JSONHandlerState {
    public static void main(String[] args) {
//        JSONHandlerState ej = new JSONHandlerState();
//        try{
//            List<States> list = ej.readJson("data\\states.json");
//            for (States state : list) {
//                System.out.println(state);
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
        List<States> list = List.of(
                new States("Alabama", "AL", "Montgomery", 1819, 1),
                new States("Alaska", "AK", "Juneau", 1959, 2),
                new States("Arizona", "AZ", "Phoenix", 1912, 3),
                new States("Arkansas", "AR", "Little Rock", 1836, 4),
                new States("California", "CA", "Sacramento", 1850, 5)
        );
        String json = toJSON(list);
        System.out.println(json);
        try {
            export(json, "data\\states2.json");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public List<States> readJson(String json) throws FileNotFoundException {
        List<States> list = new ArrayList<>();
        States state = null;
        try(JsonReader reader = Json.createReader(new FileReader(json));) {

            JsonArray jsonArray = reader.readArray();
            for (JsonValue jv : jsonArray) {

                JsonObject jo = (JsonObject) jv;
                if(jo != null)
                {

                    state = new States();
                    state.setStatName(jo.getString("StateName"));
                    state.setAbbreviation(jo.getString("Abbreviation"));
                    state.setCapital(jo.getString("Capital"));
                    state.setStateHood(jo.getInt("Statehood"));
                    state.setID(jo.getInt("ID"));
                    list.add(state);

                }
            }
        }
        return list;
    }
    public static String toJSON(List<States> list) {
        StringWriter st = new StringWriter();
        try (JsonWriter jW = Json.createWriter(st)) {
            JsonArrayBuilder aBuilder = Json.createArrayBuilder();

            list.forEach(s -> {
                JsonObjectBuilder oBuilder = Json.createObjectBuilder();
                oBuilder.add("StateName", s.getStatName());
                oBuilder.add("Abbreviation", s.getAbbreviation());
                oBuilder.add("Capital", s.getCapital());
                oBuilder.add("Statehood", s.getStateHood());
                oBuilder.add("ID", s.getID());
                JsonObject jO = oBuilder.build();
                aBuilder.add(jO);
            });

            JsonArray jsonArray = aBuilder.build();
            jW.writeArray(jsonArray);
        }
        return st.toString();
    }
public static void export(String json, String path) throws FileNotFoundException {
        PrintWriter out = new PrintWriter(new FileOutputStream(path), true);
        out.println(json);
        out.close();
}
}
