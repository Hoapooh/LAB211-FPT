/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 *
 * @author anphu
 */
public class ImportReceipt extends WareHouse {

    private String importCode;

    public ImportReceipt() {
    }

    public ImportReceipt(String importCode, ArrayList<Product> proList, LocalDateTime importExportDate) {
        super(proList, importExportDate);
        this.importCode = importCode;
    }

    public String getImportCode() {
        return importCode;
    }

    public void setImportCode(String importCode) {
        this.importCode = importCode;
    }

    //print barrier
    public String insertBarrier() {
        String str = "";
        for (int i = 0; i < 79; i++) {
            str += "#";
        }
        str += "\n";
        return str;
    }

    //print the format of import receipt into console
    @Override
    public String importExportInformation() {

        String str = "";
        for (Product product : this.proList) {
            str += product.printToReceipt() + "\n";
        }

        return insertBarrier()
                + "#                                  " + importCode + "                                  #\n"
                + insertBarrier()
                + String.format("#%-11s# %-18s# %-11s# %-18s# %-11s#\n", "ID", "NAME", "SIZE", "PRICE", "QUANTITY")
                + str
                + "######################Local Time: " + getImportExportDate() + "######################";
    }

    //use to add into file with the format below
    @Override
    public String toString() {
        String str = "import," + importCode + "," + importExportDate;
        
        for (Product product : proList) {
            str += "," + product.getProductID() + ":" + product.getNameProduct() + ":" + product.getSize() + ":" + product.getPrice() + ":" + product.getQuantity();
        }
        
        return str + "\n";
    }
}
