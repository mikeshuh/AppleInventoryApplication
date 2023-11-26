package com.example.appleinventoryapp;

public class iPhoneSearchResultsModel {

    private String Model;
    private String Color;
    private String Size;
    private String Chip;
    private String Price;


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

    public String getSize() {
        return Size;
    }

    public void setSize(String size) {
        this.Size = size;
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

    public iPhoneSearchResultsModel(String Model, String Color, String Size,
                                    String Chip, String Price) {
        this.Model = Model;
        this.Color = Color;
        this.Size = Size;
        this.Chip = Chip;
        this.Price = Price;
    }

}