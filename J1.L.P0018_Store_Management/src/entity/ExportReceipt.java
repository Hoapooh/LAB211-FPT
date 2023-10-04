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
public class ExportReceipt extends WareHouse {

    private String exportCode;

    public ExportReceipt() {
    }

    public ExportReceipt(String exportCode, ArrayList<Product> proList, LocalDateTime importExportDate) {
        super(proList, importExportDate);
        this.exportCode = exportCode;
    }

    public String getExportCode() {
        return exportCode;
    }

    public void setExportCode(String exportCode) {
        this.exportCode = exportCode;
    }    

    public String insertBarrier() {
        String str = "";
        for (int i = 0; i < 79; i++) {
            str += "#";
        }
        str += "\n";
        return str;
    }

    //print the format of export receipt into console
    @Override
    public String importExportInformation() {
        
        String str = "";
        for (Product product : this.proList) {
            str += product.printToReceipt() + "\n";
        }
        
        return insertBarrier()
                + "#                                  " + exportCode + "                                  #\n"
                + insertBarrier()
                + String.format("#%-11s# %-18s# %-11s# %-18s# %-11s#\n", "ID", "NAME", "SIZE", "PRICE", "QUANTITY")
                + str
                + "######################Local Time: " + getImportExportDate() + "######################";
    }

    //use to add into file with the format below
    @Override
    public String toString() {
        String str = "export," + exportCode + "," + importExportDate;
        
        for (Product product : proList) {
            str += "," + product.getProductID() + ":" + product.getNameProduct() + ":" + product.getSize() + ":" + product.getPrice() + ":" + product.getQuantity();
        }
        
        return str + "\n";
    }
}
