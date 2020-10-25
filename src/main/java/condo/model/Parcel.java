package condo.model;

public class Parcel extends Mail
{
    private String service;
    private String tracknumber;


    public Parcel(Resident receiver, String sender, String size, boolean status, String service, String tracknum)
    {
        super(receiver, sender, size, status);
        this.service = service;
        this.tracknumber = tracknum;
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

    /*@Override
    public String toString()
    {
        return "Parcel - from : " + getSender() + " to : " + getReceiver() + " " + getReceiver().getAddress() + " (by " + service + " : " + tracknum + ") | size : " + getSize() +  " (" + isStatus() + ")\n";
    }*/
}
