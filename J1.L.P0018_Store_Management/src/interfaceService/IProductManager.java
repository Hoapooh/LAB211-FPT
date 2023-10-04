package interfaceService;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import entity.ExportReceipt;
import entity.ImportReceipt;
import entity.Product;
import java.util.ArrayList;

/**
 *
 * @author anphu
 */
public interface IProductManager {
    void addProduct(ArrayList<Product> proList);
    void updateProduct(ArrayList<Product> proList);
    void deleteProduct(ArrayList<Product> proList, ArrayList<ImportReceipt> iList, ArrayList<ExportReceipt> eList);
    void showAllProduct(ArrayList<Product> proList);
}
