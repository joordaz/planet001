package com.omarordaz.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Employee {
    public String firstName;
    public String lastName;
    public Date startDate;
    public String addressLine1;
    public String addressLine2;
    public String city;
    public String state;
    public String country;
    public Integer zipCode;

    public Employee(String firstName, String lastName, Date startDate, String addressLine1, String addressLine2, String city, String state, String country, Integer zipCode) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.startDate = startDate;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.city = city;
        this.state = state;
        this.country = country;
        this.zipCode = zipCode;
    }

    // returns string with employee data in requested format
    public String getOutFmt() {
        SimpleDateFormat edf = new SimpleDateFormat("MMMM dd, yyyy");
        return String.format(" %s %s, (%s)\n", this.firstName, this.lastName, edf.format(this.startDate)) +
                String.format(" %s, %s\n", this.addressLine1, this.addressLine2) +
                String.format(" %s, %s\n", this.city, this.state) +
                String.format(" %s, %s\n", this.country, this.zipCode);
    }
}
