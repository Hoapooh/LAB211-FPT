/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaceService;

import entity.ExportReceipt;
import entity.ImportReceipt;
import entity.Product;
import java.util.ArrayList;

/**
 *
 * @author anphu
 */
public interface IWarehouseManager {
    void createImportReceipt(ArrayList<Product> proList, ArrayList<ImportReceipt> iList);
    void createExportReceipt(ArrayList<Product> proList, ArrayList<ExportReceipt> eList);
}
