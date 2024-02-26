/*
 * @(#)JSONHandlerStreamPerson.java      1.0        15
 *
 * Copyright (c) 2024 IUH
 * All rights reserved.
 */

package iuh.JSONHandler;/*
 * @description
 * @author:  Phạm Đăng Khôi
 * @date :   15
 * @version : 1.0
 */

import iuh.Entity.Person;
import iuh.Entity.PersonAddress;
import iuh.Entity.PhoneNumbers;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonValue;
import javax.json.stream.JsonParser;
import javax.json.stream.JsonParser.Event;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class JSONHandlerStreamPerson {
    public static void main(String[] args) {
        JSONHandlerStreamPerson jsonHandlerStreamPerson = new JSONHandlerStreamPerson();
        try {
            List<Person> persons = jsonHandlerStreamPerson.readJSONFile("data\\list.json");
            for (Person person : persons) {
                System.out.println(person);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public List<Person> readJSONFile(String fileName) throws FileNotFoundException {
        Person person = null;
        List<PhoneNumbers> phoneNumbers = null;
        String keyName = "";
        PersonAddress address = null;
        List<Person> persons = new ArrayList<>();
        try(JsonParser parser = Json.createParser(new FileReader(fileName)))
        {
            while(parser.hasNext())
            {
                Event e = parser.next();
                switch (e)
                {
                    case START_OBJECT :
                        if(keyName.equals("address"))
                        {
                            address = new PersonAddress();
                        }
                        else {
                            person = new Person();
                        }
                        break;
                    case START_ARRAY:
                        if(keyName.equals("phoneNumbers")) {
                            JsonArray ja = parser.getArray();
//                            if(keyName.equals("phoneNumbers")) {
                                phoneNumbers = new ArrayList<>();
                                for (JsonValue jv : ja) {
                                    JsonObject jo = (JsonObject) jv;
                                    phoneNumbers.add(new PhoneNumbers(jo.getString("type"), jo.getString("number")));
                                }
                            }
                        break;
                    case KEY_NAME:
                        keyName = parser.getString();
                        break;
                    case VALUE_STRING:
                        if(keyName.equals("firstName"))
                        {
                            person.setFirstName(parser.getString());
                        }
                        else if(keyName.equals("lastName"))
                        {
                            person.setLastName(parser.getString());
                        }
                        else if(keyName.equals("streetAddress"))
                        {
                            address.setStreetAddress(parser.getString());
                        }
                        else if(keyName.equals("city"))
                        {
                            address.setCity(parser.getString());
                        }
                        else if(keyName.equals("state"))
                        {
                            address.setState(parser.getString());
                        }
                        break;
                    case VALUE_NUMBER:
                        if(keyName.equals("age"))
                        {
                            person.setAge(parser.getInt());
                        }
                        else if(keyName.equals("postalCode"))
                        {
                            address.setPostalCode(parser.getInt());
                        }
                        break;
                    case END_OBJECT:
                        if (person != null && address != null && phoneNumbers != null) {
                            person.setAddress(address);
                            person.setPhoneNumbers(phoneNumbers);
                            persons.add(person);
                            person = null;
                            address = null;
                            phoneNumbers = null;
                        }
                        break;
                }
            }
        }
        return persons;
    }
}
