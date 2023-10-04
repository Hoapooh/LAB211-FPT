/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Vehicle;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 *
 * @author anphu
 */
public class VehicleManager {

    public static final Scanner sc = new Scanner(System.in);
    Validation myVal = new Validation();
    ArrayList<Vehicle> vehicleList = new ArrayList<>();

    public VehicleManager() {
    }

    public ArrayList<Vehicle> getVehicleList() {
        return vehicleList;
    }

    public void setVehicleList(ArrayList<Vehicle> vehicleList) {
        this.vehicleList = vehicleList;
    }

    public void insertBarrier() {
        for (int i = 0; i < 93; i++) {
            System.out.print("#");
        }
        System.out.println();
    }

    //FUNCTION 0 : ADD NEW VEHICLE
    public void addVehicle(ArrayList<Vehicle> list) {
        String id = myVal.inputIDCheck("Input ID of vehicle: ", list);
        String name = myVal.inputString("Input name of vehicle: ");
        String color = myVal.inputString("Input color of vehicle: ");
        double price = myVal.inputDouble("Input price of vehicle: ", 0, Double.MAX_VALUE);
        String brand = myVal.inputString("Input brand of vehicle: ");
        String type = myVal.inputString("Input type of vehicle: ");
        int productYear = myVal.inputInt("Input the year published of vehicle: ", 1800, 2023);

        Vehicle myV = new Vehicle(id, name, color, price, brand, type, productYear);
        list.add(myV);
        System.out.println("VEHICLE HAS BEEN ADD SUCCESSFULLY INTO LIST!!");
    }

    //FUNCTION 1: CHECK TO EXIST VEHICLE
    public void checkVehicleExist(String id_vehicle, ArrayList<Vehicle> list) {
        ArrayList<Vehicle> checkList = new ArrayList<>();
        try {
            File file = new File("Vehicle.dat");
            if (!file.exists()) {
                return;
            }

            BufferedReader bfr = new BufferedReader(new FileReader(file));

            String line;
            while ((line = bfr.readLine()) != null) {
                StringTokenizer stk = new StringTokenizer(line, "\\|");

                String id = stk.nextToken().trim();
                String name = stk.nextToken().trim();
                String color = stk.nextToken().trim();
                double price = Double.parseDouble(stk.nextToken().trim());
                String brand = stk.nextToken().trim();
                String type = stk.nextToken().trim();
                int year = Integer.parseInt(stk.nextToken().trim());

                Vehicle myVI = new Vehicle(id, name, color, price, brand, type, year);
                checkList.add(myVI);

            }
            bfr.close();
        } catch (IOException ioe) {
            System.out.println("CAN NOT FIND THE FILE YOU REQUIRED");
        }
        for (Vehicle vi : checkList) {
            if (vi.getID_Vehicle().equalsIgnoreCase(id_vehicle)) {
                System.out.println("EXIST VEHICLE!!");
                return;
            }
        }
        System.out.println("NO VEHICLE FOUND!!");
    }

    //FUNCTION 2: UPDATE VEHICLE
    public void updateVehicle(ArrayList<Vehicle> list) {
        String id_vehicle = myVal.inputIDNotExist("Input the ID of vehicle you want to update: ");
        Vehicle ve = SearchData.searchVehicleID(list, id_vehicle);
        if (ve != null) {
            for (Vehicle v : list) {
                if (v.getID_Vehicle().equalsIgnoreCase(id_vehicle)) {

                    String name_vh = myVal.inputStringUpdate("Input new name of vehicle: ");
                    if (!name_vh.trim().isEmpty()) {
                        v.setName_Vehicle(name_vh);
                    }

                    String color_vh = myVal.inputStringUpdate("Input new color of vehicle: ");
                    if (!color_vh.trim().isEmpty()) {
                        v.setColor_Vehicle(color_vh);
                    }

                    String price_vh = myVal.inputStringUpdate("Input new price of vehicle: ");
                    if (!price_vh.trim().isEmpty()) {
                        while (!myVal.inputDoubleUpdate(price_vh, 0, 1e9)) {
                            price_vh = myVal.inputStringUpdate("Input new price of vehicle: ");
                        }
                        v.setPrice_Vehicle((double) Double.parseDouble(price_vh));
                    }

                    String brand_vh = myVal.inputStringUpdate("Input new brand of vehicle: ");
                    if (!brand_vh.trim().isEmpty()) {
                        v.setBrand_Vehicle(brand_vh);
                    }

                    String type = myVal.inputStringUpdate("Input new type of vehicle: ");
                    if (!type.trim().isEmpty()) {
                        v.setType(type);
                    }

                    String year = myVal.inputStringUpdate("Input the new year published of vehicle: ");
                    if (!year.trim().isEmpty()) {
                        while (!myVal.checkIntUpdate(year, 1800, 2023)) {
                            year = myVal.inputStringUpdate("Input new year published of vehicle: ");
                        }
                        v.setProductYear((int) (Integer.parseInt(year)));
                    }

                    System.out.println("VEHICLE HAS BEEN UPDATED SUCCESSFULLY");
                    return;
                }
            }
        } else {
            System.out.println("VEHICLE DOES NOT EXIST!!");
        }
    }

