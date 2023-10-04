/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.ExportReceipt;
import entity.ImportReceipt;
import entity.Product;
import interfaceService.IReportManager;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import utilities.InputData;
import utilities.SearchData;
import utilities.ValidateData;

/**
 *
 * @author anphu
 */
public class ReportManager implements IReportManager {

    SearchData sd = new SearchData();
    InputData ind = new InputData();
    ValidateData vd = new ValidateData();

    public ReportManager() {
    }

    //print barrier
    public void insertBarrier() {
        for (int i = 0; i < 123; i++) {
            System.out.print("#");
        }
        System.out.println("");
    }

    @Override
    public void expiredProduct(ArrayList<Product> proList) {
        //if expired date is smaller than today , it will print to the console with the format
        System.out.println("**                                                       =EXPIRED=                                                       **");
        insertBarrier();
        System.out.printf("#%-11s# %-18s# %-11s# %-20s# %-20s# %-18s# %-11s#\n", "ID", "NAME", "SIZE", "MANUFACTURING DATE",
                "EXPIRATION DATE", "PRICE", "QUANTITY");
        insertBarrier();
        for (Product product : proList) {
            if (product.getExpirationDate().isBefore(LocalDate.now())) {
                System.out.println(product);
            }
        }
        insertBarrier();
    }

    @Override
    public void sellingProduct(ArrayList<Product> proList) {
        //if expired date is greater than today , it will print to the console with the format
        System.out.println("**                                                       =SELLING=                                                       **");
        insertBarrier();
        System.out.printf("#%-11s# %-18s# %-11s# %-20s# %-20s# %-18s# %-11s#\n", "ID", "NAME", "SIZE", "MANUFACTURING DATE",
                "EXPIRATION DATE", "PRICE", "QUANTITY");
        insertBarrier();
        for (Product product : proList) {
            if (product.getExpirationDate().isAfter(LocalDate.now())) {
                System.out.println(product);
            }
        }
        insertBarrier();
    }

    @Override
    public void runningOutOfStockProduct(ArrayList<Product> proList) {
        //add product to a new list and sort it ascengdingly
        ArrayList<Product> newProList = new ArrayList<>();
        for (Product product : proList) {
            if (product.getQuantity() < 3) {
                newProList.add(product);
            }
        }
        Collections.sort(newProList, Product.compQuantity);
        
        //print the format of product list into console
        System.out.println("**                                                   =RUN OUT OF STOCK=                                                  **");
        insertBarrier();
        System.out.printf("#%-11s# %-18s# %-11s# %-20s# %-20s# %-18s# %-11s#\n", "ID", "NAME", "SIZE", "MANUFACTURING DATE",
                "EXPIRATION DATE", "PRICE", "QUANTITY");
        insertBarrier();
        for (Product product : newProList) {
            System.out.println(product);
        }
        insertBarrier();
    }

    @Override
    public void printImportReceiptProduct(ArrayList<ImportReceipt> iList) {
        //loop used to search for imprt receipt and print it out
        for (ImportReceipt imReceipt : iList) {
            System.out.println(imReceipt.importExportInformation());
            System.out.println();
        }
    }

    @Override
    public void printExportReceiptProduct(ArrayList<ExportReceipt> eList) {
        for (ExportReceipt exReceipt : eList) {
            System.out.println(exReceipt.importExportInformation());
            System.out.println();
        }
    }

    @Override
    public void searchImportExportID(ArrayList<ImportReceipt> iList, ArrayList<ExportReceipt> eList) {
        String proCode = vd.inputProductIDNonList("Input product ID that you want to search: ");

        int c = 0;
        for (ImportReceipt importReceipt : iList) {
            for (Product product : importReceipt.getProList()) {
                if (product.getProductID().equalsIgnoreCase(proCode)) {
                    System.out.println("Product found in receipt " + importReceipt.getImportCode());
                    System.out.println(importReceipt.importExportInformation());
                    c++;
                }
            }
        }

        for (ExportReceipt exportReceipt : eList) {
            for (Product product : exportReceipt.getProList()) {
                if (product.getProductID().equalsIgnoreCase(proCode)) {
                    System.out.println("Product found in receipt " + exportReceipt.getExportCode());
                    System.out.println(exportReceipt.importExportInformation());
                    c++;
                }
            }
        }

        if (c == 0) {
            System.out.println("PRODUCT NOT FOUND!!");
        }
    }
}
