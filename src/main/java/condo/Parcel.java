package condo;

public class Parcel extends Mail
{
    private String service;
    private String tracknum;


    public Parcel(Receiver receiver, Sender sender, String size, boolean status, String service, String tracknum)
    {
        super(receiver, sender, size, status);
        this.service = service;
        this.tracknum = tracknum;
    }

    public String getService()
    {
        return service;
    }

    public String getTracknum()
    {
        return tracknum;
    }

    public void setService(String service)
    {
        this.service = service;
    }

    public void setTracknum(String tracknum)
    {
        this.tracknum = tracknum;
    }

    /*@Override
    public String toString()
    {
        return "Parcel - from : " + getSender() + " to : " + getReceiver() + " " + getReceiver().getAddress() + " (by " + service + " : " + tracknum + ") | size : " + getSize() +  " (" + isStatus() + ")\n";
    }*/
}
