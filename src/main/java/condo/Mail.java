package condo;

public class Mail
{
    private Roomer receiver;
    private Sender sender;
    private String size;
    //picture of this mail
    private boolean status;
    private Staff staff; //who receive the mail from the mail man
    private char type;//to check the type of mail (L) is Letter, (D) is Document, (P) is Parcel

    public Mail(Roomer receiver, Sender sender, String size, boolean status)
    {
        this.receiver = receiver;
        this.sender = sender;
        this.size = size;
        this.status = status;
    }

    public Roomer getReceiver()
    {
        return receiver;
    }

    public Sender getSender()
    {
        return sender;
    }

    public String getSize()
    {
        return size;
    }

    public boolean isStatus()
    {
        return status;
    }

    public void setStatus(boolean status)
    {
        this.status = status;
    }

    /*@Override
    public String toString()
    {
        return "Letter - from : " + sender + " to : " + receiver  + " " + receiver.getAddress() + " | size : " + size + " (" + status + ")\n";
    }*/
}
