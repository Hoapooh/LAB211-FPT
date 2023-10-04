/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.Validation;
import java.util.Scanner;
import Controller.VehicleManager;
import Model.Vehicle;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 *
 * @author anphu
 */
public class Menu {

    Scanner sc = new Scanner(System.in);
    Validation myVal = new Validation();
    VehicleManager myVM = new VehicleManager();

    public void menuChoice() {
        System.out.println("#****************VEHICLE MANAGEMENT****************#");
        System.out.println("#  1. Add a new vehicle                            #");
        System.out.println("#  2. Check exist Vehicle                          #");
        System.out.println("#  3. Update vehicle                               #");
        System.out.println("#  4. Delete vehicle                               #");
        System.out.println("#  5. Search for vehicle                           #");
        System.out.println("#  6. Display vehicle list                         #");
        System.out.println("#  7. Saving vehicle to file                       #");
        System.out.println("#  8. Print vehicle list                           #");
        System.out.println("#  9. Quit program                                 #");
        System.out.println("#**************************************************#");
    }

    public void addVehicleMenu(ArrayList<Vehicle> list) {
        boolean check = true;
        while (check) {
            myVM.addVehicle(list);
            check = myVal.inputYesNo("Do you want to continue adding new Vehicle?(Y/N) ");
        }
    }

    public void checkExistMenu(ArrayList<Vehicle> list) {
        String id_vh;
        boolean check = true;
        while (check) {
            id_vh = myVal.inputIDNotExist("Input ID of vehicle you want to check: ");
            myVM.checkVehicleExist(id_vh, list);
            check = myVal.inputYesNo("Do you want to continue checking Vehicle?(Y/N) ");
        }
    }

    public void updateVehicleMenu(ArrayList<Vehicle> list) {
        boolean check = true;
        while (check) {
            myVM.updateVehicle(list);
            check = myVal.inputYesNo("Do you want to continue updating Vehicle?(Y/N) ");
        }
    }

    public void deleteVehicleMenu(ArrayList<Vehicle> list) {
        String id_vehicle = myVal.inputIDNotExist("Input ID of vehicle you want to delete: ");
        myVM.deleteVehicle(id_vehicle, list);
    }

    public void searchVehicleMenu(ArrayList<Vehicle> list) {
        int option = 0;
        do {
            System.out.println("<----------------Search vehicle---------------->");
            System.out.println("| 5.1 Search by Name_vehicle                   |");
            System.out.println("| 5.2 Search by id_vehicle                     |");
            System.out.println("| 5.3 Back to main menu                        |");
            System.out.println("<---------------------------------------------->");
            option = myVal.inputInt("Your choice...", 1, 3);
            switch (option) {
                case 1:
                    System.out.print("Input name of vehicle: ");
                    String name = sc.nextLine();
                    myVM.searchByName(name, list);
                    break;
                case 2:
                    String id = myVal.inputIDNotExist("Input ID of vehicle: ");
                    myVM.searchByID(id, list);
                    break;
                default:
                    System.out.println("GOODBYE!!");
                    break;
            }
        } while (option >= 1 && option <= 2);
    }

    public void displayVehicleListMenu(ArrayList<Vehicle> list) {
        int option = 0;
        do {
            System.out.println("<---------------Display vehicle list--------------->");
            System.out.println("| 6.1 Show all                                    |");
            System.out.println("| 6.2 Show all (descending by price_vehicle)      |");
            System.out.println("| 6.3 Back to main menu                           |");
            System.out.println("<-------------------------------------------------->");
            option = myVal.inputInt("Your choice...", 1, 3);
            switch (option) {
                case 1:
                    myVM.displayVehicleList(list);
                    break;
                case 2:
                    myVM.displayVehicleListPriceDesc(list);
                    break;
                default:
                    System.out.println("GOODBYE!!");
                    break;
            }
        }while(option >= 1 && option <= 2);
    }

    public void saveDataToFileMenu(ArrayList<Vehicle> list) {
        myVM.saveDataToFile(list);
    }

    public void printListFromFile(ArrayList<Vehicle> list) {
        int option = 0;
        do {
            System.out.println("<---------------Print list from file--------------->");
            System.out.println("| 8.1: Print all                                   |");
            System.out.println("| 8.2: Print all (descending by price_vehicle)     |");
            System.out.println("| 8.3: Back to main menu                           |");
            System.out.println("<-------------------------------------------------->");
            System.out.print("Input your choice: ");
            option = myVal.inputInt("Your choice...", 1, 3);
            switch (option) {
                case 1:
                    myVM.readFromFile(list);
                    break;
                case 2:
                    myVM.readFromFileDescendingly();
                    break;
                default:
                    System.out.println("GOODBYE!!");
                    break;
            }

        } while (option >= 1 && option <= 2);
    }
}
