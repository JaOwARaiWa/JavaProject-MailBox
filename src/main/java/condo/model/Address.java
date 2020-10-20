package condo.model;

public class Address
{
    private char building;
    private int floor;
    private String roomnumber;

    public Address(char building, int floor, String roomnumber)
    {
        this.building = building;
        this.floor = floor;
        this.roomnumber = roomnumber;
    }

    public char getBuilding()
    {
        return building;
    }

    public int getFloor()
    {
        return floor;
    }

    public String getRoomnumber()
    {
        return roomnumber;
    }

    public String getAddress()
    {
        String address = building + "" + floor + "" + roomnumber;
        return address;
    }

    /*@Override
    public String toString()
    {
        return "Room : " + building + floor + roomnum +"\n";
    }*/
}
