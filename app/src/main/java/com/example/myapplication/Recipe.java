package com.example.myapplication;

enum Foods
{
    BreakFast , Lunch , Dinner , DietFood , Salads , Desserts;
}


public class Recipe {
    private String photo;
    private String name;
    private String description;
    private String instruction;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "photo='" + photo + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", instruction='" + instruction + '\'' +
                ", rating='" + rating + '\'' +
                '}';
    }

    public Recipe(String photo, String name, String description, String instruction, String rating) {
        this.photo = photo;
        this.name = name;
        this.description = description;
        this.instruction = instruction;
        this.rating = rating;
    }

    private String rating;

}
