package com.sunbeaminfo.application4.model;

public class Car {
    int id;
    String model;
    String company;

    @Override
    public String toString() {
        return "Car {" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", company='" + company + '\'' +
                '}';
    }

    public Car() {
    }

    public Car(int id, String model, String company) {
        this.id = id;
        this.model = model;
        this.company = company;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}
