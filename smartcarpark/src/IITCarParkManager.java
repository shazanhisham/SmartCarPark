import java.util.*;
import java.util.stream.Collectors;

public class IITCarParkManager implements CarkParkManager{
    Scanner myObj = new Scanner(System.in);

    Floor floorG = new Floor(60);
    Floor floor1 = new Floor(60);
    Floor floor2 = new Floor(60);
    Floor floor3 = new Floor(60);
    Floor floor4 = new Floor(60);
    Floor floor5 = new Floor(60);

    DateTime time = new DateTime();

    ArrayList<Vehicle> floorGVehicles= new ArrayList<>();
    ArrayList<Vehicle> floor1Vehicles= new ArrayList<>();
    ArrayList<Vehicle> floor2Vehicles= new ArrayList<>();
    ArrayList<Vehicle> floor3Vehicles= new ArrayList<>();
    ArrayList<Vehicle> floor4Vehicles= new ArrayList<>();
    ArrayList<Vehicle> floor5Vehicles= new ArrayList<>();

    ArrayList<Integer> allRegisterNumber = new ArrayList<>();
    ArrayList<String> allVehicleType = new ArrayList<>();

    ArrayList<ArrayList> allFloorListOfVehicles = new ArrayList<>(Arrays.asList(floorGVehicles,floor1Vehicles,floor2Vehicles,
            floor3Vehicles,floor4Vehicles,floor5Vehicles));
    ArrayList<Floor> allFloorList = new ArrayList<>(Arrays.asList(floor3,floor4,floor5,floor2,floor1,floorG));
    ArrayList<Floor> threeFloorList = new ArrayList<>(Arrays.asList(floor1,floor2,floorG));

    public ArrayList<Integer> getAllRegisterNumber() {
        return allRegisterNumber;
    }

    public ArrayList<String> getAllVehicleType() {
        return allVehicleType;
    }

    @Override
    public int addVehicle(ArrayList vehicleList) {
        System.out.println("Enter Registration Number");
        int regNo = myObj.nextInt();
        myObj.nextLine();
        while(true) {
            if (allRegisterNumber.contains(regNo)) {
                System.out.println("Vehicle already exists, enter again");
                regNo = myObj.nextInt();
                myObj.nextLine();
                continue;
            }
            if (regNo<=0) {
                System.out.println("Invalid number, enter again");
                regNo = myObj.nextInt();
                myObj.nextLine();
                continue;
            }
            break;
        }

        List<String> vehicleTypes = new ArrayList<>(Arrays.asList("Car","Bike","Van","MiniBus","MiniLorry"));
        System.out.println("Enter Vehicle type (\"Car\",\"Bike\",\"Van\",\"MiniBus\",\"MiniLorry\")");
        String type = myObj.nextLine();

        while(true) {
            if (!vehicleTypes.contains(type)) {
                System.out.println("Invalid type, enter again");
                type = myObj.nextLine();
                continue;
            }
            break;
        }

        allRegisterNumber.add(regNo);
        allVehicleType.add(type);

        if(type.equals("Car")) {
            vehicleList.add(0, new Car(regNo, type, time.getTime()));
            for(Floor floor : allFloorList){
                if(floor.getSpace() != 0){
                    floor.decreaseFloorSpace(3);
                    if(floor == floorG)
                        floorGVehicles.add(0, new Car(regNo, type, time.getTime()));
                    else if(floor == floor1)
                        floor1Vehicles.add(0, new Car(regNo, type, time.getTime()));
                    else if(floor == floor2)
                        floor2Vehicles.add(0, new Car(regNo, type, time.getTime()));
                    else if(floor == floor3)
                        floor3Vehicles.add(0, new Car(regNo, type, time.getTime()));
                    else if(floor == floor4)
                        floor4Vehicles.add(0, new Car(regNo, type, time.getTime()));
                    else if(floor == floor5)
                        floor5Vehicles.add(0, new Car(regNo, type, time.getTime()));
                    break;
                }
                else{
                    System.out.println("No space");
                }
            }
        }

        else if(type.equals("Van")){

            vehicleList.add(0,new Van(regNo,type, time.getTime()));
            for(Floor floor : threeFloorList){
                if(floor.getSpace() != 0){
                    floor.decreaseFloorSpace(6);
                    if(floor == floorG)
                        floorGVehicles.add(0, new Van(regNo,type, time.getTime()));
                    else if(floor == floor1)
                        floor1Vehicles.add(0, new Van(regNo,type, time.getTime()));
                    else if(floor == floor2)
                        floor2Vehicles.add(0, new Van(regNo,type, time.getTime()));

                    break;
                }
                else{
                    System.out.println("No Space");
                }
            }
        }

        else if(type.equals("Bike")) {

            vehicleList.add(0,new Bike(regNo,type, time.getTime()));
            for(Floor floor : threeFloorList){
                if(floor.getSpace() != 0){
                    floor.decreaseFloorSpace(1);
                    if(floor == floorG)
                        floorGVehicles.add(0, new Bike(regNo,type, time.getTime()));
                    else if(floor == floor1)
                        floor1Vehicles.add(0, new Bike(regNo,type, time.getTime()));
                    else if(floor == floor2)
                        floor2Vehicles.add(0, new Bike(regNo,type, time.getTime()));

                    break;
                }
                else{
                    System.out.println("No Space");
                }
            }
        }

        else if(type.equals("MiniBus")) {

            vehicleList.add(0,new MiniBus(regNo, type, time.getTime()));
            if(floorG.getSpace() !=0){
                floorG.decreaseFloorSpace(9);
                floorGVehicles.add(0, new MiniBus(regNo,type, time.getTime()));
            }
            else{
                System.out.println("No Space");
            }
        }
        else if(type.equals("MiniLorry")){

            vehicleList.add(0,new MiniLorry(regNo,type, time.getTime()));
            if(floorG.getSpace() !=0){
                floorG.decreaseFloorSpace(9);
                floorGVehicles.add(0, new MiniLorry(regNo,type, time.getTime()));
            }
            else{
                System.out.println("No Space");
            }
        }
        else
            System.out.println("Type Doesn't Exist");

        return (floorG.getSpace() + floor1.getSpace() + floor2.getSpace() + floor3.getSpace() + floor4.getSpace() + floor5.getSpace())/3;


    }

