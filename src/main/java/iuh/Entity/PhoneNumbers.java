/*
 * @(#)PhoneNumbers.java      1.0        15
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

public class PhoneNumbers {
    private String type;
    private String number;

    public PhoneNumbers() {
    }

    public PhoneNumbers(String type, String number) {
        this.type = type;
        this.number = number;
    }
    public void setNumber(String number) {
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "PhoneNumbers{" +
                "type='" + type + '\'' +
                ", number='" + number + '\'' +
                '}';
    }
}
