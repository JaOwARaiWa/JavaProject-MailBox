package condo.process;

import condo.model.*;

import java.io.*;
import java.util.ArrayList;

public interface ProgramDataSource
{
    // universal
    ArrayList readAllAccount() throws IOException;
    void changePass(User currentUser, String newPass) throws IOException;

    // admin
    boolean addStaff(Staff newAccount) throws IOException;
    void writeNewStaff(Staff newStaff) throws IOException;
    ArrayList<Staff> readStaffAccount() throws IOException;
    void updateLogInStaff(Staff currentStaff) throws IOException;

    //staff
    boolean addNewRoom(Room newRoom) throws IOException;
    ArrayList<Room> readCondoRoom() throws IOException;
    void updateResidentInRoom(Room currentRoom) throws IOException;
    boolean checkRoomAndOwner(String thisRoom) throws IOException;
    void addMail(Mail newMail) throws IOException;
    ArrayList<Mail> readMail(String show) throws IOException;
    //ArrayList<Mail> readHistory() throws IOException;
    void pickUpMail(Mail currentMail) throws IOException;
    //void updateMail(Mail currentMail) throws IOException;

    //resident
    void register(Resident newUser) throws IOException;

}