    @Override
    public Vehicle removeVehicle(ArrayList<Vehicle> vehicleList, int regNo) {
        for (Iterator<Vehicle> itr = vehicleList.iterator(); itr.hasNext();){
            Vehicle vehicle = itr.next();
            if(vehicle.getRegNo() == regNo){
                itr.remove();
            }
            for(ArrayList floor : allFloorListOfVehicles){
                for (Iterator<Vehicle> itr2 = floor.iterator(); itr2.hasNext();){
                    Vehicle vehicleInFloor = itr2.next();
                    if(vehicleInFloor.getRegNo() == regNo){
                        switch (vehicleInFloor.getType()) {
                            case "Car":
                                if (allFloorListOfVehicles.indexOf(floor) == 0) floorG.increaseFloorSpace(3);
                                else if (allFloorListOfVehicles.indexOf(floor) == 1) floor1.increaseFloorSpace(3);
                                else if (allFloorListOfVehicles.indexOf(floor) == 2) floor2.increaseFloorSpace(3);
                                else if (allFloorListOfVehicles.indexOf(floor) == 3) floor3.increaseFloorSpace(3);
                                else if (allFloorListOfVehicles.indexOf(floor) == 4) floor4.increaseFloorSpace(3);
                                else if (allFloorListOfVehicles.indexOf(floor) == 5) floor5.increaseFloorSpace(3);
                                break;
                            case "Van":
                                if (allFloorListOfVehicles.indexOf(floor) == 0) floorG.increaseFloorSpace(6);
                                else if (allFloorListOfVehicles.indexOf(floor) == 1) floorG.increaseFloorSpace(6);
                                else if (allFloorListOfVehicles.indexOf(floor) == 2) floorG.increaseFloorSpace(6);
                                break;
                            case "Bike":
                                if (allFloorListOfVehicles.indexOf(floor) == 0) floorG.increaseFloorSpace(1);
                                else if (allFloorListOfVehicles.indexOf(floor) == 1) floorG.increaseFloorSpace(1);
                                else if (allFloorListOfVehicles.indexOf(floor) == 2) floorG.increaseFloorSpace(1);
                                break;
                            case "MiniBus":
                            case "MiniLorry":
                                floorG.increaseFloorSpace(9);
                                break;
                        }
                        itr2.remove();
                        System.out.println("Remove Vehicle Type: " + vehicleInFloor.getType());
                        return vehicleInFloor;
                    }
                }
            }
        }


        return null;
    }

    @Override
    public void printVehicleParked(ArrayList vehicleList) {
        if(vehicleList.size() == 0){
            System.out.println("No vehicles are parked yet");
        }
        else{
            System.out.println("Currently Parked Vehicle : " + allFloorListOfVehicles);
        }
    }

    @Override
    public void printPercentage(ArrayList vehicleList) {
        float  carCount = 0;
        float  bikeCount = 0;
        float  vanCount = 0;
        float  miniLorryCount = 0;
        float  miniBusCount = 0;

        for (int i = 0; i < vehicleList.size(); i++) {
            if(vehicleList.get(i).getClass().getName() == "Car")carCount++;
            else if(vehicleList.get(i).getClass().getName().equals("Bike")) bikeCount++;
            else if(vehicleList.get(i).getClass().getName().equals("Van")) vanCount++;
            else if(vehicleList.get(i).getClass().getName().equals("MiniBus")) miniBusCount++;
            else if(vehicleList.get(i).getClass().getName().equals("MiniLorry")) miniLorryCount++;
        }

        System.out.println("Car percentage :" + ((carCount/vehicleList.size())* 100));
        System.out.println("Bike percentage :" + ((bikeCount/vehicleList.size())* 100));
        System.out.println("Van percentage :" + ((vanCount/vehicleList.size())* 100));
        System.out.println("MiniLorry percentage :" + ((miniLorryCount/vehicleList.size())* 100));
        System.out.println("MiniBus percentage :" +((miniBusCount/vehicleList.size())* 100));
    }


    @Override
    public void printVehicleByDay(ArrayList<Vehicle> vehicleList) {
        System.out.println("Enter Specific Date : (Format: dd-mm-yyyy)");
        String userDate = myObj.nextLine();

        while(true) {
            if ( !(userDate.matches("\\d{2}-\\d{2}-\\d{4}"))) {
                System.out.println("Invalid date format, enter again");
                userDate = myObj.nextLine();
                continue;
            }
            break;
        }

        List<String> vehicleListString = vehicleList.stream().map(object -> Objects.toString(object, null)).collect(Collectors.toList());
        ArrayList<String> userDateList = new ArrayList<>();
        for (String string : vehicleListString) {
            if(string.contains(userDate)){
                userDateList.add(string);
            }
        }
        if(userDateList.size() == 0){
            System.out.println("No vehicles parked on that date");
        }
        else System.out.println("Parked on " + userDate +" : " + userDateList);
    }


}