/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.ExportReceipt;
import entity.ImportReceipt;
import entity.Product;
import interfaceService.IProductManager;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import utilities.InputData;
import utilities.SearchData;
import utilities.ValidateData;

/**
 *
 * @author anphu
 */
public class ProductManager implements IProductManager {

    public static final Scanner sc = new Scanner(System.in);
    private final InputData ind;
    private final SearchData sd;
    private final ValidateData vd;

    public ProductManager() {
        ind = new InputData();
        sd = new SearchData();
        vd = new ValidateData();
    }

    public void insertBarrier() {
        for (int i = 0; i < 123; i++) {
            System.out.print("#");
        }
        System.out.println("");
    }

    @Override
    public void addProduct(ArrayList<Product> proList) {
        boolean choice = true;

        LocalDate manufactoringDate;
        LocalDate expirationDate;
        while (choice) {
            boolean type = ind.inputType("Input type of product you want to add? (Daily/Long) ");
            String id = vd.inputProductID("Input Product ID: ", proList).trim();
            String name = ind.inputString("Input name Product: ").trim();
            String size = ind.inputSize("Input size of Product: ").trim();
            if (type) {
                manufactoringDate = LocalDate.now();
                expirationDate = LocalDate.now();
            } else {
                System.out.println("NOTICE!!! It will automatically transfer to 29-02 or 28-02 base on that year (leap or not leap) when you input out of range in February");
                manufactoringDate = ind.inputDate("Input manufactoring date following this format (dd-MM-yyyy): ", "dd-MM-yyyy");
                expirationDate = ind.inputDate("Input expiration date following this format (dd-MM-yyyy): ", "dd-MM-yyyy");
                while (manufactoringDate.isAfter(expirationDate)) {
                    if (vd.compareManufacturingExpirationDate(manufactoringDate, expirationDate) == 1) {
                        manufactoringDate = ind.inputDate("Input manufactoring date following this format (dd-MM-yyyy): ", "dd-MM-yyyy");
                    } else {
                        expirationDate = ind.inputDate("Input expiration date following this format (dd-MM-yyyy): ", "dd-MM-yyyy");
                    }
                }
            }
            double price = ind.inputDouble("Input price of product: ", 0, Double.MAX_VALUE);
            int quantity = ind.inputInt("Input quantity of product: ", 0, Integer.MAX_VALUE);
            proList.add(new Product(id, name, size, manufactoringDate, expirationDate, price, quantity));
            insertBarrier();
            System.out.println("#                                      PRODUCT HAS BEEN ADDED TO LIST SUCCESSFULLY!!                                      #");
            insertBarrier();
            choice = ind.inputYesNo("Do you want to continue adding Product?(Y/N): ");
        }
    }

    @Override
    public void updateProduct(ArrayList<Product> proList) {
        String id = vd.inputProductIDNonList("Input product ID you want to update: ");
        Product product = sd.searchProductByID(proList, id);
        LocalDate manufactorDate;
        LocalDate expiredDate;

        if (product != null) {
            System.out.println("PRODUCT FOUND!! HERE IS THE PRODUCT'S INFORMATION");
            insertBarrier();
            System.out.printf("#%-11s# %-18s# %-11s# %-20s# %-20s# %-18s# %-11s#\n", "ID", "NAME", "SIZE", "MANUFACTURING DATE",
                    "EXPIRATION DATE", "PRICE", "QUANTITY");
            insertBarrier();
            System.out.println(product);
            insertBarrier();
            boolean type = ind.inputType("Input type of product you want to update? (Daily/Long) ");
            String name = vd.inputNameUpdate("Input name of product: ", product).trim();
            String size = vd.inputSizeUpdate("Input size of product: ", product).trim();
            if (type) {
                manufactorDate = LocalDate.now();
                expiredDate = LocalDate.now();
            } else {
                manufactorDate = vd.inputDateUpdate("Input manufactoring date of product: ", "dd-MM-yyyy", product, "manufactoring");
                expiredDate = vd.inputDateUpdate("Input expiration date of product: ", "dd-MM-yyyy", product, "expiration");
            }
            double price = vd.inputPriceUpdate("Input price of product: ", 0, Double.MAX_VALUE, product);
            int quantity = vd.inputQuantityUpdate("Input quantity of product: ", 0, Integer.MAX_VALUE, product);

            product.setNameProduct(name);
            product.setSize(size);
            product.setManufactoringDate(manufactorDate);
            product.setExpirationDate(expiredDate);
            product.setPrice(price);
            product.setQuantity(quantity);
            System.out.println(product);

            insertBarrier();
            System.out.println("#                                         PRODUCT HAS BEEN UPDATED SUCCESSFULLY!!                                         #");
            insertBarrier();
        } else {
            System.out.println("PRODUCT NOT FOUND!!");
        }
    }

    @Override
    public void deleteProduct(ArrayList<Product> proList, ArrayList<ImportReceipt> iList, ArrayList<ExportReceipt> eList) {
        String id = vd.inputProductIDNonList("Input product ID you want to delete: ").trim();
        Product product = sd.searchProductByID(proList, id);

        //kiểm tra product code có tồn tại trong import hay export hay không
        for (ImportReceipt impReceipt : iList) {
            for (Product product1 : impReceipt.getProList()) {
                if (product1.getProductID().equalsIgnoreCase(id)) {
                    System.out.println("PRODUCT FOUND IN IMPORT RECEIPT " + impReceipt.getImportCode() + "!!! CAN NOT DELETE THIS PRODUCT");
                    System.out.println(impReceipt.importExportInformation());
                    return;
                }
            }
        }

        for (ExportReceipt expReceipt : eList) {
            for (Product product1 : expReceipt.getProList()) {
                if (product1.getProductID().equalsIgnoreCase(id)) {
                    System.out.println("PRODUCT FOUND IN EXPORT RECEIPT " + expReceipt.getExportCode() + "!!! CAN NOT DELETE THIS PRODUCT");
                    System.out.println(expReceipt.importExportInformation());
                    return;
                }
            }
        }

        if (product != null) {
            System.out.println("PRODUCT FOUND!! HERE IS THE PRODUCT'S INFORMATION");
            insertBarrier();
            System.out.printf("#%-11s# %-18s# %-11s# %-20s# %-20s# %-18s# %-11s#\n", "ID", "NAME", "SIZE", "MANUFACTURING DATE",
                    "EXPIRATION DATE", "PRICE", "QUANTITY");
            insertBarrier();
            System.out.println(product);
            insertBarrier();
            boolean check = ind.inputYesNo("Do you want to delete this product?(Y/N) ");
            if (check) {
                proList.remove(product);
                insertBarrier();
                System.out.println("#                                         PRODUCT HAS BEEN DELETED SUCCESSFULLY!!                                         #");
                insertBarrier();
            } else {
                System.out.println("Program continue...");
            }
        } else {
            System.out.println("PRODUCT NOT FOUND!!");
        }
    }

    @Override
    public void showAllProduct(ArrayList<Product> proList) {
        insertBarrier();
        System.out.printf("#%-11s# %-18s# %-11s# %-20s# %-20s# %-18s# %-11s#\n", "ID", "NAME", "SIZE", "MANUFACTURING DATE",
                "EXPIRATION DATE", "PRICE", "QUANTITY");
        insertBarrier();
        for (Product product : proList) {
            System.out.println(product);
        }
        insertBarrier();
    }
}
