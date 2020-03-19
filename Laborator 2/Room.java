package lab3;

public class Room {
    private String room, type_room;
    private int floor;

    public Room() {
    }

    public Room(String room, String type_room, int floor) {
        this.room = room;
        this.type_room = type_room;
        this.floor = floor;
    }

    public String getRoom() {
        return room;
    }

    public String getType() {
        return type_room;
    }

    public int getFloor() {
        return floor;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public void setType(String type) {
        this.type_room = type_room;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }
    public void displayRoom() {
        System.out.println(room + " " + type_room + " " + floor);
    }
}
