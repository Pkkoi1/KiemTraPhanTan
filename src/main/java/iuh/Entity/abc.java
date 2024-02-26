/*
 * @(#)abc.java      1.0        13
 *
 * Copyright (c) 2024 IUH
 * All rights reserved.
 */

package iuh.Entity;/*
 * @description
 * @author:  Phạm Đăng Khôi
 * @date :   13
 * @version : 1.0
 */

public class abc {
    private String firstName;
    private int age;
    private  address address;

    public abc(String firstName, int age, iuh.Entity.address address) {
        this.firstName = firstName;
        this.age = age;
        this.address = address;
    }
    public abc() {
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public iuh.Entity.address getAddress() {
        return address;
    }
    public void setAddress(iuh.Entity.address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "abc{" +
                "firstName='" + firstName + '\'' +
                ", age=" + age +
                ", address=" + address +
                '}';
    }
}