    //FUNCTION 3: DELETE VEHICLE
    public void deleteVehicle(String id_vehicle, ArrayList<Vehicle> list) {
        for (Vehicle vehicleInformation : list) {
            if (vehicleInformation.getID_Vehicle().equalsIgnoreCase(id_vehicle)) {
                boolean check = myVal.inputYesNo("DO YOU WANT TO DELETE THIS VEHICLE(Y/N)? ");
                if (!check) {
                    System.out.println("NOTHING TO DELETE");
                    return;
                } else if (check) {
                    vehicleList.remove(vehicleInformation);
                    System.out.println("VEHICLE HAS BEEN REMOVED SUCCESSFULLY");
                    return;
                }
            }
        }
        System.out.println("VEHICLE NOT FOUND!!");
    }

    //FUNCTION 4: SEARCH VEHICLE
    public void searchByName(String name, ArrayList<Vehicle> list) {
        int count = 0;
        for (Vehicle v : list) {
            if (!v.getName_Vehicle().equalsIgnoreCase(name)) {
                count = 1;
            } else {
                System.out.println(v);
            }
        }
        if (count == 0) {
            System.out.println("VEHICLE NOT FOUND!!");
        }
    }

    public void searchByID(String id, ArrayList<Vehicle> list) {
        for (Vehicle vehicleInformation : list) {
            if (vehicleInformation.getID_Vehicle().equalsIgnoreCase(id)) {
                System.out.println(vehicleInformation);
                return;
            }
        }
        System.out.println("VEHICLE NOT FOUND!!");
    }

    //FUNCTION 5: DISPLAY VEHICLE LIST
    public static Comparator<Vehicle> compPrice = new Comparator() {
        @Override
        public int compare(Object o1, Object o2) {
            Vehicle s1 = (Vehicle) o1;
            Vehicle s2 = (Vehicle) o2;

            if (s1.getPrice_Vehicle() < s2.getPrice_Vehicle()) {
                return 1;
            } else if (s1.getPrice_Vehicle() == (s2.getPrice_Vehicle())) {
                return 0;
            } else {
                return -1;
            }
        }
    };

    public void displayVehicleList(ArrayList<Vehicle> list) {
        System.out.println("**                                  =NORMAL VEHICLE LIST=                                  **");
        insertBarrier();
        System.out.println(String.format("#%-4s #%-10s #%-11s #%-11s #%-14s #%-14s #%-14s #",
                "VID", "Name", "Color", "Price", "Brand", "Type", "Product Year"));
        insertBarrier();
        for (Vehicle v : list) {
            System.out.println(v);
        }
        insertBarrier();
    }

    public void displayVehicleListPriceDesc(ArrayList<Vehicle> list) {
        ArrayList<Vehicle> myNewList = new ArrayList<>();
        for (Vehicle vehicle : list) {
            myNewList.add(vehicle);
        }
        Collections.sort(myNewList, compPrice);
        System.out.println("**                                   =DESC VEHICLE LIST=                                   **");
        insertBarrier();
        System.out.println(String.format("#%-4s #%-10s #%-11s #%-11s #%-14s #%-14s #%-14s #",
                "VID", "Name", "Color", "Price", "Brand", "Type", "Product Year"));
        insertBarrier();
        for (Vehicle v : myNewList) {
            System.out.println(v);
        }
        insertBarrier();
    }

