package com.epam.lisovyn.model;

/**
 * Created by Andrii_Lisovyn on 14.4.2016.
 */
public class UserModel extends Model {

    private String name;
    private String secondName;

    public UserModel() {
    }

    public UserModel(int id, String name, String secondName) {
        super(id);
        this.name = name;
        this.secondName = secondName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

}
