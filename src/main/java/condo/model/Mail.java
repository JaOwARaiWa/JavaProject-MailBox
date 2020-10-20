package condo.model;

import javafx.scene.image.Image;

import java.io.File;

public class Mail
{
    private Resident receiver;
    private Sender sender;
    private String size;
    private String image;
    private boolean status;
    private Staff staff;
    private char type;

    public Mail(Resident receiver, Sender sender, String size, boolean status)
    {
        this.receiver = receiver;
        this.sender = sender;
        this.size = size;
        this.status = status;
    }

    public Resident getReceiver()
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
