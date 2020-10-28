package condo.process;

import condo.model.Mail;
import condo.model.Room;
import condo.model.Staff;
import condo.model.User;

import java.io.*;
import java.util.ArrayList;

public interface ProgramDataSource
{
    // universal
    ArrayList readAllAccount() throws IOException;
    void changePass(User currentUser, String newPass) throws IOException;

    // admin
    boolean addStaff(ArrayList<String> newAccount) throws IOException;
    void writeNewStaff(ArrayList<String> newAccount) throws IOException;
    ArrayList<Staff> showStaffHistory() throws IOException;
    void updateLogInStaff(Staff currentStaff) throws IOException;

    //staff
    boolean addNewRoom(Room newRoom) throws IOException;
    ArrayList<Room> showResidentRoom() throws IOException;
    void updateResidentInRoom(Room currentRoom) throws IOException;
    boolean checkRoomAndOwner(String thisRoom) throws IOException;
    void addMail(Mail newMail) throws IOException;
    ArrayList<Mail> readMail() throws IOException;
    ArrayList<Mail> readHistory() throws IOException;
    void pickUpMail(Mail currentMail) throws IOException;
    void updateMail(Mail currentMail) throws IOException;

}
