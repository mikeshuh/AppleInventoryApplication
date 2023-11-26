package com.example.appleinventoryapp;

public class iPhoneSearchResultsModel {

    String Model;
    String Color;
    String ScreenSize;
    String Chip;
    String Price;

    public String getModel() {
        return Model;
    }

    public void setModel(String model) {
        this.Model = model;
    }

    public String getColor() {
        return Color;
    }

    public void setColor(String color) {
        this.Color = color;
    }

    public String getScreenSize() {
        return ScreenSize;
    }

    public void setScreenSize(String screenSize) {
        this.ScreenSize = ScreenSize;
    }

    public String getChip() {
        return Chip;
    }

    public void setChip(String chip) {
        this.Chip = chip;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        this.Price = price;
    }

    public iPhoneSearchResultsModel(String Model, String Color, String ScreenSize,
                                    String Chip) {
        this.Model = Model;
        this.Color = Color;
        this.ScreenSize = ScreenSize;
        this.Chip = Chip;
        //this.Price = Price;
    }

}