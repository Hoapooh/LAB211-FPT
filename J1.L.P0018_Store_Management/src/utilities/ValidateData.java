/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import entity.ExportReceipt;
import entity.ImportReceipt;
import entity.Product;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author anphu
 */
public class ValidateData {

    public static final Scanner sc = new Scanner(System.in);
    private final SearchData sd = new SearchData();
    private final InputData inD = new InputData();

    public String inputProductID(String msg, ArrayList<Product> list) {
        while (true) {
            System.out.print(msg);
            String inputID = sc.nextLine();

            if (sd.searchProductByID(list, inputID) != null) {
                System.err.println("ID existed!! Please input again");
            } else if (inputID.isEmpty()) {
                System.err.println("Must input a string not empty !!!");
            } else if (!inputID.matches("[P]\\d{4}")) {
                System.err.println("ID must match the following format: Pxyzt (xyzt is number 0 -> 9 without spaces)");
                System.err.println("Please input again");
            } else {
                return inputID;
            }
        }
    }
    
    public String inputProductIDNonList(String msg) {
        while (true) {
            System.out.print(msg);
            String inputID = sc.nextLine();

            if (inputID.isEmpty()) {
                System.err.println("Must input a string not empty !!!");
            } else if (!inputID.matches("[P]\\d{4}")) {
                System.err.println("ID must match the following format: Pxyzt (xyzt is number 0 -> 9 without spaces)");
                System.err.println("Please input again");
            } else {
                return inputID;
            }
        }
    }
    
    public String inputProductIDCheckExist(String msg, ArrayList<Product> list) {
        while (true) {
            System.out.print(msg);
            String inputID = sc.nextLine();

            if (inputID.isEmpty()) {
                System.err.println("Must input a string not empty !!!");
                continue;
            }
            
            if (!inputID.matches("[P]\\d{4}")) {
                System.err.println("ID must match the following format: Pxyzt (xyzt is number 0 -> 9 without spaces)");
                System.err.println("Please input again");
                continue;
            }
            
            boolean check = false;
            for (Product product : list) {
                if (product.getProductID().equalsIgnoreCase(inputID)) {
                    check = true;
                    return inputID;
                }
            }
            
            if (!check) {
                System.err.println("ID not existed!! Please input again");
            }
        }
    }
    
    public String inputIECode(String msg) {
        while (true) {
            System.out.print(msg);
            String inputCode = sc.nextLine();
            
            if (inputCode.isEmpty()) {
                System.out.println("Must input a string not empty !!!");
                continue;
            }
            
            if (!inputCode.matches("[IE]C\\d{7}")) {
                System.err.println("ID must match the following format: IC******* or EC******* (* is number 0 -> 9 without spaces)");
                System.err.println("Please input again");
                continue;
            }
            
            return inputCode;
        }
    }

    public String inputNameUpdate(String msg, Product product) {
        while (true) {
            System.out.print(msg);
            String inputName = sc.nextLine();

            if (inputName.trim().isEmpty()) {
                inputName = product.getNameProduct();
            }

            if (!inputName.matches("^[a-zA-Z\\s]+$")) {
                System.err.println("Must input only alphabet character!!!");
                System.err.println("Please input again");
                continue;
            }
            return inputName.trim();
        }
    }

    public String inputSizeUpdate(String msg, Product product) {
        while (true) {
            try {
                System.out.print(msg);
                String inputSize = sc.nextLine();

                if (inputSize.trim().isEmpty()) {
                    inputSize = product.getSize();
                    return inputSize;
                }

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

    public double inputPriceUpdate(String msg, double min, double max, Product product) {
        while (true) {
            System.out.print(msg);
            String input_raw = sc.nextLine();
            double price;

            if (input_raw.trim().isEmpty()) {
                price = product.getPrice();
                return price;
            }

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

    public int inputQuantityUpdate(String msg, int min, int max, Product product) {
        while (true) {
            System.out.print(msg);
            String input_raw = sc.nextLine();
            int quantity;

            if (input_raw.trim().isEmpty()) {
                quantity = product.getQuantity();
                return quantity;
            }

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

    public LocalDate inputDateUpdate(String msg, String pattern, Product product, String type) {
        System.out.print(msg);
        boolean check = true;
        LocalDate date = null;

        while (check) {
            try {
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern(pattern);
                String inputDate = sc.nextLine();

                if (inputDate.trim().isEmpty()) {
                    if (type.equals("manufactoring")) {
                        date = product.getManufactoringDate();
                        return date;
                    } else if (type.equals("expiration")) {
                        date = product.getExpirationDate();
                        return date;
                    }
                }

                date = LocalDate.parse(inputDate, dtf);
                check = false;
            } catch (DateTimeParseException e) {
                System.err.println("Format date must be " + pattern);
                System.err.println("Please input again");
                check = true;
            }
        }
        return date;
    }

    public int compareManufacturingExpirationDate(LocalDate manufacturingDate, LocalDate expirationDate) {
        int choice = 0;
        System.out.println("Manufactoring date can't be after Expiration date");
        System.out.println("What date you want to fix?");
        System.out.println("1. Manufacturing Date");
        System.out.println("2. Expiration Date");
        choice = inD.inputInt("Your choice: ", 1, 2);
        return choice;
    }
    
    public String createImportID(ArrayList<ImportReceipt> irList) {
        String id;
        if (irList.isEmpty()) {
            return "IC0000001";
        } else {
            //take the previous list index
            int index = irList.size() - 1; 
            //get the previous list index Import Code
            String getCode = irList.get(index).getImportCode(); 
            //parse the Code from the second index to the end to integer
            int upCode = Integer.parseInt(getCode.substring(2)); 
            //format the code
            id = String.format("IC%07d", upCode + 1); 
        }
        return id;
    }
    
    public String createExportCode(ArrayList<ExportReceipt> erList) {
        String id;
        if (erList.isEmpty()) {
            return "EC0000001";
        } else {
            //take the previous list index
            int index = erList.size() - 1; 
            //get the previous list index Export Code
            String getCode = erList.get(index).getExportCode(); 
            //parse the Code from the second index to the end to integer
            int upCode = Integer.parseInt(getCode.substring(2)); 
            //format the code
            id = String.format("EC%07d", upCode + 1); 
        }
        return id;
    }
}
