/*
 * @(#)grades.java      1.0        14
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

import java.time.LocalDate;

public class grades {
    private LocalDate date;
    private String grade;
    private int score;

    public grades() {
    }

    public grades(LocalDate date, String grade, int score) {
        this.date = date;
        this.grade = grade;
        this.score = score;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Grade{" +
                "date=" + date +
                ", grade='" + grade + '\'' +
                ", score=" + score +
                '}';
    }
}
