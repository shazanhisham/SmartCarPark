import java.time.format.DateTimeFormatter;
import java.time.*;

class DateTime {
    public String getTime() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return (dtf.format(now));
    }

    public int calculateCost(String type, int hours){
        int cost = 0;
        int days = hours/24;
        int extraHours = hours%24;
        switch (type) {
            case "Car":
                if(days == 0) {
                    if (hours < 3) {
                        cost = hours * 300;
                    } else {
                        cost = 900 + ((hours - 3) * 400);
                    }
                }
                else{
                    int costForextraHours = extraHours * 400;
                    if(costForextraHours > 3000){
                        cost = (3000) +(days * 3000) ;
                    }
                    else{
                        cost = (costForextraHours) + (days * 3000);
                    }
                }
                break;
            case "Van":
                if(days == 0) {
                    if (hours < 3) {
                        cost = hours * 600;
                    } else {
                        cost = 1800 + ((hours - 3) * 700);
                    }
                }
                else{
                    int costForextraHours = extraHours * 700;
                    if(costForextraHours > 3000){
                        cost = (3000) +(days * 3000) ;
                    }
                    else{
                        cost = (costForextraHours) + (days * 3000);
                    }
                }
                break;
            case "Bike":
                if(days == 0) {
                    if (hours < 3) {
                        cost = hours * 100;
                    } else {
                        cost = 300 + ((hours - 3) * 200);
                    }
                }
                else{
                    int costForextraHours = extraHours * 200;
                    if(costForextraHours > 3000){
                        cost = (3000) +(days * 3000) ;
                    }
                    else{
                        cost = (costForextraHours) + (days * 3000);
                    }
                }
                break;
            case "MiniBus":
            case "MiniLorry":
                if(days == 0) {
                    if (hours < 3) {
                        cost = hours * 900;
                    } else {
                        cost = 1800 + ((hours - 3) * 1000);
                    }
                }
                else{
                    int costForextraHours = extraHours * 1000;
                    if(costForextraHours > 3000){
                        cost = (3000) +(days * 3000) ;
                    }
                    else{
                        cost = (costForextraHours) + (days * 3000);
                    }
                }
                break;
        }
        return cost;
    }
}

