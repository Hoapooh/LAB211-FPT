/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaceService;

import entity.ExportReceipt;
import entity.ImportReceipt;
import java.util.List;

/**
 *
 * @author anphu
 */
public interface IStoreWarehouseToFiles {
    public void saveDataToFile(String filename, List<ImportReceipt> iList, List<ExportReceipt> eList);
    public void loadDataFromFile(String filename, List<ImportReceipt> iList, List<ExportReceipt> eList);
}
