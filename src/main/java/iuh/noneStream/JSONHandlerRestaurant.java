/*
 * @(#)JSONHandlerRestaurant.java      1.0        17
 *
 * Copyright (c) 2024 IUH
 * All rights reserved.
 */

package iuh.noneStream;/*
 * @description
 * @author:  Phạm Đăng Khôi
 * @date :   17
 * @version : 1.0
 */

import iuh.Entity.Restaurant;
import iuh.Entity.address;
import iuh.Entity.grades;

import javax.json.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class JSONHandlerRestaurant {
    public static void main(String[] args) {
        try {
            List<Restaurant> restaurants = fromFile("data\\restaurant.json");
            for (Restaurant restaurant : restaurants) {
                System.out.println(restaurant);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static List<Restaurant> fromFile(String path) throws FileNotFoundException {
        Restaurant restaurant = null;
        List<Restaurant> restaurants = new ArrayList<>();
        address address = null;
        List<grades> grades = new ArrayList<>();
        List<Double> coord = new ArrayList<>();
        LocalDate date = null;

        try(JsonReader jr = Json.createReader(new FileReader(path)))
        {
            JsonArray ja = jr.readArray();
            for(JsonValue jv: ja)
            {
                JsonObject jo = (JsonObject) jv;
                 if(jo != null)
                 {
                     restaurant = new Restaurant();
                        restaurant.setRestaurant_id(jo.getString("restaurant_id"));
                        restaurant.setIs_closed(jo.getBoolean("is_closed"));
                        restaurant.setName(jo.getString("name"));
                        restaurant.setBorough(jo.getString("borough"));
                        restaurant.setCuisine(jo.getString("cuisine"));

                     JsonObject joAddress = jo.getJsonObject("address");
                        address = new address();
                        address.setBuilding(joAddress.getString("building"));
                        address.setStreet(joAddress.getString("street"));
                        address.setZipcode(joAddress.getString("zipcode"));
                        restaurant.setAddress(address);
                     JsonArray coordArray = joAddress.getJsonArray("coord");
                     address.setCoord(new ArrayList<>());
                        for(JsonValue jvCoord: coordArray)
                        {
                            coord.add(Double.parseDouble(jvCoord.toString()));
                        }
                        address.getCoord().addAll(coord);
                        coord.clear();

                        JsonArray jaGrades = jo.getJsonArray("grades");
                        grades = new ArrayList<>();
                        for(JsonValue jvGrade: jaGrades)
                        {
                            JsonObject joGrade = (JsonObject) jvGrade;
                            grades grade = new grades();
                            JsonObject joDate = joGrade.getJsonObject("date");
                            date = LocalDate.of(joDate.getInt("year"), joDate.getInt("month"), joDate.getInt("day"));
                            grade.setDate(date);
                            grade.setGrade(joGrade.getString("grade"));
                            grade.setScore(joGrade.getInt("score"));
                            grades.add(grade);

                        }

                        restaurant.setGrades(grades);
                        restaurants.add(restaurant);


                 }

            }
        }
        return restaurants;
    }
}
