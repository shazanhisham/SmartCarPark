public class Floor {

    public Floor(int space){
        this.space = 180;
    }

    int space;

    public int getSpace() {
        return space;
    }

    public void setSpace(int space) {
        this.space = space;
    }

    public void increaseFloorSpace(int space){
        setSpace(this.space + space);
    }

    public void decreaseFloorSpace(int space){
        setSpace(this.space - space);
    }

}
