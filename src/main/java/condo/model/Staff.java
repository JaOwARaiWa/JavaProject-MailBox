package condo.model;

public class Staff extends User
{
    private String surname;
    private String date;
    private String time;

    public Staff(String username, String password, String name, String id)
    {
        super(username, password, name, id);
    }

    public Staff(String username, String password, String name, String surname, String date, String time)
    {
        super(username, password, name);
        this.surname = surname;
        this.date = date;
        this.time = time;
    }

    public void setSurname(String surname)
    {
        this.surname = surname;
    }

    public String getSurname()
    {
        return surname;
    }

    public void setDate(String date)
    {
        this.date = date;
    }

    public String getDate()
    {
        return date;
    }

    public void setTime(String time)
    {
        this.time = time;
    }

    public String getTime()
    {
        return time;
    }
}
