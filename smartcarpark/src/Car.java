public class Car extends Vehicle {

    public Car(int regNo, String type, String time) {
        super(regNo, type, time);
    }

    public void test(){
        System.out.println("It works in car");
    }
}
