/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import entity.Product;
import java.util.ArrayList;

/**
 *
 * @author anphu
 */
public class SearchData {
    
    public Product searchProductByID(ArrayList<Product> arr, String id){
        for (Product product : arr) {
            if(product.getProductID().equals(id)){
                return product;
            }
        }
        return null;
    }
    
}
