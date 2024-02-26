/*
 * @(#)States.java      1.0        19
 *
 * Copyright (c) 2024 IUH
 * All rights reserved.
 */

package iuh.Entity;/*
 * @description
 * @author:  Phạm Đăng Khôi
 * @date :   19
 * @version : 1.0
 */

public class States {
    private String statName;
    private String abbreviation;
    private String capital;
    private int StateHood;
    private int ID;

    public States(String statName, String abbreviation, String capital, int stateHood, int ID) {
        this.statName = statName;
        this.abbreviation = abbreviation;
        this.capital = capital;
        StateHood = stateHood;
        this.ID = ID;
    }
    public States(){

    }
    public String getStatName() {
        return statName;
    }

    public void setStatName(String statName) {
        this.statName = statName;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public int getStateHood() {
        return StateHood;
    }

    public void setStateHood(int stateHood) {
        StateHood = stateHood;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    @Override
    public String toString() {
        return "States{" +
                "statName='" + statName + '\'' +
                ", abbreviation='" + abbreviation + '\'' +
                ", capital='" + capital + '\'' +
                ", StateHood=" + StateHood +
                ", ID=" + ID +
                '}';
    }
}
