/*
 * @(#)Restaurant.java      1.0        14
 *
 * Copyright (c) 2024 IUH
 * All rights reserved.
 */

package iuh.Entity;/*
 * @description
 * @author:  Phạm Đăng Khôi
 * @date :   14
 * @version : 1.0
 */
import java.util.List;

public class Restaurant {
    private String restaurant_id;
    private boolean is_closed;
    private String name;
    private address address;
    private String borough;
    private String cuisine;
    private List<grades> grades;
    public String getRestaurant_id() {
        return restaurant_id;
    }
    public void setRestaurant_id(String restaurant_id) {
        this.restaurant_id = restaurant_id;
    }
    public boolean isIs_closed() {
        return is_closed;
    }
    public void setIs_closed(boolean is_closed) {
        this.is_closed = is_closed;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public address getAddress() {
        return address;
    }
    public void setAddress(address address) {
        this.address = address;
    }
    public String getBorough() {
        return borough;
    }
    public void setBorough(String borough) {
        this.borough = borough;
    }
    public String getCuisine() {
        return cuisine;
    }
    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }
    public List<grades> getGrades() {
        return grades;
    }
    public void setGrades(List<grades> grades) {
        this.grades = grades;
    }
    public Restaurant(String restaurant_id, boolean is_closed, String name, address address,
                      String borough, String cuisine, List<grades> grades) {
        super();
        this.restaurant_id = restaurant_id;
        this.is_closed = is_closed;
        this.name = name;
        this.address = address;
        this.borough = borough;
        this.cuisine = cuisine;
        this.grades = grades;
    }
    public Restaurant() {

    }
    @Override
    public String toString() {
        return "Restaurant [restaurant_id=" + restaurant_id + ", is_closed=" + is_closed + ", name=" + name
                + ", address=" + address + ", borough=" + borough + ", cuisine=" + cuisine + ", grades=" + grades + "]";
    }



}
