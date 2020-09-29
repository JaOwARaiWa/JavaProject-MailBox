package condo;

import java.util.Scanner;

public class Admin implements Changing
{
    private String name;
    private String id;
    private String password;

    public Admin(String name, String id, String password)
    {
        this.name = name;
        this.id = id;
        this.password = password;
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
        return "Admin : " + name + " | " + id + "\n";
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