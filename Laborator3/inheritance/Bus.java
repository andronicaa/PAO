package inheritance;

public class Bus extends Car{
    int seatis = 20;
    private int doors = 2;
    public Bus() {

    }
    public int getDoors() {
        return doors;
    }

    public void setDoors(int doors) {
        this.doors = doors;
    }
}
