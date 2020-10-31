package condo.model;

public class Mail
{
    private String type;

    private String to;
    private String room;
    private String staff;

    private String sender;
    private String address;

    private String size;
    private String image;
    private String date;
    private String time;

    private String receiver;

    private String status;

    public Mail(String type, String to, String room, String staff, String sender, String address, String size, String image, String date, String time, String receiver, String status)
    {
        this.type = type;
        this.to = to;
        this.room = room;
        this.staff = staff;
        this.sender = sender;
        this.address = address;
        this.size = size;
        this.image = image;
        this.date = date;
        this.time = time;
        this.receiver = receiver;
        this.status = status;
    }

    public String getType()
    {
        return type;
    }

    public String getTo()
    {
        return to;
    }

    public String getRoom()
    {
        return room;
    }

    public String getStaff()
    {
        return staff;
    }

    public String getSender()
    {
        return sender;
    }

    public String getAddress()
    {
        return address;
    }

    public String getSize()
    {
        return size;
    }

    public String getImage()
    {
        return image;
    }

    public String getDate()
    {
        return date;
    }

    public String getTime()
    {
        return time;
    }

    public String getReceiver()
    {
        return receiver;
    }

    public String getStatus()
    {
        return status;
    }

    /*-----------------------------------------------------------------------------------------------------*/

    public void setType(String type)
    {
        this.type = type;
    }

    public void setTo(String to)
    {
        this.to = to;
    }

    public void setRoom(String room)
    {
        this.room = room;
    }

    public void setStaff(String staff)
    {
        this.staff = staff;
    }

    public void setSender(String sender)
    {
        this.sender = sender;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public void setSize(String size)
    {
        this.size = size;
    }

    public void setImage(String image)
    {
        this.image = image;
    }

    public void setDate(String date)
    {
        this.date = date;
    }

    public void setTime(String time)
    {
        this.time = time;
    }

    public void setReceiver(String receiver)
    {
        this.receiver = receiver;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }


}
