package iuh.JSONHandler;

import iuh.Entity.Restaurant;
import iuh.Entity.address;
import iuh.Entity.grades;

import javax.json.*;
import javax.json.stream.JsonParser;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RestaurantParser {
    public static void main(String[] args) {
        String filePath = "data\\res.json";
        try (FileReader fileReader = new FileReader(filePath)) {
            JsonParser jsonParser = Json.createParser(fileReader);
            Restaurant restaurant = parseJson(jsonParser);
            System.out.println(restaurant);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Restaurant parseJson(JsonParser jsonParser) {
        Restaurant restaurant = new Restaurant();
        List<grades> grades = new ArrayList<>();
        address address = new address();
        String keyName = null;
        while (jsonParser.hasNext()) {
            JsonParser.Event event = jsonParser.next();
            switch (event) {
                case START_ARRAY:
                    if ("grades".equals(keyName)) {
                        grades = new ArrayList<>();
                    }
                    break;
                case KEY_NAME:
                    keyName = jsonParser.getString();
                    break;
                case VALUE_STRING:
                    setStringValue(restaurant, grades, address, keyName, jsonParser.getString());
                    break;
                case VALUE_NUMBER:
                    setNumberValue(restaurant, grades, address, keyName, jsonParser.getInt());
                    break;
                case VALUE_TRUE:
                    setBooleanValue(restaurant, grades, address, keyName, true);
                    break;
                case VALUE_FALSE:
                    setBooleanValue(restaurant, grades, address, keyName, false);
                    break;

                case END_ARRAY:
                    if ("grades".equals(keyName)) {
                        restaurant.setGrades(grades);
                    }
                    break;
                case START_OBJECT:
                    if ("address".equals(keyName)) {
                        address = new address();
                    }
                    break;
                case END_OBJECT:
                    if ("address".equals(keyName)) {
                        restaurant.setAddress(address);
                    }
                    break;
            }
        }
        return restaurant;
    }

    private static void setStringValue(Restaurant restaurant, List<grades> grades, address address, String keyName, String value) {
        switch (keyName) {
            case "restaurant_id":
                restaurant.setRestaurant_id(value);
                break;
            case "name":
                restaurant.setName(value);
                break;
            case "borough":
                restaurant.setBorough(value);
                break;
            case "cuisine":
                restaurant.setCuisine(value);
                break;
            case "building":
                address.setBuilding(value);
                break;
            case "street":
                address.setStreet(value);
                break;
            case "zipcode":
                address.setZipcode(value);
                break;
            case "grade":
                grades grade = new grades();
                grade.setGrade(value);
                grades.add(grade);
                break;
        }
    }

    private static void setNumberValue(Restaurant restaurant, List<grades> grades, address address, String keyName, int value) {
        switch (keyName) {
            case "score":
                grades grade = new grades();
                grade.setScore(value);
                grades.add(grade);
                break;
        }
    }

    private static void setBooleanValue(Restaurant restaurant, List<grades> grades, address address, String keyName, boolean value) {
        switch (keyName) {
            case "is_closed":
                restaurant.setIs_closed(value);
                break;
        }
    }


}