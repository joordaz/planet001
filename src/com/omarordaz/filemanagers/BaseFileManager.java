package com.omarordaz.filemanagers;

import com.omarordaz.exceptions.InvalidSortOption;
import com.omarordaz.model.Employee;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;

public class BaseFileManager {

    private ArrayList<Employee> employees = new ArrayList<>();
    private String[] fileContent;
    private int parsedLines = 0;

    public BaseFileManager(String[] fileContent) {
        this.fileContent = fileContent;
        parseFile();
    }

    public Employee parseEmployee(String line) {
        throw new UnsupportedOperationException();
    }

    public String[] getFileContent() {
        return fileContent;
    }

    public int getParsedLines() {
        return parsedLines;
    }

    public void setParsedLines(int parsedLines) {
        this.parsedLines = parsedLines;
    }

    private void parseFile() {
        for (String line : getFileContent()) {
            Employee e = parseEmployee(line);
            if (e != null) {
                employees.add(e);
                setParsedLines(getParsedLines() + 1);
            }
        }
    }

    private Employee[] getSortedEmployees(String sortOrder) throws InvalidSortOption {
        Employee[] res;
        switch (sortOrder) {
            case "F":
                res = employees.stream()
                        .sorted(Comparator.comparing(e -> e.firstName))
                        .toArray(Employee[]::new);
                break;
            case "L":
                res = employees.stream()
                        .sorted(Comparator.comparing(e -> e.lastName))
                        .toArray(Employee[]::new);
                break;
            case "D":
                res = employees.stream()
                        .sorted(Comparator.comparing(e -> e.startDate))
                        .toArray(Employee[]::new);
                break;
            default:
                throw new InvalidSortOption("Invalid sort option specified.");
        }
        return res;
    }

    public boolean saveEmployees(String outputFileName, String sortOrder) {
        Employee[] orderedList;
        boolean res = true;

        try {
            orderedList = getSortedEmployees(sortOrder);
        } catch (InvalidSortOption invalidSortOption) {
            System.err.println("Invalid sort option specified.");
            return false;
        }

        try (FileWriter outFile = new FileWriter(outputFileName, false)) {
            for (int e = 0; e < orderedList.length; e++) {
                outFile.write(String.format("%d\n", e + 1));
                outFile.write(orderedList[e].getOutFmt());
            }
        } catch (IOException e) {
            //e.printStackTrace();
            res = false;
            System.err.println("Error: output file could not be written. Please make sure you have write rights on this folder.");
        }
        return res;
    }
}
