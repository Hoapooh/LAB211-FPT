/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import entity.ExportReceipt;
import entity.ImportReceipt;
import entity.Product;
import java.util.ArrayList;
import service.StoreProductToFiles;
import service.StoreWarehouseToFiles;
import utilities.InputData;


/** 
 *
 * @author anphu
 */
public class Main {

    static Menu mu = new Menu();
    static InputData ind = new InputData();
    static ArrayList<Product> proList = new ArrayList<>();
    static ArrayList<ImportReceipt> iList = new ArrayList<>();
    static ArrayList<ExportReceipt> eList = new ArrayList<>();
    static StoreProductToFiles sptf = new StoreProductToFiles();
    static StoreWarehouseToFiles swtf = new StoreWarehouseToFiles();
    
    public static void main(String[] args) {
        sptf.loadProductFromFile("product.dat", proList);
        swtf.loadDataFromFile("warehouse.dat", iList, eList);
        int choice;
        do {
            mu.mainMenu();
            choice = ind.inputInt("Input your choice...", 1, 5);
            switch(choice) {
                case 1:
                    mu.productMenu(proList, iList, eList);
                    break;
                case 2:
                    mu.warehouseMenu(proList, iList, eList);
                    break;
                case 3:
                    mu.reportMenu(proList, iList, eList);
                    break;
                case 4:
                    mu.storeDataMenu(proList, iList, eList);
                    break;
                default:
                    System.out.println("End of program!! Thank you for using...GOODBYE!!");
                    break;
            }
        }while(choice >= 1 && choice <= 4);
    }
}
