package com.omarordaz.filemanagers;

import com.omarordaz.model.Employee;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TypeTwoFileManager extends BaseFileManager {
    public TypeTwoFileManager(String[] fileContent) {
        super(fileContent);
    }


    @Override
    public Employee parseEmployee(String line) {
        String firstName, lastName, addressLine1, addressLine2, city, state, country;
        int zipCode;
        Date startDate;
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        Employee res = null;

        try {
            String[] fields = line.split(",");
            if (fields.length < 9)
                return null;
            firstName = fields[0].trim();
            lastName = fields[1].trim();
            String sdTemp = fields[2].trim();
            startDate = df.parse(sdTemp);
            addressLine1 = fields[3].trim();
            addressLine2 = fields[4].trim();
            city = fields[5].trim();
            state = fields[6].trim();
            country = fields[7].trim();
            zipCode = Integer.parseInt(fields[8].trim());
            res = new Employee(firstName, lastName, startDate, addressLine1, addressLine2, city, state, country, zipCode);
        } catch (ParseException | NumberFormatException ex) {
            res = null;
            // if we get an error parsing date or zip code the line is skipped
        }
        return res;
    }
}
