package condo.model;

public class Document extends Mail
{
    private String priorityMsg;
    private int priorityLv;

    public Document(Resident receiver, String sender, boolean status, String size, String priorityMsg, int priorityLv)
    {
        super(receiver, sender, size, status);
        this.priorityMsg = priorityMsg;
        this.priorityLv = priorityLv;
    }

    public String getPriorityMsg()
    {
        return priorityMsg;
    }

    public int getPriorityLv()
    {
        return priorityLv;
    }

    /*@Override
    public String toString()
    {
        return "Document - from : " + getSender() + " to : " + getReceiver() + " " + getReceiver().getAddress() + " | size : " + getSize() + " [" + priorityMsg + "] (" + isStatus() + ")\n";
    }*/
}
