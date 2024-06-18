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
import service.ProductManager;
import service.ReportManager;
import service.StoreProductToFiles;
import service.StoreWarehouseToFiles;
import service.WarehouseManager;
import utilities.InputData;

/**
 *
 * @author anphu
 */
public class Menu {

    ProductManager pm = new ProductManager();
    WarehouseManager wm = new WarehouseManager();
    ReportManager rm = new ReportManager();
    StoreProductToFiles sptf = new StoreProductToFiles();
    StoreWarehouseToFiles swtf = new StoreWarehouseToFiles();
    InputData ind = new InputData();

    public void mainMenu() {
        System.out.println("<-----------------STORE MANAGEMENT----------------->");
        System.out.println("| 1. Manage products                               |");
        System.out.println("| 2. Manage warehouse                              |");
        System.out.println("| 3. Report                                        |");
        System.out.println("| 4. Store data to files                           |");
        System.out.println("| 5. Exit                                          |");
        System.out.println("| THERE IS NOTHING HEREEEEEE                       |");
        System.out.println("<-------------------------------------------------->");
    }

    public void productMenu(ArrayList<Product> proList, ArrayList<ImportReceipt> iList, ArrayList<ExportReceipt> eList) {
        int choice = 0;
        do {
            System.out.println("<-------------------------------------------------->");
            System.out.println("| 1. Manage products                               |");
            System.out.println("|    1.1 Add a product                             |");
            System.out.println("|    1.2 Update product information                |");
            System.out.println("|    1.3 Delete product                            |");
            System.out.println("|    1.4 Show all product                          |");
            System.out.println("|    1.5 Back to main menu                         |");
            System.out.println("<-------------------------------------------------->");
            choice = ind.inputInt("Your choice...", 1, 5);
            switch (choice) {
                case 1:
                    pm.addProduct(proList);
                    break;
                case 2:
                    pm.updateProduct(proList);
                    break;
                case 3:
                    pm.deleteProduct(proList, iList, eList);
                    break;
                case 4:
                    pm.showAllProduct(proList);
                    break;
                default:
                    System.out.println("GOODBYE!!!");
                    break;
            }
        } while (choice >= 1 && choice <= 4);
    }

    public void warehouseMenu(ArrayList<Product> proList, ArrayList<ImportReceipt> iList, ArrayList<ExportReceipt> eList) {
        int choice = 0;
        do {
            System.out.println("<-------------------------------------------------->");
            System.out.println("| 2. Manage warehouse                              |");
            System.out.println("|    2.1 Create an import receipt                  |");
            System.out.println("|    2.2 Create an export receipt                  |");
            System.out.println("|    2.3 Back to main menu                         |");
            System.out.println("<-------------------------------------------------->");
            choice = ind.inputInt("Your choice...", 1, 3);
            switch (choice) {
                case 1:
                    wm.createImportReceipt(proList, iList);
                    break;
                case 2:
                    wm.createExportReceipt(proList, eList);
                    break;
                default:
                    System.out.println("GOODBYE!!!");
                    break;
            }
        } while (choice >= 1 && choice <= 2);
    }

    public void reportMenu(ArrayList<Product> proList, ArrayList<ImportReceipt> iList, ArrayList<ExportReceipt> eList) {
        int choice = 0;
        do {
            System.out.println("<-------------------------------------------------->");
            System.out.println("| 3. Report                                        |");
            System.out.println("|    3.1 Products that have expired                |");
            System.out.println("|    3.2 Products selling                          |");
            System.out.println("|    3.3 Products running out of stock             |");
            System.out.println("|    3.4 Import/Export receipt of a product        |");
            System.out.println("|    3.5 Back to main menu                         |");
            System.out.println("<-------------------------------------------------->");
            choice = ind.inputInt("Your choice...", 1, 5);
            switch (choice) {
                case 1:
                    rm.expiredProduct(proList);
                    break;
                case 2:
                    rm.sellingProduct(proList);
                    break;
                case 3:
                    rm.runningOutOfStockProduct(proList);
                    break;
                case 4:
                    int choice1 = 0;
                    do {
                        System.out.println("<-------------------------------------------------->");
                        System.out.println("| 3.4 Import/Export receipt of a product           |");
                        System.out.println("|     3.4.1 Show import receipt                    |");
                        System.out.println("|     3.4.2 Show export receipt                    |");
                        System.out.println("|     3.4.3 Search import/export receipt           |");
                        System.out.println("|     3.4.4 Back to submenu                        |");
                        System.out.println("<-------------------------------------------------->");
                        choice1 = ind.inputInt("Your choice...", 1, 4);
                        switch (choice1) {
                            case 1:
                                rm.printImportReceiptProduct(iList);
                                break;
                            case 2:
                                rm.printExportReceiptProduct(eList);
                                break;
                            case 3:
                                rm.searchImportExportID(iList, eList);
                                break;
                            case 4:
                                System.out.println("GOODBYE!!!");
                        }
                    } while (choice1 >= 1 && choice1 <= 3);
                    break;
                default:
                    System.out.println("GOODBYE!!!");
                    break;
            }
        } while (choice >= 1 && choice <= 4);
    }

    public void storeDataMenu(ArrayList<Product> proList, ArrayList<ImportReceipt> iList, ArrayList<ExportReceipt> eList) {
        int choice = 0;
        do {
            System.out.println("<-------------------------------------------------->");
            System.out.println("| 4. Store data to files                           |");
            System.out.println("|    4.1 Store Product to files                    |");
            System.out.println("|    4.2 Store receipts to files                   |");
            System.out.println("|    4.3 Back to main menu                         |");
            System.out.println("<-------------------------------------------------->");
            choice = ind.inputInt("Your choice...", 1, 3);
            switch (choice) {
                case 1:
                    sptf.saveProductToFile("product.dat", proList);
                    break;
                case 2:
                    swtf.saveDataToFile("warehouse.dat", iList, eList);
                    break;

                default:
                    System.out.println("GOODBYE!!!");
                    break;
            }
        } while (choice >= 1 && choice <= 2);
    }
}
