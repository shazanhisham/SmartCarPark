import java.io.Serializable;

abstract public class Vehicle  implements Serializable {

    public Vehicle(int regNo, String type, String time ){
        this.regNo = regNo;
        this.type = type;
        this.time = time;
    }
    private int regNo;
    private String type;
    private String time;

    public int getRegNo() {
        return regNo;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String toString(){
        return "RegNumber: "+ regNo + ", Type: " + type + ", ParkedTime: " + time;
    }

    abstract void test();
}
