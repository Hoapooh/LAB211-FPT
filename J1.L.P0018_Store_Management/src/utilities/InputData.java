/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 *
 * @author anphu
 */
public class InputData {

    public static final Scanner sc = new Scanner(System.in);

    public String inputString(String msg) {
        while (true) {
            //PRINT OUT MESSAGE
            System.out.print(msg);
            String input_raw = sc.nextLine();

            if (input_raw.trim().isEmpty()) {
                System.err.println("Must input a string not empty !!!");
                System.err.println("Please input again!");
                continue;
            }
            
            if (!input_raw.matches("^[a-zA-Z\\s]+$")) {
                System.err.println("Must input only alphabet character!!!");
                System.err.println("Please input again");
                continue;
            }
            return input_raw.trim();
        }
    }
    
    public boolean inputType(String msg) {
        while (true) {            
            System.out.print(msg);
            String type = sc.nextLine();
            if (type.equalsIgnoreCase("daily")) {
                return true;
            } else if (type.equalsIgnoreCase("long")) {
                return false;
            } else {
                System.err.println("Must input Daily or Long");
                continue;
            }
        }
    }

    public int inputInt(String msg, int min, int max) {
        while (true) {
            //PRINT OUT MESSAGE
            System.out.print(msg);
            String input_raw = sc.nextLine();

            try {
                int input = Integer.parseInt(input_raw);

                if (input < min || input > max) {
                    throw new NumberFormatException();
                }
                return input;
            } catch (NumberFormatException nfe) {
                System.err.println("You must input number from " + min + " to " + max);
                System.err.println("Please enter again!");
                continue;
            }
        }
    }

    public double inputDouble(String msg, double min, double max) {
        while (true) {
            System.out.print(msg);
            String input_raw = sc.nextLine();

            try {
                double input = Double.parseDouble(input_raw);

                if (input < min || input > max) {
                    throw new NumberFormatException();
                }
                return input;
            } catch (NumberFormatException nfe) {
                System.err.println("You must input number from " + min + " to " + max);
                System.err.println("Please enter again!");
                continue;
            }
        }
    }

    public String inputSize(String msg) {
        while (true) {
            try {
                System.out.print(msg);
                String inputSize = sc.nextLine();
                if (!inputSize.matches("^[S|s|M|m|L|l]$")) {
                    throw new Exception();
                }
                return inputSize.toUpperCase();
            } catch (Exception e) {
                System.err.println("You must input 1 of 3 size (S or M or L)");
                System.err.println("Please input again");
                continue;
            }
        }
    }

    public boolean inputYesNo(String msg) {
        String choice;
        while (true) {
            System.out.print(msg);
            choice = sc.nextLine();
            if (choice.equalsIgnoreCase("Y")) {
                return true;
            } else if (choice.equalsIgnoreCase("N")) {
                return false;
            } else {
                System.err.println("Must be Y or N");
                System.err.println("Please input again");
                continue;
            }
        }
    }

    public LocalDate inputDate(String msg, String pattern) {
        System.out.print(msg);
        boolean check = true;
        LocalDate date = null;
        while (check) {
            try {
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern(pattern);
                date = LocalDate.parse(sc.nextLine(), dtf);
                check = false;
            } catch (DateTimeParseException e) {
                System.err.println("Format date must be " + pattern);
                System.err.println("Please input again");
                check = true;
            } catch (DateTimeException e) {
                System.err.println("Invalid Date!!! Please input again");
                check = true;
            }
        }
        return date;
    }
}
