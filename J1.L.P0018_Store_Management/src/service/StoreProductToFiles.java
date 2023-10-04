/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.Product;
import interfaceService.IStoreProductToFiles;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *
 * @author anphu
 */
public class StoreProductToFiles implements IStoreProductToFiles {
    
    @Override
    public void loadProductFromFile(String filename, ArrayList<Product> proList) {
        try {
            File file = new File(filename);
            if (!file.exists()) {
                System.err.println("CAN NOT FIND THE FILE YOU REQUIRED");
                return;
            }
            
            BufferedReader bfr = new BufferedReader(new FileReader(file));

            String line;
            while ((line = bfr.readLine()) != null) {
                StringTokenizer stk = new StringTokenizer(line,"\\#");

                String id = stk.nextToken().trim();
                String name = stk.nextToken().trim();
                String size = stk.nextToken().trim();
                LocalDate manuDate = LocalDate.parse(stk.nextToken().trim());
                LocalDate expDate = LocalDate.parse(stk.nextToken().trim());
                double price = Double.parseDouble(stk.nextToken().trim());
                int quantity = Integer.parseInt(stk.nextToken().trim());
                Product p = new Product(id, name, size, manuDate, expDate, price, quantity);
                proList.add(p);
            }
            bfr.close();
            System.out.println("PRODUCT LOADED FROM FILE SUCCESSFULLY!!");
        } catch (IOException ioe) {
            System.err.println("FILE ERROR!!");
        }
    }
    
    @Override
    public void saveProductToFile(String filename, ArrayList<Product> proList) {
        
        if (proList.isEmpty()) {
            System.err.println("THE DATA IS EMPTY!!");
            return;
        }

        try {
            File file = new File(filename);
            PrintWriter pw = new PrintWriter(new FileWriter(file));
            for (Product pro : proList) {
                pw.println(String.format("%-11s# %-18s# %-11s# %-20s# %-20s# %-18.2f# %-11d", 
                pro.getProductID(),pro.getNameProduct(),pro.getSize(),
                pro.getManufactoringDate(),pro.getExpirationDate(),pro.getPrice(),pro.getQuantity()));
            };
            pw.close();
            System.out.println("PRODUCT HAS BEEN SAVED SUCCESSFULLY!!");
        } catch (IOException ioe) {
            System.out.println("CAN NOT SAVE FILE!!");
        }
    }
}
