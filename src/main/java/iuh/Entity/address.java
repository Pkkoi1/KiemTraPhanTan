/*
 * @(#)address.java      1.0        13
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

import java.util.ArrayList;

public class address {
        private String building;
        private ArrayList<Double> coord;
        private String street;
        private String zipcode;
        public String getBuilding() {
            return building;
        }
        public void setBuilding(String building) {
            this.building = building;
        }
        public ArrayList<Double> getCoord() {
            return coord;
        }
        public void setCoord(ArrayList<Double> coord2) {
            this.coord = coord2;
        }
        public String getStreet() {
            return street;
        }
        public void setStreet(String street) {
            this.street = street;
        }
        public String getZipcode() {
            return zipcode;
        }
        public void setZipcode(String zipcode) {
            this.zipcode = zipcode;
        }
        public address(String building, ArrayList<Double> coord, String street, String zipcode) {
            super();
            this.building = building;
            this.coord = coord;
            this.street = street;
            this.zipcode = zipcode;
        }
        public address() {
            super();
            // TODO Auto-generated constructor stub
        }
        @Override
        public String toString() {
            return "address [building=" + building + ", coord=" + coord + ", street=" + street + ", zipcode=" + zipcode
                    + "]";
        }
}
