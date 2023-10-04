/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.ExportReceipt;
import entity.ImportReceipt;
import entity.Product;
import interfaceService.IStoreWarehouseToFiles;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

/**
 *
 * @author anphu
 */
public class StoreWarehouseToFiles implements IStoreWarehouseToFiles{

    @Override
    public void saveDataToFile(String filename, List<ImportReceipt> iList, List<ExportReceipt> eList) {
        try {
            //use bufferedwriter to write string objects
            BufferedWriter bw = new BufferedWriter(new FileWriter(new File(filename)));
            
            for (ImportReceipt imp : iList) {
                bw.write(imp.toString());
            }
            
            for (ExportReceipt exp : eList) {
                bw.write(exp.toString());
            }
            
            System.out.println("BOTH RECEIPT HAS BEEN SAVED SUCCESSFULLY!!!");
            //close file after using
            bw.close();
        }
        catch (IOException | NumberFormatException otherException) {
            System.out.println("[ERROR] " + otherException.getMessage()); // Print out error message to the console
        }
    }

    @Override
    public void loadDataFromFile(String filename, List<ImportReceipt> iList, List<ExportReceipt> eList) {
        try {
            //use buffered reader to load the string in file
            BufferedReader br = new BufferedReader(new FileReader(new File(filename)));
            
            String line;
            while((line = br.readLine()) != null) {
                StringTokenizer stk = new StringTokenizer(line, ",");
                
                String type = stk.nextToken().trim();
                String iecode = stk.nextToken().trim();
                LocalDateTime date = LocalDateTime.parse(stk.nextToken().trim());
                
                ArrayList<Product> pList = new ArrayList<>();
                while(stk.hasMoreTokens()) {
                    StringTokenizer stk1 = new StringTokenizer(stk.nextToken(), ":");
                    String pid = stk1.nextToken().trim();
                    String name = stk1.nextToken().trim();
                    String size = stk1.nextToken().trim();
                    double price = Double.parseDouble(stk1.nextToken().trim());
                    int quantity = Integer.parseInt(stk1.nextToken().trim());
                    pList.add(new Product(pid, name, size, price, quantity));
                }
                
                if (type.equals("import")) {
                    iList.add(new ImportReceipt(iecode, pList, date));
                } else {
                    eList.add(new ExportReceipt(iecode, pList, date));
                }
            }
            System.out.println("BOTH RECEIPT LOADED INTO LIST SUCCESSFULLY!!!");
            br.close();
        }
        catch (FileNotFoundException exception) {
            System.out.println("[ERROR] " + exception.getMessage()); // Should only used during development!
        }
        catch (IOException | NumberFormatException | NoSuchElementException exception) {
            System.out.println("[ERROR] " + exception.getMessage());
        }
    }
}
