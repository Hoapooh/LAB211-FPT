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
public abstract class WareHouse {
    protected ArrayList<Product> proList;
    protected LocalDateTime importExportDate;

    public WareHouse() {
    }

    public WareHouse(ArrayList<Product> proList, LocalDateTime importExportDate) {
        this.proList = proList;
        this.importExportDate = importExportDate;
    }

    public ArrayList<Product> getProList() {
        return proList;
    }

    public void setProList(ArrayList<Product> proList) {
        this.proList = proList;
    }

    public LocalDateTime getImportExportDate() {
        return importExportDate;
    }

    public void setImportExportDate(LocalDateTime importExportDate) {
        this.importExportDate = importExportDate;
    }
    
    public abstract String importExportInformation();

    //=========toString used to write format to file=========
    @Override
    public abstract String toString();
    
    
}
