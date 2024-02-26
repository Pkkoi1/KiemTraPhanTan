/*
 * @(#)StreamDemo.java      1.0        26
 *
 * Copyright (c) 2024 IUH
 * All rights reserved.
 */

package iuh.Demo;/*
 * @description
 * @author:  Phạm Đăng Khôi
 * @date :   26
 * @version : 1.0
 */

import iuh.Entity.Restaurant;
import iuh.JSONHandler.JSONHandlerStreamRes;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class StreamDemo {
    public static void main(String[] args) throws FileNotFoundException {
        JSONHandlerStreamRes jhs = new JSONHandlerStreamRes();
        List<Restaurant> list = jhs.fromFile("data\\restaurant.json");
        for (Restaurant restaurant : list) {
            System.out.println(restaurant);
        }
    }
}
