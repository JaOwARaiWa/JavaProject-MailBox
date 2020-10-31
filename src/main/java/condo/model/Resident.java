package condo.model;

public class Resident extends User
{
    private String room;

    public Resident(String username, String password, String name, String id, String room)
    {
        super(username, password, name, id);
        this.room = room;
    }

    /*public Resident(String username, String password, String name, String id)
    {
        super(username, password, name, id);
    }*/

    public void setRoom(String room)
    {
        this.room = room;
    }

    public String getRoom()
    {
        return room;
    }
}
