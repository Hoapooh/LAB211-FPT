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
public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        Validation myVal = new Validation();
        ArrayList<Vehicle> vehicleList = new ArrayList<>();
        Menu myMenu = new Menu();
        int choice;

        do {
            myMenu.menuChoice();
            choice = myVal.inputInt("Input your choice: ", 1, 9);
            switch (choice) {
                case 1:
                    myMenu.addVehicleMenu(vehicleList);
                    break;
                case 2:
                    myMenu.checkExistMenu(vehicleList);
                    break;
                case 3:
                    myMenu.updateVehicleMenu(vehicleList);
                    break;
                case 4:
                    myMenu.deleteVehicleMenu(vehicleList);
                    break;
                case 5:
                    myMenu.searchVehicleMenu(vehicleList);
                    break;
                case 6:
                    myMenu.displayVehicleListMenu(vehicleList);
                    break;
                case 7:
                    myMenu.saveDataToFileMenu(vehicleList);
                    break;
                case 8:
                    myMenu.printListFromFile(vehicleList);
                    break;
                default:
                    System.out.println("THANK YOU AND GOODBYE!!");
                    break;
            }
        } while (choice >= 1 && choice <= 8);
    }
}
