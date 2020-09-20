package com.omarordaz;

import com.omarordaz.filemanagers.BaseFileManager;
import com.omarordaz.filemanagers.TypeOneFileManager;
import com.omarordaz.filemanagers.TypeTwoFileManager;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static com.omarordaz.util.FileUtils.getJarPath;

public class Main {

    public static String[] parseParams(String[] args) throws URISyntaxException {
        String fn = "", so = "";

        if (args.length < 1) {
            System.out.println("Usage: " + getJarPath(Main.class) + " file_name sort_order");
            System.out.println("\tsort_order could be:\n\tF = sort by first name\n\tL = sort by last name\n\tD = sort by start date");
            System.out.println("\tIf no sort order is specified, data will be sorted by first name.\n");
            System.exit(1); // return error level/error code to OS
        } else {
            fn = args[0];
            if (args.length >= 2) {
                switch (args[1]) {
                    case "F":
                    case "L":
                    case "D":
                        so = args[1];
                        break;
                    default:
                        System.err.println("Invalid sort option, please try one of the options mentioned above.\n");
                        System.exit(2); //// return error level/error code to OS
                }
            } else {
                so = "F"; // default, if not specified
            }
        }
        return new String[]{fn, so};
    }

    public static void main(String[] args) throws URISyntaxException {
        String[] params = parseParams(args);

        // if we reached this point, the parameters were correct
        String fileName = params[0];
        String sortOrder = params[1];

        // check if file exists
        if (!Files.exists(Paths.get(fileName))) {
            System.err.println("File: " + fileName + "does not exists. Please try with an existing file.");
            System.exit(3);
        }

        String[] fileContent = {};
        try {
            fileContent = Files.readAllLines(Paths.get(fileName)).toArray(String[]::new);
        } catch (IOException e) {
            System.err.println("\nUnable to read file: " + fileName);
            System.exit(4);
        }
        System.out.println("File read successful, " + fileContent.length + " lines read.\n");

        BaseFileManager fm = null;
        switch (fileContent[0].trim()) {
            case "1":
                fm = new TypeOneFileManager(fileContent);
                break;
            case "2":
                fm = new TypeTwoFileManager(fileContent);
                break;
            default:
                System.err.println("\nInvalid file header (should be 1 or 2).");
                System.exit(5);
        }
        if (fm.saveEmployees(fileName + ".out", sortOrder)) {
            System.out.println("Output saved to: " + fileName +
                    ".out, with " + fm.getParsedLines() + " records");
        }
    }
}
