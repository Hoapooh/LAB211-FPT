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
public interface IReportManager {
    void expiredProduct(ArrayList<Product> proList);
    void sellingProduct(ArrayList<Product> proList);
    void runningOutOfStockProduct(ArrayList<Product> proList);
    void printImportReceiptProduct(ArrayList<ImportReceipt> iList);
    void printExportReceiptProduct(ArrayList<ExportReceipt> eList);
    void searchImportExportID(ArrayList<ImportReceipt> iList, ArrayList<ExportReceipt> eList);
}
