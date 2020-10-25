package condo.model;

public class Letter extends Mail
{
    public Letter(Resident receiver, String sender, String size, boolean status)
    {
        super(receiver, sender, size, status);
    }
}
