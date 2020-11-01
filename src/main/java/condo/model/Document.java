package condo.model;

public class Document extends Letter
{
    private String priority;

    public Document(String type, String to, String room, String staff, String sender, String address, String size, String image, String date, String time, String receiver, String status, String priority)
    {
        super(type, to, room, staff, sender, address, size, image, date, time, receiver, status);
        this.priority = priority;
    }

    public String getPriority()
    {
        return priority;
    }

    public void setPriority(String priority)
    {
        this.priority = priority;
    }
}
