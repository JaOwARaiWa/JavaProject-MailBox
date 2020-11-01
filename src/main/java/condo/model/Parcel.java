package condo.model;

public class Parcel extends Letter
{
    private String service;
    private String tracknumber;

    public Parcel(String type, String to, String room, String staff, String sender, String address, String size, String image, String date, String time, String receiver, String status, String service, String tracknumber)
    {
        super(type, to, room, staff, sender, address, size, image, date, time, receiver, status);
        this.service = service;
        this.tracknumber = tracknumber;
    }

    public String getService()
    {
        return service;
    }

    public String getTracknum()
    {
        return tracknumber;
    }

    public void setService(String service)
    {
        this.service = service;
    }

    public void setTracknum(String tracknum)
    {
        this.tracknumber = tracknum;
    }


}
