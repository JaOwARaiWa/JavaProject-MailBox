package condo;

import java.util.Scanner;

public class Staff implements Changing
{
    private String name;
    private String id;
    private String password;

    public Staff(String name, String id)
    {
        this.name = name;
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public String getId()
    {
        return id;
    }

    public String getPassword()
    {
        return password;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    /*@Override
    public String toString()
    {
        return "Staff : " + name + " | " +  id + "\n";
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
