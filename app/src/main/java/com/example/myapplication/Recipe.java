package com.example.myapplication;


public class Recipe {
    private String address;
    private RestFoods category;
    private String phone;
    private String photo;
    private String name;

    public Recipe(String name, String description, String address, RestFoods restFoods, String photo, String phone) {

    }
    public Recipe(String address , String photo , String phone , String description , String name , RestFoods category){
        this.description = description;
        this.name = name;
        this.photo = photo;
        this.address = address;
        this.category = category;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "address='" + address + '\'' +
                ", category=" + category +
                ", phone='" + phone + '\'' +
                ", photo='" + photo + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public RestFoods getCategory() {
        return category;
    }

    public void setCategory(RestFoods category) {
        this.category = category;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


