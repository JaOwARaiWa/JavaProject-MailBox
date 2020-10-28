package condo.model;

public class Letter extends Mail
{
    public Letter(String type, String to, String room, String staff, String sender, String address, String size, String image, String date, String time, String receiver, boolean status)
    {
        super(type, to, room, staff, sender, address, size, image, date, time, receiver, status);
    }
}
