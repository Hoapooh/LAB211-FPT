/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Vehicle;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author anphu
 */
public class Validation {

    public static final Scanner sc = new Scanner(System.in);
    
    //check ID but not check for exited
    public String inputIDNotExist(String msg) {
        while (true) {            
            System.out.print(msg);
            String inputID = sc.nextLine();
            if (inputID.trim().isEmpty()) {
                System.err.println("Must not input empty string!! PLease enter again");
            } else if (!inputID.matches("[V]\\d{3}")) {
                System.err.println("Must input the following format: Vxyz - xyz is the number from 0 --> 9 and no spaces");
                System.err.println("Please enter again");
            } else {
                return inputID.trim();
            }
        }
    }
    //Check for ID input in add Vehicle
    public String inputIDCheck(String msg, ArrayList<Vehicle> list) {
        while (true) {            
            System.out.print(msg);
            String inputID = sc.nextLine();
            if (SearchData.searchVehicleID(list, inputID) != null) {
                System.err.println("ID existed!! Please enter again");
            } else if (inputID.trim().isEmpty()) {
                System.err.println("Must not input empty string!! PLease enter again");
            } else if (!inputID.matches("[V]\\d{3}")) {
                System.err.println("Must input the following format: Vxyz - xyz is the number from 0 --> 9 and no spaces");
                System.err.println("Please enter again");
            } else {
                return inputID.trim();
            }
        }
    }

    //USE TO INPUT STRING AND WILL RETURN ERROR IF STRING IS EMPTY
    public String inputString(String msg) {
        while (true) {
            //PRINT OUT MESSAGE
            System.out.print(msg);
            String input_raw = sc.nextLine();

            if (input_raw.trim().isEmpty()) {
                System.err.println("Must input a string not empty !!!");
                System.err.println("Please enter again!");
                continue;
            }
            return input_raw.trim();
        }
    }

    //USE TO INPUT STRING AND ALLOW BLANK LINE
    public String inputStringUpdate(String msg) {
        //PRINT OUT MESSAGE
        System.out.print(msg);
        String input_raw = sc.nextLine();
        return input_raw.trim();
    }

    //CHECK FOR THE INT NUMBER THAT VALUES FROM MIN TO MAX
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
    
    //USE TO CHECK INT NUMBER FOR UPDATE FUNCTION THAT VALUES FROM MIN TO MAX
    public boolean checkIntUpdate(String check, int min, int max) {
        try {
            int number = Integer.parseInt(check);
            if (number < min  || number > max) {
                throw new NumberFormatException();
            }
            return true;
        } catch (NumberFormatException nfe) {
            System.err.println("You must input number from " + min + " to " + max);
            System.err.println("Please enter again!");
            return false;
        }
    }

    //CHECK FOR THE DOUBLE NUMBER THAT VALUES FROM MIN TO MAX
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

    //CHECK FOR THE DOUBLE NUMBER FOR UPDATE FUNCTION THAT VALUES FROM MIN TO MAX
    public boolean inputDoubleUpdate(String check, double min, double max) {
        try {
            double number = Double.parseDouble(check);
            if (number < min  || number > max) {
                throw new NumberFormatException();
            }
            return true;
        } catch (NumberFormatException nfe) {
            System.err.println("You must input number from " + min + " to " + max);
            System.err.println("Please enter again!");
            return false;
        }
    }

    //CHECK YES NO QUESTION
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
}
