package condo.model;

public class User
{
    private String username;
    private String password;
    private String name;
    private String id;

    public User(String username, String password, String name, String id)
    {
        this.username = username;
        this.password = password;
        this.name = name;
        this.id = id;
    }

    public User(String username, String password, String name)
    {
        this.username = username;
        this.password = password;
        this.name = name;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public void reset()
    {
        this.id = null;
        this.username = null;
        this.password = null;
        this.name = null;
    }
}
