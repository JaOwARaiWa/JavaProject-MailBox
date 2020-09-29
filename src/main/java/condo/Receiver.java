package condo;

import java.util.Scanner;

public class Receiver implements Changing
{
    private String name;
    private Address address;
    private String id;
    private String password;

    public Receiver(String name, Address address)
    {
        this.name = name;
        this.address = address;
    }

    public String getName()
    {
        return name;
    }

    public Address getAddress()
    {
        return address;
    }

    public String getId()
    {
        return id;
    }

    public String getPassword()
    {
        return password;
    }

    public void setId(Address address) {
        this.id = address.id();
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /*@Override
    public String toString() {
        return "Receiver : " + name + " | " + address + "\n";
    }*/

    @Override
    public void changePassword(String newpass)
    {
        if (!newpass.equals(getPassword()))
        {
            setPassword(newpass);
        }
    }
}
