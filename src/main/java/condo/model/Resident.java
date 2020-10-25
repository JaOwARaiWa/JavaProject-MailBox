package condo.model;

public class Resident extends User
{
    private Room room;

    public Resident(String username, String password, String name, String id, Room room)
    {
        super(username, password, name, id);
        this.room = room;
    }

    public Resident(String username, String password, String name, String id)
    {
        super(username, password, name, id);
    }

    public void setRoom(Room room)
    {
        this.room = room;
    }

    public Room getRoom()
    {
        return room;
    }
}
