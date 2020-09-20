package com.omarordaz.filemanagers;

import com.omarordaz.model.Employee;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TypeOneFileManager extends BaseFileManager {


    public TypeOneFileManager(String[] fileContent) {
        super(fileContent);
    }

    @Override
    public Employee parseEmployee(String line) {
        String firstName, lastName, addressLine1, addressLine2, city, state, country;
        int zipCode;
        Date startDate;
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");

        Employee res = null;

        if (line.trim().length() < 75)
            return null; // specified length is 80 but with 75 chars we can have a complete record

        try {
            firstName = line.substring(0, 10).trim();
            lastName = line.substring(10, 17).trim();
            String sdTemp = line.substring(27, 35).trim();
            startDate = df.parse(sdTemp);
            addressLine1 = line.substring(35, 44).trim();
            addressLine2 = line.substring(45, 54).trim();
            city = line.substring(55, 64).trim();
            state = line.substring(65, 67).trim();
            country = line.substring(67, 70).trim();
            zipCode = Integer.parseInt(line.substring(70).trim());
            res = new Employee(firstName, lastName, startDate, addressLine1, addressLine2, city, state, country, zipCode);
        } catch (ParseException | NumberFormatException ex) {
            res = null;
            // if we get an error parsing date or zip code the line is skipped
        }
      return res;
    }
}

