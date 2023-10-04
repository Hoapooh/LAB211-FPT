/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaceService;

import entity.Product;
import java.util.ArrayList;

/**
 *
 * @author anphu
 */
public interface IStoreProductToFiles {
    public void loadProductFromFile(String filename, ArrayList<Product> proList);
    public void saveProductToFile(String filename, ArrayList<Product> proList);
}
