package com.sunbeaminfo.application6.model;

import androidx.annotation.NonNull;

public class Contact {
    String name;
    String address;
    String email;
    String phone;

    public Contact(String name, String address, String email, String phone) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    @NonNull
    @Override
    public String toString() {
        return "Contact[name: " + this.name + ", address: " + this.address + ", email: " + this.email + ", phone: " + this.phone + "]";
    }
}
