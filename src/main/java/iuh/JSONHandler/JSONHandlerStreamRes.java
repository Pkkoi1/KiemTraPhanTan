/*
 * @(#)JSONHandlerStreamRes.java      1.0        14
 *
 * Copyright (c) 2024 IUH
 * All rights reserved.
 */

package iuh.JSONHandler;/*
 * @description
 * @author:  Phạm Đăng Khôi
 * @date :   14
 * @version : 1.0
 */

import iuh.Entity.Restaurant;
import iuh.Entity.address;
import iuh.Entity.grades;

import javax.json.*;
import javax.json.stream.JsonGenerator;
import javax.json.stream.JsonParser;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.json.stream.JsonParser.Event;
public class JSONHandlerStreamRes {
    public static void main(String[] args) throws IOException {
        JSONHandlerStreamRes ej = new JSONHandlerStreamRes();
        try{
            List<Restaurant> res = fromFile("data\\restaurant.json");
//            System.out.println(res);
            for (Restaurant restaurant : res) {
                System.out.println(restaurant);
            }
            export(res, "data\\restaurant2.json");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static List<Restaurant> fromFile(String path) throws FileNotFoundException {
        Restaurant res = null;
        address address = null;
        String keyName = "";
        List<grades> grades = null;
        LocalDate date = null;
        List<Restaurant> restaurants = new ArrayList<>();

        try(JsonParser parser = Json.createParser(new FileReader(path))) {
            while (parser.hasNext()) {
                Event e = parser.next();
                switch (e) {
                    case START_OBJECT:
                        if (keyName.equals("address")) {
                            address = new address();
                        } else {
                            res = new Restaurant();
                            grades = new ArrayList<>();
                        }
                        break;
                    case START_ARRAY:
                        if (keyName.equals("grades")) {
                            JsonArray array = parser.getArray();
                            grades = new ArrayList<>();
                            for (JsonValue jv : array) {
                                if (jv instanceof JsonObject) {
                                    JsonObject joPh = (JsonObject) jv;
                                    JsonObject joDate = joPh.getJsonObject("date");
                                    date = LocalDate.of(
                                            joDate.getInt("year"),
                                            joDate.getInt("month"),
                                            joDate.getInt("day"));
                                    grades.add(new grades(date,
                                            joPh.getString("grade"),
                                            joPh.getInt("score")));
                                }
                            }
                        } else if (keyName.equals("coord")) {
                            ArrayList<Double> coord = new ArrayList<>();
                            JsonArray array = parser.getArray();
                            for (int i = 0; i < array.size(); i++) {
                                JsonValue jsonValue = array.get(i);
                                if (jsonValue instanceof JsonNumber) {
                                    double value2 = Double.parseDouble(((JsonNumber) jsonValue)
                                            .toString());
                                    coord.add(value2);
                                }
                            }
                            address.setCoord(coord);
                        }
                        break;
                    case KEY_NAME:
                        keyName = parser.getString();
                        break;
                    case VALUE_STRING:
                        if (keyName.equals("restaurant_id")) {
                            res.setRestaurant_id(parser.getString());
                            System.out.println(res.getRestaurant_id());
                        } else if (keyName.equals("borough")) {
                            res.setBorough(parser.getString());
                        } else if (keyName.equals("cuisine") && parser.getString().equals("Japanese") || parser.getString().equals("japanese")) {
                            res.setCuisine(parser.getString());
                        } else if (keyName.equals("street")) {
                            address.setStreet(parser.getString());
                        } else if (keyName.equals("zipcode")) {
                            address.setZipcode(parser.getString());
                        } else if (keyName.equals("building")) {
                            address.setBuilding(parser.getString());
                        } else if (keyName.equals("name")) {
                            res.setName(parser.getString());
                        }
                        break;
                    case VALUE_FALSE:
                        res.setIs_closed(false);
                        break;
                    case VALUE_TRUE:
                        res.setIs_closed(true);
                        break;
                    case VALUE_NUMBER:
                        break;
                    case END_OBJECT:
                        if (res != null && res.getCuisine() != null && res.getCuisine().equals("Japanese")) {
                            res.setAddress(address);
                            res.setGrades(grades);
                            restaurants.add(res);

                        }
                        break;
                    default:
                        break;
                }
            }
        }
        return restaurants;}
    //
    public static List<Restaurant> fromJapaneseFile(String path) throws FileNotFoundException {
        Restaurant res = null;
        address address = null;
        String keyName = "";
        List<grades> grades = null;
        LocalDate date = null;
        List<Restaurant> restaurants = new ArrayList<>();


        try(JsonParser parser = Json.createParser(new FileReader(path))) {
            while (parser.hasNext()) {
                Event event = parser.next();
                switch (event) {
                    case START_OBJECT:
                        if (keyName.equals("address")) {
                            address = new address();
                        } else {
                            res = new Restaurant();
                            grades = new ArrayList<>();
                        }
                        break;
                    case START_ARRAY:

                        if (keyName.equals("grades")) {
                            JsonArray array = parser.getArray();
                            grades = new ArrayList<>();
                            for (JsonValue jv : array) {
                                if (jv instanceof JsonObject) {
                                    JsonObject joPh = (JsonObject) jv;
                                    JsonObject joDate = joPh.getJsonObject("date");
                                    date = LocalDate.of(joDate.getInt("year"), joDate.getInt("month"),
                                            joDate.getInt("day"));
                                    grades.add(new grades(date, joPh.getString("grade"), joPh.getInt("score")));
                                }
                            }
                        } else if (keyName.equals("coord")) {
                            ArrayList<Double> coord = new ArrayList<>();
                            JsonArray array = parser.getArray();
                            for (int i = 0; i < array.size(); i++) {
                                JsonValue jsonValue = array.get(i);
                                if (jsonValue instanceof JsonNumber) {
                                    double value2 = Double.parseDouble(((JsonNumber) jsonValue).toString());
                                    coord.add(value2);
                                }
                            }
                            address.setCoord(coord);
                        }
                        break;
                    case KEY_NAME:
                        keyName = parser.getString();
                        break;
                    case VALUE_STRING:
                        if (keyName.equals("restaurant_id")) {
                            res.setRestaurant_id(parser.getString());
                            System.out.println(res.getRestaurant_id());
                        } else if (keyName.equals("borough")) {
                            res.setBorough(parser.getString());
                        } else if (keyName.equals("cuisine") && parser.getString().equals("Japanese") || parser.getString().equals("japanese")) {
                            res.setCuisine(parser.getString());
                        } else if (keyName.equals("street")) {
                            address.setStreet(parser.getString());
                        } else if (keyName.equals("zipcode")) {
                            address.setZipcode(parser.getString());
                        } else if (keyName.equals("building")) {
                            address.setBuilding(parser.getString());
                        } else if (keyName.equals("name")) {
                            res.setName(parser.getString());
                        }
                        break;
                    case VALUE_FALSE:
                        res.setIs_closed(false);
                        break;
                    case VALUE_TRUE:
                        res.setIs_closed(true);
                        break;
                    case VALUE_NUMBER:
                        break;
                    case END_OBJECT:
                        if (res != null && res.getCuisine() != null && res.getCuisine().equals("Japanese")) {
                            res.setAddress(address);
                            res.setGrades(grades);
                            restaurants.add(res);
//                                    System.out.println(res);
                        }
                        break;
                    default:
                        break;
                }
            }
        }
        return restaurants;
    }
    public static void export(List<Restaurant> res, String path) throws IOException {
        try (JsonGenerator gen = Json.createGenerator(new FileWriter(path))) {
            gen.writeStartArray();
            res.forEach(restaurant -> {
                address ad = restaurant.getAddress();
                gen.writeStartObject()
                        .write("restaurant_id", restaurant.getRestaurant_id())
                        .write("is_closed", restaurant.isIs_closed())
                        .write("name", restaurant.getName())
                        .write("borough", restaurant.getBorough())
                        .write("cuisine", restaurant.getCuisine())
                        .writeStartObject("address")
                        .write("building", ad.getBuilding())
                        .write("street", ad.getStreet())
                        .write("zipcode", ad.getZipcode())
                        .writeStartArray("coord")
                        .write(ad.getCoord().get(0))
                        .write(ad.getCoord().get(1))
                        .writeEnd()
                        .writeEnd()
                        .writeStartArray("grades");
                restaurant.getGrades().forEach(grade -> {
                    gen.writeStartObject()
                            .writeStartObject("date")
                            .write("year", grade.getDate().getYear())
                            .write("month", grade.getDate().getMonthValue())
                            .write("day", grade.getDate().getDayOfMonth())
                            .writeEnd()
                            .write("grade", grade.getGrade())
                            .write("score", grade.getScore())
                            .writeEnd();
                });
                gen.writeEnd()
                        .writeEnd();
            });
            gen.writeEnd();
        }

        }
}
