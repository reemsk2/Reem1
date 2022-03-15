package com.example.myapplication;


import java.io.Serializable;

public class Recipe implements Serializable {
    private static String Name;
    private static String name;
    private String address;
    private String phone;
    private String photo;
    private String description;

    public Recipe(String address , String photo , String phone , String description , String name , RestFoods category){
        this.description = description;
        this.Name = name;
        this.photo = photo;
        this.address = address;
        this.phone = phone;
    }

    public static int getDesc() {
        return 0;
    }

    public static int getAdress() {
        return 0;
    }

    public static int getPhone() {
        return 0;
    }

    public static Object getPhoto() {
        return getName();
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public void setPhone(String phone) {
        this.phone = phone;
    }



    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public static String getName() {
        return name;
    }

    public String getDescription() {return description; }

    public void setDescription(String description) {this.description = description;}

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", photo='" + photo + '\'' +
                ", name='" + name + '\'' +
                '}';
    }}





