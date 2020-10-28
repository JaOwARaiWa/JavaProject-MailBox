package condo.model;

import java.util.ArrayList;

public class Room
{
    private String building;
    private String floor;
    private String type;
    private String roomNumber;
    private ArrayList<String> resident = new ArrayList<>();
    private String room;

    public Room(String building, String floor, String type, String roomNumber)
    {
        this.building = building;
        this.floor = floor;
        this.type = type;
        this.roomNumber = roomNumber;
    }

    public String getBuilding()
    {
        return building;
    }

    public void setBuilding(String building)
    {
        this.building = building;
    }

    public String getFloor()
    {
        return floor;
    }

    public void setFloor(String floor)
    {
        this.floor = floor;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public String getRoomNumber()
    {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber)
    {
        this.roomNumber = roomNumber;
    }

    public ArrayList<String> getAllResident()
    {
        return resident;
    }

    public String getOwner()
    {
        return resident.get(0);
    }

    public String getResident1()
    {
        return resident.get(1);
    }

    public String getResident2()
    {
        int check = 0;
        if (getType().equals("Deluxe"))
        {
            check = -1;
        }
        else if (getType().equals("Suite"))
        {
            check = 1;
        }

        if (check == -1)
        {
            return "-";
        }
        else
        {
            return resident.get(2);
        }
    }

    public String getResident3()
    {
        int check = 0;
        if (getType().equals("Deluxe"))
        {
            check = -1;
        }
        else if (getType().equals("Suite"))
        {
            check = 1;
        }

        if (check == -1)
        {
            return "-";
        }
        else
        {
            return resident.get(3);
        }
    }

    public void setResident(ArrayList<String> resident)
    {
        if (resident.size() == 2)
        {
            this.resident.clear();
            this.resident.add(resident.get(0));
            this.resident.add(resident.get(1));
        }
        else if (resident.size() == 4)
        {
            this.resident.clear();
            this.resident.add(resident.get(0));
            this.resident.add(resident.get(1));
            this.resident.add(resident.get(2));
            this.resident.add(resident.get(3));
        }
    }

    public void setRoom(String room)
    {
        this.room = room;
    }

    public String getRoom()
    {
        return room;
    }

    public void setRoom()
    {
        String buildingName = this.building;
        String floorName = this.floor;
        String roomNumName = this.roomNumber;

        this.room = buildingName + floorName + roomNumName;
    }

    public void reset()
    {
        this.building = null;
        this.floor = null;
        this.type = null;
        this.roomNumber = null;
        //this.resident.clear();
        this.room = null;
    }


}
