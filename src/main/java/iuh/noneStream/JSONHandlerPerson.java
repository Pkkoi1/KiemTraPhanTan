/*
 * @(#)JSONHandlerPerson.java      1.0        16
 *
 * Copyright (c) 2024 IUH
 * All rights reserved.
 */

package iuh.noneStream;/*
 * @description
 * @author:  Phạm Đăng Khôi
 * @date :   16
 * @version : 1.0
 */

import iuh.Entity.Person;
import iuh.Entity.PersonAddress;
import iuh.Entity.PhoneNumbers;

import javax.json.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class JSONHandlerPerson {
    public static void main(String[] args) throws FileNotFoundException {
        List<Person> p = fromFile("data\\list.json");
        for (Person person : p) {
            System.out.println(person);
        }


    }
    public static List<Person> fromFile(String path) throws FileNotFoundException {
        Person person = null;
        PersonAddress address = null;
        List<Person> persons = new ArrayList<>();
        try(JsonReader reader = Json.createReader(new FileReader(path)))
        {
            JsonArray personArray = reader.readArray();
            for(JsonValue jV : personArray)
            {
                JsonObject personObject = (JsonObject) jV;
                if (personObject != null) {
                    person = new Person();
                    person.setFirstName(personObject.getString("firstName"));
                    person.setLastName(personObject.getString("lastName"));
                    person.setAge(personObject.getInt("age"));

                    JsonObject addressObject = personObject.getJsonObject("address");
                    address = new PersonAddress();
                    address.setStreetAddress(addressObject.getString("streetAddress"));
                    address.setCity(addressObject.getString("city"));
                    address.setState(addressObject.getString("state"));
                    address.setPostalCode(addressObject.getInt("postalCode"));
                    person.setAddress(address);

                    JsonArray ja = personObject.getJsonArray("phoneNumbers");
                    List<PhoneNumbers> phoneNumbers = new ArrayList<>();
                    for (JsonValue jv : ja) {
                        JsonObject jo = (JsonObject) jv;
                        PhoneNumbers ph = new PhoneNumbers(jo.getString("type"), jo.getString("number"));
                        phoneNumbers.add(ph);
                    }
                    person.setPhoneNumbers(phoneNumbers);
                    persons.add(person);
                }
            }
        }
        return persons;
    }
}