    //FUNCTION 6: SAVE DATA TO FILE
    public void saveDataToFile(ArrayList<Vehicle> list) {
        if (list.isEmpty()) {
            System.err.println("THE DATA IS EMPTY");
            return;
        }

        try {
            File f = new File("Vehicle.dat");
            PrintWriter pw = new PrintWriter(new FileWriter(f));
            for (Vehicle vi : list) {
                pw.println(String.format("%-4s |%-10s |%-8s |%-12.2f |%-10s |%-10s |%-10d",
                        vi.getID_Vehicle(), vi.getName_Vehicle(), vi.getColor_Vehicle(),
                        vi.getPrice_Vehicle(), vi.getBrand_Vehicle(), vi.getType(), vi.getProductYear()));
            };
            pw.close();
            System.out.println("DATA HAS BEEN SAVE SUCCESSFULLY");
        } catch (IOException ioe) {
            System.out.println("CAN NOT SAVE FILE");
        }
    }

    //FUNCTION 7: PRINT VEHICLE LIST
    public void readFromFile(ArrayList<Vehicle> vehicleList) {
        try {
            File file = new File("Vehicle.dat");
            if (!file.exists()) {
                return;
            }

            BufferedReader bfr = new BufferedReader(new FileReader(file));

            String line;
            while ((line = bfr.readLine()) != null) {
                boolean check = false;
                StringTokenizer stk = new StringTokenizer(line, "\\|");

                String id = stk.nextToken().trim();

                if (!vehicleList.isEmpty()) {
                    for (Vehicle vehicle : vehicleList) {
                        if (vehicle.getID_Vehicle().equalsIgnoreCase(id)) {
                            check = true;
                            break;
                        }
                    }
                }

                if (!check) {
                    String name = stk.nextToken().trim();
                    String color = stk.nextToken().trim();
                    double price = Double.parseDouble(stk.nextToken().trim());
                    String brand = stk.nextToken().trim();
                    String type = stk.nextToken().trim();
                    int year = Integer.parseInt(stk.nextToken().trim());

                    Vehicle myVI = new Vehicle(id, name, color, price, brand, type, year);
                    vehicleList.add(myVI);
                } else {
                    continue;
                }
            }
            bfr.close();
        } catch (IOException ioe) {
            System.out.println("CAN NOT FIND THE FILE YOU REQUIRED");
        }
        for (Vehicle vehicle : vehicleList) {
            System.out.println(vehicle);
        }
        System.out.println("LIST PRINTED SUCCESSFULLY!!");
    }

    public void readFromFileDescendingly() {
        ArrayList<Vehicle> checkList = new ArrayList<>();
        try {
            File file = new File("Vehicle.dat");
            if (!file.exists()) {
                System.out.println("CAN NOT FIND THE FILE YOU REQUIRED");
                return;
            }

            BufferedReader bfr = new BufferedReader(new FileReader(file));

            String line;
            while ((line = bfr.readLine()) != null) {
                boolean check = false;
                StringTokenizer stk = new StringTokenizer(line, "\\|");

                String id = stk.nextToken().trim();
                if (!vehicleList.isEmpty()) {
                    for (Vehicle vehicle : vehicleList) {
                        if (vehicle.getID_Vehicle().equalsIgnoreCase(id)) {
                            check = true;
                            break;
                        }
                    }
                }

                if (!check) {
                    String name = stk.nextToken().trim();
                    String color = stk.nextToken().trim();
                    double price = Double.parseDouble(stk.nextToken().trim());
                    String brand = stk.nextToken().trim();
                    String type = stk.nextToken().trim();
                    int year = Integer.parseInt(stk.nextToken().trim());

                    Vehicle myVI = new Vehicle(id, name, color, price, brand, type, year);
                    checkList.add(myVI);
                } else {
                    continue;
                }
            }
            bfr.close();
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
        Collections.sort(checkList, compPrice);
        for (Vehicle vi : checkList) {
            System.out.println(vi);
        }
        System.out.println("LIST PRINTED SUCCESSFULLY!!");
    }
}
