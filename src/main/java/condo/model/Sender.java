package condo.model;

public class Sender
{
    private String name;
    private String address;

    public Sender(String name, String address)
    {
        this.name = name;
        this.address = address;
    }

    public String getName()
    {
        return name;
    }

    public String getAddress()
    {
        return address;
    }

    /*@Override
    public String toString()
    {
        return "Sender : " + name + " | " + address + "\n";
    }*/
}
