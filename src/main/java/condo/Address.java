package condo;

public class Address
{
    private char building;
    private char floor;
    private String roomnum;

    public Address(char building, char floor, String roomnum)
    {
        this.building = building;
        this.floor = floor;
        this.roomnum = roomnum;
    }

    public char getBuilding()
    {
        return building;
    }

    public char getFloor()
    {
        return floor;
    }

    public String getRoomnum()
    {
        return roomnum;
    }

    public String id()
    {
        String id = building + "" + floor + roomnum;
        return id;
    }

    /*@Override
    public String toString()
    {
        return "Room : " + building + floor + roomnum +"\n";
    }*/
}
