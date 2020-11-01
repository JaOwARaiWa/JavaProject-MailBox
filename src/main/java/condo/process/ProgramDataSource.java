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
    void addMail(Letter newLetter) throws IOException;
    ArrayList<Letter> readMail(String show) throws IOException;
    ArrayList<Letter> searchMail(String patial) throws IOException;
    void pickUpMail(Letter currentLetter) throws IOException;

    //resident
    void register(Resident newUser) throws IOException;

}
