package condo.model;

public class Resident extends User
{
    private Address address;

    public Resident(String username, String password, String name, String id, Address address)
    {
        super(username, password, name, id);
        this.address = address;
    }

    public Resident(String username, String password, String name, String id)
    {
        super(username, password, name, id);
    }

    public void setAddress(Address address)
    {
        this.address = address;
    }

    public Address getAddress()
    {
        return address;
    }
}
