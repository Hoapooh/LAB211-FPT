/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Vehicle;
import java.util.ArrayList;

/**
 *
 * @author anphu
 */
public class SearchData {
    public static Vehicle searchVehicleID(ArrayList<Vehicle> list,String id) {
        for (Vehicle vehicle : list) {
            if (vehicle.getID_Vehicle().equalsIgnoreCase(id)) {
                return vehicle;
            }
        }
        return null;
    }
}
