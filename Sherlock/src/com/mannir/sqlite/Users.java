package com.mannir.sqlite;

public class Users {

    public int id;
    public String name;
    public String gender;
    public String height;
    public String age;
    public String haircolor;
    public String comment;
    public String email;

    // constructor
    public Users(int id, String name, String gender, String height, String age, String haircolor, String comment, String email) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.height = height;
        this.age = age;
        this.haircolor = haircolor;
        this.comment = comment;
        this.email = email;
    }

    // 2nd constructor
    public Users(String name, String gender, String height, String age, String haircolor, String comment, String email) {
        this.name = name;
        this.gender = gender;
        this.height = height;
        this.age = age;
        this.haircolor = haircolor;
        this.comment = comment;
        this.email = email;
    }

}