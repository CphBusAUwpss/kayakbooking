package model.domain.entity;

import java.io.InputStream;

/**
 *
 * @author Thomas Hartmann - tha@cphbusiness.dk created on Nov 10, 2016
 */
public class Kayak {

    int id;
    private String name;
    private String model;
    private String description;
    private String color;
    private double length;
    private InputStream image;

    public Kayak() {
    }

    public Kayak(String name, String model, String description, String color, double length, InputStream image) {
        this.name = name;
        this.model = model;
        this.description = description;
        this.color = color;
        this.length = length;
        this.image = image;
    }

    public Kayak(int id, String name, String model, String description, String color, double length, InputStream image) {
        this.id = id;
        this.name = name;
        this.model = model;
        this.description = description;
        this.color = color;
        this.length = length;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public InputStream getImage() {
        return image;
    }

    public void setImage(InputStream image) {
        this.image = image;
    }
    

}
