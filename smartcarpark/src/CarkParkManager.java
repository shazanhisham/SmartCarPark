import java.util.ArrayList;

public interface CarkParkManager {
    int addVehicle(ArrayList<Vehicle> vehicleList);
    Vehicle removeVehicle(ArrayList<Vehicle> vehicleList, int regNo);
    void printVehicleParked(ArrayList<Vehicle> vehicleList);
    void printPercentage(ArrayList<Vehicle> vehicleList);
    void printVehicleByDay(ArrayList<Vehicle> vehicleList);
}

