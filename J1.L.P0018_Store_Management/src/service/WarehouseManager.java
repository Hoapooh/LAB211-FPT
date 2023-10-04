/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.ExportReceipt;
import entity.ImportReceipt;
import entity.Product;
import interfaceService.IWarehouseManager;

import java.time.LocalDateTime;
import java.util.ArrayList;
import utilities.InputData;
import utilities.SearchData;
import utilities.ValidateData;

/**
 *
 * @author anphu
 */
public class WarehouseManager implements IWarehouseManager {

    InputData ind = new InputData();
    ValidateData vd = new ValidateData();
    SearchData sd = new SearchData();
    ProductManager pm = new ProductManager();

    @Override
    public void createImportReceipt(ArrayList<Product> proList, ArrayList<ImportReceipt> iList) {
        //show product list
        System.out.println("Here is the product list");
        pm.showAllProduct(proList);
        //create another list for import receipt
        ArrayList<Product> ipList = new ArrayList<>();
        String code = "";
        boolean choice = true;
        //loop use to input data and check if user want to continue or not
        while (choice) {
            //create import code
            code = vd.createImportID(iList);
            String idIn = vd.inputProductIDCheckExist("Input id of product you want to import: ", proList);
            int quantityIn = ind.inputInt("Input quanity of product to import: ", 0, Integer.MAX_VALUE);
            //we get product information
            Product product = sd.searchProductByID(proList, idIn);
            //add product to ipList
            ipList.add(new Product(product.getProductID(),product.getNameProduct(),product.getSize(),product.getPrice(),quantityIn));
            //set the quantity in product list to update the real number that we import into out store
            product.setQuantity(product.getQuantity() + quantityIn);
            System.out.println("PRODUCT HAS BEEN ADDED TO RECEIPT SUCCESSFULLY!!");
            //ask user to continue or not
            choice = ind.inputYesNo("Do you want to continue adding product to receipt?(Y/N) ");
        }
        LocalDateTime today = LocalDateTime.now();
        //add import receipt to the main import list
        iList.add(new ImportReceipt(code, ipList, today));
        System.out.println("IMPORT RECEIPT HAS BEEN CREATED SUCCESSFULLY!!");
    }

    @Override
    public void createExportReceipt(ArrayList<Product> proList, ArrayList<ExportReceipt> eList) {
        //show product list
        System.out.println("Here is the product list");
        pm.showAllProduct(proList);
        //create another list for export receipt
        ArrayList<Product> exList = new ArrayList<>();
        String code = "";
        boolean choice = true;
        //loop use to input data and check if user want to continue or not
        while (choice) {
            //create export id
            code = vd.createExportCode(eList);
            String idEn = vd.inputProductIDCheckExist("Input id of product you want to export: ", proList);
            Product product = sd.searchProductByID(proList, idEn);
            int quantityOut = ind.inputInt("Input quanity of product to export: ", 0, product.getQuantity());
            //add product you want to the exList
            exList.add(new Product(product.getProductID(),product.getNameProduct(),product.getSize(),product.getPrice(),quantityOut));
            System.out.println("PRODUCT HAS BEEN ADDED TO RECEIPT SUCCESSFULLY!!");
            //set the quantity in product list to update the real number that we export out of store
            product.setQuantity(product.getQuantity() - quantityOut);
            //ask user to continue or not
            choice = ind.inputYesNo("Do you want to continue adding product to receipt?(Y/N) ");
        }
        LocalDateTime today = LocalDateTime.now();
        //add export receipt to the main export list
        eList.add(new ExportReceipt(code, exList, today));
        System.out.println("EXPORT RECEIPT HAS BEEN CREATED SUCCESSFULLY!!");
    }

}
