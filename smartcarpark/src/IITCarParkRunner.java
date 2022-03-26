import java.io.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.time.*;

public class IITCarParkRunner {

    public static void main(String[] args) throws IOException {

        Scanner myObj = new Scanner(System.in);

        IITCarParkManager manager = new IITCarParkManager();
        ArrayList<Vehicle> vehicleList = new ArrayList<>(); //All floors
        ArrayList<Integer> removedVehicleList = new ArrayList<>();
        ArrayList<String> removedAddTimeList = new ArrayList<>();
        DateTime time = new DateTime();
        ArrayList<Integer> timeList = new ArrayList<>();
        ArrayList<Integer> removedTimeList = new ArrayList<>();
        ArrayList<String> dateList = new ArrayList<>();

        int remainingSpots;
        String parkingTime = null;
        int hours;

        while(true) {
            System.out.println();
            System.out.println("To Park, enter 1 ");
            System.out.println("To remove vehicle, enter 2");
            System.out.println("To view currently parked vehicles, enter 3");
            System.out.println("To view percentage of vehicles, enter 4");
            System.out.println("To show longest parked and last parked vehicles, enter 5");
            System.out.println("To show vehicles parked on specific date, enter 6");
            System.out.println("To exit program, enter 7");

            int menuAnswer = myObj.nextInt();
            myObj.nextLine();

            if(menuAnswer == 1) {
                String addMore = "y";
                while (addMore.equals("y")) {

                    if (addMore.equals("y")) {
                        remainingSpots = manager.addVehicle(vehicleList);
                        System.out.println("How many hours you want to park ?");
                        hours = myObj.nextInt();
                        myObj.nextLine();



                        if(remainingSpots!=0)
                        System.out.println("Remaining Spots : " + remainingSpots);
                    } else {
                        addMore.equals("n");
                        break;
                    }

                    timeList.add(hours); //List of hours parked
                    parkingTime = time.getTime();

                    LocalDate parkedDateNotString = LocalDate.now();
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                    String parkedDate = dtf.format(parkedDateNotString);
                    dateList.add(parkedDate);
                    System.out.println("Do you want to park more?");
                    addMore = myObj.nextLine();
                }


            }

            if(menuAnswer == 2){
                if(vehicleList.size() != 0) {
                    System.out.println("Enter registration number plate");
                    int removeRegNo = myObj.nextInt();
                    myObj.nextLine();

                    while(true) {
                        if (!(manager.getAllRegisterNumber().contains(removeRegNo))) {
                            System.out.println("Vehicle doesn't exist, enter again");
                            removeRegNo = myObj.nextInt();
                            myObj.nextLine();
                            continue;
                        }
                        break;
                    }
                    manager.removeVehicle(vehicleList, removeRegNo);

                    //finding regNo and hours parked of removed vehicle
                    removedVehicleList.add(removeRegNo); //List of removed vehicles list
                    int indexOfRemovedVehicle = manager.getAllRegisterNumber().indexOf(removeRegNo);
                    int removedTimeFromList = timeList.get(indexOfRemovedVehicle);
                    removedTimeList.add(removedTimeFromList);

                    manager.getAllRegisterNumber();

                    removedAddTimeList.add(parkingTime);

                    int cost = time.calculateCost(manager.getAllVehicleType().get(manager.getAllRegisterNumber().indexOf
                            (removeRegNo)), removedTimeFromList);
                    System.out.println("Cost for Parking : Rs." + cost);
                }
                else{
                    System.out.println("There is no vehicles in the parking lot");
                }

            }
            if(menuAnswer == 3){
                manager.printVehicleParked(vehicleList) ;
            }
            if(menuAnswer == 4){
                manager.printPercentage(vehicleList) ;
            }
            if(menuAnswer == 5)
            {
                if(!(removedVehicleList.size() ==0)) {
                    //Longest Park time
                    int longestTimeIndex = removedTimeList.indexOf(Collections.max(removedTimeList));
                    int longestRegNo = removedVehicleList.get(longestTimeIndex); //Get longest regNo using index of time list
                    String longestType = manager.getAllVehicleType().get(manager.getAllRegisterNumber().indexOf(longestRegNo)); //Get longest Type
                    String longestParkingTime = removedAddTimeList.get(longestTimeIndex);
                    System.out.println("Longest Vehicle : " + "RegNo: " + longestRegNo + ", Type: " + longestType +
                            ", Entry Time: " + longestParkingTime + ", Hours Parked: " + removedTimeList.get(longestTimeIndex));
                }
                else{
                    System.out.println("Cannot find longest stayed vehicle because none left the parking yet");
                }
                if(!(vehicleList.size() == 0)){
                    System.out.println("Last Parked vehicle : " + vehicleList.get(0));
                }
            }
            if(menuAnswer == 6){
                manager.printVehicleByDay(vehicleList);
            }
            if(menuAnswer == 7){
                //write to file
                System.out.println("Do you want to save the details of vehicles currently parked? (y/n)");
                String fileSave = myObj.nextLine();

                if(fileSave.equals("y")) {
                    try {
                        FileOutputStream writeData = new FileOutputStream("parkedVehicles.ser");
                        ObjectOutputStream writeStream = new ObjectOutputStream(writeData);

                        writeStream.writeObject(vehicleList);
                        writeStream.flush();
                        writeStream.close();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    //read file
                    try {
                        FileInputStream readData = new FileInputStream("parkedVehicles.ser");
                        ObjectInputStream readStream = new ObjectInputStream(readData);

                        ArrayList<Vehicle> vehicles2 = (ArrayList<Vehicle>) readStream.readObject();
                        readStream.close();
                        System.out.println(vehicles2.toString());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;
            }

            }
        }

        //((Car)vehicleList.get(0)).test();
        //vehicleList.get(0).test();

}

