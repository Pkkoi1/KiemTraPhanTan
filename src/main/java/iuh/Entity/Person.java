/*
 * @(#)Person.java      1.0        15
 *
 * Copyright (c) 2024 IUH
 * All rights reserved.
 */

package iuh.Entity;/*
 * @description
 * @author:  Phạm Đăng Khôi
 * @date :   15
 * @version : 1.0
 */

import java.util.List;

public class Person {
    private String firstName;
    private String lastName;
    private int age;

    private PersonAddress address;
    private List<PhoneNumbers> phoneNumbers;

    public Person() {
    }

    public Person(String firstName, String lastName, int age, PersonAddress address, List<PhoneNumbers> phoneNumbers) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.address = address;
        this.phoneNumbers = phoneNumbers;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public PersonAddress getAddress() {
        return address;
    }
    public void setAddress(PersonAddress address) {
        this.address = address;
    }
    public List<PhoneNumbers> getPhoneNumbers() {
        return phoneNumbers;
    }
    public void setPhoneNumbers(List<PhoneNumbers> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", address=" + address +
                ", phoneNumbers=" + phoneNumbers +
                '}';
    }
}
